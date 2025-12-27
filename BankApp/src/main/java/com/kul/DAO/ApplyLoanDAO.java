package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kul.beanclass.LoanBean;
import com.kul.beanclass.UserBean;
import com.kul.dbconnection.DBConnection;

public class ApplyLoanDAO {

	
	public int  ApplyLoan(LoanBean lb, UserBean ub) {
		Connection con=DBConnection.getConnection();
		int count=0;
		try {
			PreparedStatement pstmt=con.prepareStatement(" insert into loans(customer_id, loan_type, amount, duration,status) values (?, ?, ?, ?, ?)");
		    pstmt.setInt(1,ub.getU_ID());
		    pstmt.setString(2,lb.getLaon_type());
		    pstmt.setDouble(3, lb.getAmount());
		    pstmt.setInt(4,lb.getDuration());
		    pstmt.setString(5, "Approved");
		    count=pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return count;
	}

}
