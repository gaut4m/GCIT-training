<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="com.gcit.lms.service.LibrarianService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<% AdministratorService admin = new AdministratorService();
LibrarianService lib =new LibrarianService();
int branchId = new Integer(request.getParameter("branchId"));

Branch branch = new Branch();
branch = lib.getBranch(branchId);
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body align="center">

<h1>Branch Management</h1>
<h3>You have chosen to update Branch details of <%=branch.getBranchName() %> branch </h3>

<form action="updateBranch" method="post">
	
		<input type="text" name="branchId" value="<%=branch.getBranchId() %>" style="display:none">
		<table align="center" >
		<tr><td>
		Enter Branch Name:</td><td> <input type="text" name="branchName"  value="<%=branch.getBranchName()%>"></td></tr>
		<tr><td>
		Enter Branch Address:</td><td> <input type="text" name="branchAddr" value="<%if(branch.getBranchName()!=null)out.println(branch.getBranchAddress());%>">
		 </td></tr>
		 <tr>
		 <td>
		 </td><td><input type="submit" value="Submit"></td></tr>
		 </table>
		
	
	</form>
	<br/>
	<a href="branch.jsp?branchId=<%=branch.getBranchId()%>">Previous page</a><br/>

</body>
</html>