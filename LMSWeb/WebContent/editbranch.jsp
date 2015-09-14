
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.domain.BCopies"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="com.gcit.lms.service.LibrarianService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	AdministratorService admin = new AdministratorService();
	LibrarianService lib =new LibrarianService();
int branchId = new Integer(request.getParameter("branchId"));
	
	Branch branch = new Branch();
	branch = lib.getBranch(branchId);
	
	List<BCopies> copies = new ArrayList<BCopies>();

	copies=lib.getCopies(branchId);
	
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>


</head>
<body align="center">

<h1>Branch Management</h1>
<h3>You have chosen to update Branch details of <%=branch.getBranchName() %> branch </h3>

<form action="editBranch" method="post">
	
		<input type="text" name="branchId" value="<%=branchId %>" style="display:none">
		<table align="center" >
		<tr><td>
		Enter Branch Name:</td><td> <input type="text" name="branchName" value="<%=branch.getBranchName()%>"></td></tr>
		<tr><td>
		Enter Branch Address:</td><td> <input type="text" name="branchAddress" value="<%if(branch.getBranchAddress()!=null)out.println(branch.getBranchAddress());%>">
		 </td></tr>
		 <tr>
		 <td>
		 </td><td><input type="submit" value="Submit"></td></tr>
		 </table>
		
	
	</form>
	<br/>
	
<h2> View Existing Copies</h2>
<table align="center">
	<tr>
	<th>Branch Name</th>
	<th>Book Title</th>
	<th>No.Of Copies</th>
	<th>Add Copies</th>
	</tr>
	<%for(BCopies a: copies){ %>
		<tr>
			<form  action="editBranchCopy" id="myform"  method="post">
			<td align="center" >
			<input type="text" name="branchId" value="<%=branch.getBranchId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="branchName" name="branchName" value="<%=a.getBranch().getBranchName()%>" size="3" readonly>
		
			</td>
			<td align="center" >
			<input type="text" id="bookId" name="bookId" value="<%=a.getBook().getBookId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="title" name="title" value="<%=a.getBook().getTitle()%>" readonly></td>
			<td align="center"><input type="text" style="text-align:center;" id="noOfCopies" name="noOfCopies" value="<%=a.getNoofCopies()%>" size="5" readonly></td>
			<td align="center">+&nbsp;<input type="text" style="text-align:center;" id="addCopies" name="addCopies" value="" size="5">&nbsp;<input type="submit" value="UPDATE"></td>
			</form>
			</tr>
	<%} %>
	
</table>
<br/>
	
	<a href="viewbranch.jsp">Previous page</a><br/>

</body>
</html>