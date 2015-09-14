<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
	<h1>Add Publisher</h1>
	<h2>Enter Publisher Details</h2>
	<form action="addPublisher" method="post">
	
		Enter Publisher Name: <input type="text" name="publisherName"><br/>
		Enter Publisher Address: <input type="text" name="publisherAddr"><br/>
		Enter Publisher Phone: <input type="text" name="publisherPhone"><br/>
		 
		<input type="submit" value="Submit">
	
	</form>
<a href="publisher.jsp">Previous Page</a><br/>
	<p>
</body>
</html>