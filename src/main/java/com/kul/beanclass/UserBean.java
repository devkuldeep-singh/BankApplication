package com.kul.beanclass;

import java.io.Serializable;
import java.sql.Date;

import com.kul.pack1.AccountNumberGenerator;

public class UserBean implements Serializable {
	
	  private String accNo=AccountNumberGenerator.generateAccountNo();
	  private int u_ID;
	  private String full_Name;
	  private String email;
	  private String phoneNo;
	  private String userName;
	  private String pass;
      private double balance=20000.0;
      private Date Acc_open_date;
      
       
	 

	public String to() {
		return "UserBean [accNo=" + accNo + ", full_Name=" + full_Name + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", userName=" + userName + ", pass=" + pass + ", balance=" + balance + "]";
	}
	  public int getU_ID() {
		return u_ID;
	}
	public void setU_ID(int u_ID) {
		this.u_ID = u_ID;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	  public UserBean() {
		super();
	  }
	  
	  
	  public String getFull_Name() {
		  return full_Name;
	  }
	  public void setFull_Name(String full_Name) {
		  this.full_Name = full_Name;
	  }
	  public String getEmail() {
		  return email;
	  }
	  public void setEmail(String email) {
		  this.email = email;
	  }
	  public String getPhoneNo() {
		  return phoneNo;
	  }
	  public void setPhoneNo(String phoneNo) {
		  this.phoneNo = phoneNo;
	  }
	  public String getUserName() {
		  return userName;
	  }
	  public void setUserName(String userName) {
		  this.userName = userName;
	  }
	  public String getPass() {
		  return pass;
	  }
	  public void setPass(String pass) {
		  this.pass = pass;
	  }
	  public String getAccNo() {
		  return accNo;
	  }
	  public double getBalance() {
		  return balance;
	  }

		public Date getAcc_open_date() {
			return Acc_open_date;
		}
		  public void setAcc_open_date(Date acc_open_date) {
			  Acc_open_date = acc_open_date;
		  }
      
}
