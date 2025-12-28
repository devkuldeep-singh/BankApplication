package com.kul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kul.dbconnection.DBConnection;

public class PhotoDAO {
	


	    public static boolean updatePhoto(int id, byte[] photo) {

	        String sql = "UPDATE USERS SET photo=? WHERE id=?";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setBytes(1, photo);
	            ps.setInt(2, id);

	            return ps.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }


	    public static byte[] getPhoto(int id) {
	        String sql = "SELECT photo FROM USERS WHERE id=?";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return rs.getBytes("photo");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}


