<%@page import="com.gcit.lms.domain.Borrower"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.domain.Branch"%>
    <%@ page import="com.gcit.lms.domain.Borrower"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
    <%
    Borrower br =new Borrower();
if(null == session.getAttribute("cardNo")){
	  // User is not logged in.
	response.sendRedirect(request.getContextPath() + "/borrower.jsp");
	
	}else{
	  // User IS logged in.
	  br.setCardNo((Integer)session.getAttribute("cardNo"));
	  br.setName((String)session.getAttribute("name"));
	  
		
	}
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>

<h1> Welcome <%=br.getName() %> !!</h1>
<p>
<a href="selectbranch.jsp">Check out Books</a><br/>
<a href="checkin.jsp">Return Books</a><br/>
</p>

<form action="logOut" method="post">
    <input type="submit" value="Logout" />
</form>
</body>
</html>