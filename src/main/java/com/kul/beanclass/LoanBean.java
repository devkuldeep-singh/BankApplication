package com.kul.beanclass;

import java.sql.Date;

public class LoanBean {
	private int loan_id;
	private int customer_Id;
	private String laon_type;
	private double amount;
	private int duration;
	private String status;
	private Date apply_Date;
	
	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public int getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(int customer_Id) {
		this.customer_Id = customer_Id;
	}

	public String getLaon_type() {
		return laon_type;
	}

	public void setLaon_type(String laon_type) {
		this.laon_type = laon_type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getApply_Date() {
		return apply_Date;
	}

	public void setApply_Date(Date apply_Date) {
		this.apply_Date = apply_Date;
	}

	public LoanBean() {
		super();
	}
}
