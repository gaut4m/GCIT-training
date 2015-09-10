<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.domain.BCopies"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<% JDBC jdbc = new JDBC();
List<Branch> branches = new ArrayList<Branch>();
Branch b = new Branch();

int branchId = new Integer(request.getParameter("branchId"));
b.setBranchId(branchId);
branches = jdbc.getBranch(b);
b=branches.get(0);
List<BCopies> copies = new ArrayList<BCopies>();

copies= jdbc.getCopies(b);


%>      

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
${message}

<h2> View Existing Authors</h2>
<table>
	<tr>
	<th>Branch ID</th>
	<th>Book ID</th>
	<th>Title</th>
	<th>No.Of Copies</th>
	<th>Add Copies</th>
	</tr>
	<%for(BCopies a: copies){ %>
		<tr>
			<form  action="updateCopies" id="myform"  method="post">
			<td align="center" >
			<input type="text" style="text-align:center;" name="branchId" value="<%=b.getBranchId()%>" size="3" readonly>
			</td>
			<td align="center" >
		<input type="text" style="text-align:center;" id="bookId" name="bookId" value="<%=a.getBook().getBookId()%>" size="3" readonly>
		</td>
			<td align="center">
			<input type="text" style="text-align:center;" id="title" name="title" value="<%=a.getBook().getTitle()%>" readonly></td>
			<td align="center"><input type="text" style="text-align:center;" id="noOfCopies" name="noOfCopies" value="<%=a.getNoofCopies()%>" size="5" readonly></td>
			<td align="center">+&nbsp;<input type="text" style="text-align:center;" id="addCopies" name="addCopies" value="" size="5">&nbsp;<input type="submit" value="UPDATE"></td>
			</form>
			</tr>
	<%} %>
	
</table>
<br/>

<a href="branch.jsp?branchId=<%=b.getBranchId()%>">Previous page</a><br/>


</body>
</html>