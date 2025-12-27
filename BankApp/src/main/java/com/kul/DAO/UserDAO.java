package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kul.beanclass.UserBean;
import com.kul.dbconnection.DBConnection;

public class UserDAO {
	
	
	public static  int inserData(UserBean ub) {
		Connection con=DBConnection.getConnection();
		int count=0;
		
		try {
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO bankapp(FULL_NAME, EMAIL, USERNAME, PASSWORD, PHONE_NUMBER, ACCOUNT_NUMBER, BALANCE, CREATED_AT) VALUES(?, ?, ?, ?, ?, ?, ?, sysdate)");
		
			pstmt.setString(1, ub.getFull_Name());
			pstmt.setString(2, ub.getEmail());
			pstmt.setString(3, ub.getUserName());
			pstmt.setString(4, ub.getPass());
			pstmt.setString(5, ub.getPhoneNo());
			pstmt.setString(6, ub.getAccNo());
			pstmt.setDouble(7, ub.getBalance());
			System.out.println(ub.getFull_Name()+"  "+ ub.getEmail()+"  "+ub.getUserName()+" "+ub.getPass()+"  "+ub.getPhoneNo()+"  "+ub.getAccNo()+"  "+ub.getBalance());
			
			count = pstmt.executeUpdate();
			
			
		}
		catch (Exception e) {
			System.out.println("Error2....");
			e.printStackTrace();
			
		}
		System.out.println(count);
		return count;
	}

}
