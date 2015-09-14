<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
	<h1>Add Branch</h1>
	<h2>Enter Branch Details</h2>
	<form action="addBranch" method="post">
	
		Enter Branch Name: <input type="text" name="branchName"><br/>
		Enter Branch Address: <input type="text" name="branchAddress"><br/>
		<input type="submit" value="Submit">
	
	</form>
<a href="branchdetails.jsp">Previous Page</a><br/>
	<p>
</body>
</html>