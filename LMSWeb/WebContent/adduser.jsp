<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
	<h1>Add Borrower</h1>
	<h2>Enter Borrower Details</h2>
	<form action="addUser" method="post">
	
		Enter Borrower Name: <input type="text" name="name"><br/>
		Enter Borrower Address: <input type="text" name="address"><br/>
		Enter Borrower Phone: <input type="text" name="phone"><br/>
		 
		<input type="submit" value="Submit">
	
	</form>
<a href="author.jsp">Previous Page</a><br/>
	<p>
</body>
</html>