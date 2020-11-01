<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan success</title>
</head>
<body>
<%
session=request.getSession();
out.println("Dear,"+session.getAttribute("name")+"thank you for showing your interest on the loan from tech bank");
	out.println("<br>");
out.println("our executive will contact you soon on your email address mentioned below:");
out.println("<br>");
out.println(session.getAttribute("email"));
%>
</body>
</html>