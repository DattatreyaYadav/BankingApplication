package com.dattu.controller;

//import java.io.IOException;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dattu.model.Model;

public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,HttpServletResponse response) {
		
		// getting data from the user 
		String scust_id=request.getParameter("custid");
		int cust_id=Integer.parseInt(scust_id);
		
		String name=request.getParameter("name");
		
		String sacc_number=request.getParameter("accno");
		int acc_number=Integer.parseInt(sacc_number);
		
		String sbalance=request.getParameter("balance");
		int balance=Integer.parseInt(sbalance);
		
		String sphone=request.getParameter("phone");
		int phone=Integer.parseInt(sphone);
		
		String email=request.getParameter("email");
//		System.out.println(email);
		String pwd=request.getParameter("password");
		
		String confpwd=request.getParameter("conf_password");
		
		try {
			
			//sending the data to the database with the help of pojo class
			Model m = new Model();
			
			m.setName(name);
			m.setAccno(acc_number);
			m.setBalance(balance);
			m.setCustid(cust_id);
			m.setPwd(pwd);
			m.setConf_pwd(confpwd);
//			System.out.println(email);
			m.setEmail(email);
			m.setPhone(phone);
			
			boolean result = m.register();
			
			if(result)
			{
			   response.sendRedirect("/BankingApplication/successregister.html");
			}
			else 
			{
			   response.sendRedirect("/BankingApplication/failureregister.html");
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
