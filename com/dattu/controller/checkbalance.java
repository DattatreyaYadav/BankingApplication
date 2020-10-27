package com.dattu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dattu.model.Model;


public class checkbalance extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
        int accno=(int) session.getAttribute("accno");
        try {
        	Model m = new Model();
        	m.setAccno(accno);
        	boolean result= m.checkBalance();
        	
        	if(result)
        	{
        		session.setAttribute("bal",m.getBal());
        		response.sendRedirect("/BankingApplication/BalanceView.jsp");
        	}
        	else
        	{
        		response.sendRedirect("/BankingApplication/Balancefail.html");
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
}
