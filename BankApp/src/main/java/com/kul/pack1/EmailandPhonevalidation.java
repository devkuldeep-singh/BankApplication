package com.kul.pack1;

public class EmailandPhonevalidation {

	public static boolean phoneIsValid(String phone) {
        if (phone == null) return false;
        return phone.matches("[6-9][0-9]{9}");
    }
	
	 public static boolean emailIsValid(String email) {
	        if (email == null) return false;
	        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	    }
}
