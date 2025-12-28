package com.kul.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			
			  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","advjava","adv");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error2");
			e.printStackTrace();
		}
		return con;
	}
	
}
