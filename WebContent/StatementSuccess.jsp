<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>succcess page</title>
</head>
<body>
<%
session=request.getSession();
out.println(session.getAttribute("sal"));
out.println("<br>");
out.println(session.getAttribute("ral"));
out.println("<br>");
out.println(session.getAttribute("al"));

%>
</body>
</html>