package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kul.beanclass.TransferDataBean;
import com.kul.beanclass.UserBean;
import com.kul.dbconnection.DBConnection;

public class TransferMoneyDAO {
	
	

	public static UserBean  checkIfPresent(String number) {
		Connection con=DBConnection.getConnection();
		UserBean ub=null;
		try {
			PreparedStatement pstmt=con.prepareStatement("select * from bankapp where PHONE_NUMBER=?");
			
			pstmt.setString(1,number);
			
			ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			ub = new UserBean();
            ub.setU_ID(rs.getInt("USER_ID"));
            ub.setFull_Name(rs.getString("FULL_NAME"));
            ub.setEmail(rs.getString("EMAIL"));
            ub.setUserName(rs.getString("USERNAME"));
            ub.setAccNo(rs.getString("ACCOUNT_NUMBER"));
            ub.setBalance(rs.getDouble("BALANCE"));
            ub.setPhoneNo(rs.getString("PHONE_NUMBER"));
            ub.setPass(rs.getString("PASSWORD"));
            ub.setAcc_open_date(rs.getDate("CREATED_AT"));
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return ub;
		
	}
	
	
	public static int transferMoney(UserBean ub, TransferDataBean ts) {
		Connection con=DBConnection.getConnection();
			int count=0;
		String sender="UPDATE bankapp SET balance = balance - ? WHERE PHONE_NUMBER  = ?";
		String receive="update bankapp set balance=balance+? where PHONE_NUMBER =?";
		try {
			con.setAutoCommit(false);
		
			PreparedStatement pstmt=con.prepareStatement(sender);
			pstmt.setDouble(1, ts.getAmount());
			pstmt.setString(2,ub.getPhoneNo());
			
			int senderCount=pstmt.executeUpdate();
		
			
			PreparedStatement pstmt1=con.prepareStatement(receive);
			pstmt1.setDouble(1, ts.getAmount());
			pstmt1.setString(2,ts.getReceiver_acc());
			int receiverCount=pstmt1.executeUpdate();
			
			System.out.println("Check"+senderCount+"  "+receiverCount);
			
			if(senderCount>0 && receiverCount>0) {
				
				
				PreparedStatement pstmt2=con.prepareStatement("insert into BANK_TRANSACTIONS (SENDER_ACCOUNT, RECEIVER_ACCOUNT, AMOUNT, TXN_TYPE, DESCRIPTION, STATUS) values(?, ?, ?, ? ,?, ?)");
				pstmt2.setString(1,ub.getPhoneNo());
				pstmt2.setString(2,ts.getReceiver_acc());
				pstmt2.setDouble(3, ts.getAmount());
				pstmt2.setString(4,"Debit");
				pstmt2.setString(5, ts.getDescription());
				pstmt2.setString(6, "Complete");
				int count1=pstmt2.executeUpdate();
				count=1;
				
				System.out.println(".....123456"+ub.getAccNo()+" "+ts.getReceiver_acc()+" "+ts.getAmount()+" "+"Cradit"+" "+ ts.getDescription());
				con.commit();
				//-------------------------
				PreparedStatement pstmt3=con.prepareStatement(" insert into BANK_TRANSACTIONS (SENDER_ACCOUNT, RECEIVER_ACCOUNT, AMOUNT, TXN_TYPE, DESCRIPTION, STATUS) values(?, ?, ?, ? ,?, ?)");
				pstmt3.setString(1,ts.getReceiver_acc());
				pstmt3.setString(2,ub.getPhoneNo());
				pstmt3.setDouble(3, ts.getAmount());
				pstmt3.setString(4, "Credit");
				pstmt3.setString(5, ts.getDescription());
				pstmt3.setString(6, "Complete");
				pstmt3.executeUpdate();
				con.commit();
			}
			else {
				con.rollback();
				PreparedStatement pstmt2=con.prepareStatement(" insert into BANK_TRANSACTIONS (SENDER_ACCOUNT, RECEIVER_ACCOUNT, AMOUNT, TXN_TYPE, DESCRIPTION, STATUS) values(?, ?, ?, ? ,?, ?)");
				pstmt2.setString(1,ub.getPhoneNo());
				pstmt2.setString(2,ts.getReceiver_acc());
				pstmt2.setDouble(3, ts.getAmount());
				pstmt2.setString(4,"Debit");
				pstmt2.setString(5, ts.getDescription());
				pstmt2.setString(6, "Failed");
				con.commit();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	

}
