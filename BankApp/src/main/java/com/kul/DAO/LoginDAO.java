package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kul.beanclass.UserBean;
import com.kul.dbconnection.DBConnection;

public class LoginDAO {

    public UserBean checkdata(String uName, String pass) {

        UserBean ub = null;  

        Connection con = DBConnection.getConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement(
                "select * from bankapp where USERNAME=? and PASSWORD=?"
            );
            pstmt.setString(1, uName);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ub;
    }
}
