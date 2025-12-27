package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kul.beanclass.TransferDataBean;
import com.kul.beanclass.UserBean;
import com.kul.dbconnection.DBConnection;

public class TransactionDAO {
	   ArrayList<TransferDataBean> details=null;
	public ArrayList<TransferDataBean> getTransactionHistory(UserBean ub){
		
		Connection con=DBConnection.getConnection();
		String query="SELECT * FROM BANK_TRANSACTIONS WHERE SENDER_ACCOUNT=? ORDER BY Txn_DATE DESC";
		try{
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setString(1, ub.getPhoneNo());
			ResultSet rs=pstmt.executeQuery();
			details=new ArrayList<TransferDataBean>();
			while(rs.next()) {
				TransferDataBean ts=new TransferDataBean();
				ts.setTras_Date(rs.getDate("TXN_DATE"));
				ts.setReceiver_acc(rs.getString("RECEIVER_ACCOUNT"));
				ts.setSender_acc(rs.getString("SENDER_ACCOUNT"));
				ts.setDescription(rs.getString("DESCRIPTION"));
				ts.setTran_Type(rs.getString("TXN_TYPE"));
				ts.setAmount(rs.getDouble("AMOUNT"));
				ts.setStatus(rs.getString("STATUS"));
				details.add(ts);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println(details);
		return details;
	}

}
