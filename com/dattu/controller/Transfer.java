package com.dattu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dattu.model.Model;


public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		HttpSession session=request.getSession();
		int accno=(int) session.getAttribute("accno");
		
		String samt=request.getParameter("amt");
		int amt= Integer.parseInt(samt);
		String sraccno=request.getParameter("raccno");
		int raccno= Integer.parseInt(sraccno);
		
		
		try {
			Model m=new Model();
			m.setAccno(accno);
			m.setRaccno(raccno);
			m.setBal(amt);
			boolean result= m.transfer();
			 if(result) {
				 response.sendRedirect("/BankingApplication/Transfersuccess.html");	 
			 }
			 else {
				 response.sendRedirect("/BankingApplication/TransferFail.html");	 
			 }
		}
		catch(Exception e) {
			
		}
		
		
	}

}
