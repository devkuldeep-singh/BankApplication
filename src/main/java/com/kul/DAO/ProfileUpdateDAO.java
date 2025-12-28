package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kul.beanclass.UserBean;
import com.kul.dbconnection.DBConnection;

public class ProfileUpdateDAO {
	
	public int updateProfile(UserBean ub, String fName, String email,String uname,String pass,String phoneno) {
		Connection con=DBConnection.getConnection();
		String query="update bankapp set FULL_NAME=? , EMAIL=? ,USERNAME=? ,PASSWORD=?, PHONE_NUMBER=? where ACCOUNT_NUMBER=?";
		int count=0;
		
		try{
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setString(1, fName);
			pstmt.setString(2, email);
			pstmt.setString(3, uname);
			pstmt.setString(4, pass);
			pstmt.setString(5, phoneno);
			pstmt.setString(6, ub.getAccNo());
			
			count=pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return count;
	}

}
