package com.dattu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dattu.model.Model;


public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session=null;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 session = request.getSession();
		int accno=(int) session.getAttribute("accno");
		
		String samount=request.getParameter("amount");
		int amount= Integer.parseInt(samount);
		String saccno=request.getParameter("accno");
		int conf_accno= Integer.parseInt(saccno);
		
		if(accno==conf_accno) {
			try {
				System.out.println("hi");
				Model m= new Model();
				m.setAccno(accno);
				m.setAmount(amount);
				boolean result=m.deposit();
				if(result) {
					response.sendRedirect("/BankingApplication/depositsuccess.html");
				}
				else {
					response.sendRedirect("/BankingApplication/depositfail.html");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			response.sendRedirect("/BankingApplication/depositfail.html");
		}
	}
}
