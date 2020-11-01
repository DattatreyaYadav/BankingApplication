package com.dattu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dattu.model.Model;


public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String pwd=request.getParameter("npwd");
		HttpSession session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		
		try {
			Model m=new Model();
			m.setAccno(accno);
			m.setPwd(pwd);
			boolean result=m.changePwd();
			if(result)
			{
				response.sendRedirect("/BankingApplication/ChangePwdSuccess.html");
			}
			else {
				response.sendRedirect("/BankingApplication/ChangePwdFail.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}	  
}
