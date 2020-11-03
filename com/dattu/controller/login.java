package com.dattu.controller;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dattu.model.Model;

public class login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private HttpSession session=null;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("hello");
		String scust_id = request.getParameter("custid");
		
		int cust_id=Integer.parseInt(scust_id);
		
		String pwd = request.getParameter("password");
		// creating a session to store the data for further use
		session=request.getSession(true);
		
		try 
		{
			Model m = new Model();
			m.setCustid(cust_id);
			m.setPwd(pwd);
			boolean result= m.login();
			
			if(result)
			{
				session.setAttribute("accno",m.getAccno());
				response.sendRedirect("/BankingApplication/home.html");
			}
			else
			{
				response.sendRedirect("/BankingApplication/error.html");	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
