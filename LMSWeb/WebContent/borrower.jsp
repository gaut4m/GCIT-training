<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
<%
if(null == session.getAttribute("cardNo")){
	  // User is not logged in.
	}else{
	  // User IS logged in.
	  
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/bindex.jsp");
		rd.forward(request, response); 
	}
%>

</head>
<body>
${message}
<h3>Enter CardNo to move forward</h3>
<form action="verifyCard" method="post">
	
		Enter CardNo: <input type="text" name="cardNo">
		
		 
		<input type="submit" value="Submit">
	
	</form>
<a href="index.html">Previous Page</a><br/>
</body>
</html>