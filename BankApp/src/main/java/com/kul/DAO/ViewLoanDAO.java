package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kul.beanclass.LoanBean;
import com.kul.beanclass.UserBean;
import com.kul.dbconnection.DBConnection;

public class ViewLoanDAO {
	
	ArrayList<LoanBean> list =null;
	
	public ArrayList<LoanBean> viewLoanDetails(UserBean ub){
		Connection con=DBConnection.getConnection();
		String query="Select * from loans where CUSTOMER_ID=? order by APPLY_DATE desc";
		list=new ArrayList<LoanBean>();
		try{
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, ub.getU_ID());
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				LoanBean lb=new LoanBean();
				lb.setLoan_id(rs.getInt("LOAN_ID"));
				lb.setCustomer_Id(rs.getInt("CUSTOMER_ID"));
				lb.setLaon_type(rs.getString("LOAN_TYPE"));
				lb.setAmount(rs.getDouble("AMOUNT"));
				lb.setDuration(rs.getInt("DURATION"));
				lb.setStatus(rs.getString("STATUS"));
				lb.setApply_Date(rs.getDate("APPLY_DATE"));
				list.add(lb);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
