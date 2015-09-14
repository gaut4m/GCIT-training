<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.domain.BCopies"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="com.gcit.lms.service.LibrarianService"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<% 
AdministratorService admin = new AdministratorService();
LibrarianService lib =new LibrarianService();
int branchId = new Integer(request.getParameter("branchId"));

Branch branch = new Branch();
branch = lib.getBranch(branchId);

List<BCopies> copies = new ArrayList<BCopies>();

copies= lib.getCopies(branchId);


%>      

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
${message}

<h2> View Existing Copies</h2>
<table>
	<tr>
	<th>Branch Name</th>
	<th>Book Title</th>
	<th>No.Of Copies</th>
	<th>Add Copies</th>
	</tr>
	<%for(BCopies a: copies){ %>
		<tr>
			<form  action="updateCopies" id="myform"  method="post">
			<td align="center" >
			<input type="text" name="branchId" value="<%=branch.getBranchId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="branchName" name="branchName" value="<%=a.getBranch().getBranchName()%>" readonly>
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

<a href="branch.jsp?branchId=<%=branch.getBranchId()%>">Previous page</a><br/>


</body>
</html>