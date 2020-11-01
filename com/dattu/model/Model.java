package com.dattu.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.dattu.controller.ArrayList;

public class Model {
	
	private String name;
	private int custid;
	private int accno;
	private String pwd;
	private String conf_pwd;
	private int bal;
	private String email;
	private int phone;
	private int raccno;
	private ResultSet res=null;
	private Connection con=null;  
	private PreparedStatement pstmt=null;
	public ArrayList al=new ArrayList();
	public ArrayList sal=new ArrayList();
	public ArrayList ral=new ArrayList();
	public int getRaccno() {
		return raccno;
	}
	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getConf_pwd() {
		return conf_pwd;
	}
	public void setConf_pwd(String conf_pwd) {
		this.conf_pwd = conf_pwd;
	}
	public Model() throws Exception 
	{
	 Class.forName("com.mysql.jdbc.Driver");//loading the driver
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingApplication","root","mallesh@143");
	 
	 System.out.println("loading the driver and establishing the connection is completed");
	}
	public boolean register() throws SQLException {
		 
		String sql1="insert into BankingApplication.cust_details values(?,?,?,?,?,?,?,?)";
		pstmt=con.prepareStatement(sql1);
		pstmt.setInt(1,custid);
		pstmt.setString(2,name);
		pstmt.setInt(3,accno);
		pstmt.setInt(4,bal);
		pstmt.setInt(5,phone);
		pstmt.setString(6,email);
		pstmt.setString(7,pwd);
		pstmt.setString(8,conf_pwd);
		
		int a = pstmt.executeUpdate();
		if(a>0)
		{
			return true;
		}
		return false;
	}
	
	public boolean login() throws SQLException {
		  
		String sql2 ="select * from BankingApplication.cust_details where custid=? and pwd=?";
		pstmt=con.prepareStatement(sql2);
		
		pstmt.setInt(1,custid);
		pstmt.setString(2,pwd);
		
		ResultSet res=pstmt.executeQuery();
		
		while(res.next()==true) {
			
			accno = res.getInt("accno");
			return true;
			
		}
		return false;
		
	}
	public boolean checkBalance() throws SQLException {
		
		String sql3="select balance from BankingApplication.cust_details where accno=?";
		pstmt=con.prepareStatement(sql3);
		
		pstmt.setInt(1,accno);
		
          ResultSet res=pstmt.executeQuery();
		
		while(res.next()==true) {
			
			bal = res.getInt("balance");
			return true;
			
		}
		return false;
	}
	public boolean changePwd() throws SQLException {
		String sql4="update BankingApplication.cust_details set password=? where accno=?";
		pstmt=con.prepareStatement(sql4);
		pstmt.setString(1,pwd);
		pstmt.setInt(2,accno);
	    
		int x= pstmt.executeUpdate();
		if(x>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("resource")
	public boolean transfer() throws SQLException {
		
		String sql5 ="select * from BankingApplication.cust_details where accno=? ";
		pstmt=con.prepareStatement(sql5);
		pstmt.setInt(1,raccno);
		 res=pstmt.executeQuery();
		
		while(res.next()) {
			String sql6 = "update BankingApplication.cust_details set balance=balance-? where accno = ?";
			pstmt=con.prepareStatement(sql6);
			pstmt.setInt(1, bal);
			pstmt.setInt(2,accno);
			int y1=pstmt.executeUpdate();
			if(y1>0)
			{
				int x=res.getInt("balance");
				if(x>0) {
					String s3="update BankingApplication.cust_details set balance=balnace+? where accno=? ";
					pstmt=con.prepareStatement(s3);
					pstmt.setInt(1, bal);
					pstmt.setInt(2,raccno);
					int y2=pstmt.executeUpdate();
					if(y2>0) {
						String s4="insert into GetStatement  values(?,?,?)";
						pstmt=con.prepareStatement(s4);
						pstmt.setInt(1,accno);
						pstmt.setInt(2, raccno);
						pstmt.setInt(3, bal);
						int y=pstmt.executeUpdate();
						if(y>0) {
							return true;
						}
						else {
							return false;  
						}
					}
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	public ArrayList getStatement() throws SQLException  {
        String s="select * from GetStatement where accno=?";
        pstmt=con.prepareStatement(s);
        pstmt.setInt(1,accno);
        res = pstmt.executeQuery();
        while(res.next()) {
        	sal.add(res.getInt("ACCNO"));
        	ral.add(res.getInt("RACCNO"));
        	al.add(res.getInt("BALANCE"));
        }
		return al;
	}
	public boolean applyloan() throws SQLException {
        String s="select * from BankingApplication.cust_details where accno=?";
        pstmt=con.prepareStatement(s);
        pstmt.setInt(1,accno);
        res = pstmt.executeQuery();
        while(res.next())
        {
        	 name=res.getString("NAME");
        	 email=res.getString("EMAIL");
        	 return true;
        }
		return false;
	}
}
