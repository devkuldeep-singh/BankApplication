package com.kul.beanclass;

import java.sql.Date;

public class TransferDataBean {
	
	private String sender_acc;
	private String receiver_acc;
	private double amount;
	private String tran_Type;
	private  String description;
	private String status;
	private Date tras_Date;
	
	public TransferDataBean() {
		super();
	}

	public String getSender_acc() {
		return sender_acc;
	}

	public void setSender_acc(String sender_acc) {
		this.sender_acc = sender_acc;
	}

	public String getReceiver_acc() {
		return receiver_acc;
	}

	public void setReceiver_acc(String receiver_acc) {
		this.receiver_acc = receiver_acc;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTran_Type() {
		return tran_Type;
	}

	public void setTran_Type(String tran_Type) {
		this.tran_Type = tran_Type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTras_Date() {
		return tras_Date;
	}

	public void setTras_Date(Date tras_Date) {
		this.tras_Date = tras_Date;
	}

}
