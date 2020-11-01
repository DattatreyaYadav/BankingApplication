package com.dattu.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dattu.model.Model;


public class GetStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
	int accno=(int)session.getAttribute("accno");
	
	try {
		 Model m=new Model();
		 m.setAccno(accno);
		 ArrayList al=m.getStatement();
		 
		 if(al.isEmpty()==true)
		 {
			 response.sendRedirect("/BankingApplication/StatementFail.html");
		 }
		 else {
			 session.setAttribute("sal",m.sal);
			 session.setAttribute("ral",m.ral);
			 session.setAttribute("al",m.al);
			 response.sendRedirect("/BankingApplication/StatementSuccess.html");
		 }
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}

}
