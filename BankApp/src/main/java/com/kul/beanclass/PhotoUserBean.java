package com.kul.beanclass;

public class PhotoUserBean {

    private int id;
    private String full_Name;
    private String accNo;
    private byte[] photo;  // BLOB → byte array

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFull_Name() { return full_Name; }
    public void setFull_Name(String full_Name) { this.full_Name = full_Name; }

    public String getAccNo() { return accNo; }
    public void setAccNo(String accNo) { this.accNo = accNo; }

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }
	}


