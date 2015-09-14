<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.domain.Loans"%>
    <%@ page import="com.gcit.lms.domain.Borrower"%>
    <%@ page import="com.gcit.lms.service.AdministratorService"%>
    
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	AdministratorService admin = new AdministratorService();
	List<Borrower> br = new ArrayList<Borrower>();
	br = (List<Borrower>)admin.getService("users");
	
	 for(Borrower b: br)
	 {
		 List<Loans> loans = new ArrayList<Loans>();
		 loans = admin.getLoans(b.getCardNo());
		 b.setLoans(loans);
	 }
	 
	 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>

${message}



<h2> View Borrower Due Dates</h2>
<table>
	<tr>
	<th>cardNo</th>
	<th>Borrower Name</th>
	<th>Branch Name </th>
	<th>Book Title</th>
	<th>Check out Date</th>
	<th>Due Date </th>
	<th>Extend</th>
	</tr>
	<%for(Borrower b: br){ 
		for(Loans l: b.getLoans()){%>
		<tr>
			<form  action="extendDate" id="myform"  method="post">
			<td align="center" ><input type="text" style="text-align:center;" id="cardNo" name="cardNo" value="<%=b.getCardNo()%>" size="4" readonly></td>
			<td align="center" ><input type="text" style="text-align:center;" id="name" name="name" value="<%=b.getName()%>" size="4" readonly></td>
			<td align="center" >
			<input type="text" id="branchId" name="branchId" value="<%=l.getBranch().getBranchId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="branchName" name="branchName" value="<%=l.getBranch().getBranchName()%>" readonly>
			</td>
			<td align="center" >
			<input type="text"  id="bookId" name="bookId" value="<%=l.getBook().getBookId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="title" name="title" value="<%=l.getBook().getTitle()%>" readonly></td>
			<td align="center" ><input type="text" style="text-align:center;" id="dateOut" name="dateOut" value="<%=l.getDateOut()%>"  readonly></td>
			<td align="center" ><input type="text" style="text-align:center;" id="dueDate" name="dueDate" value="<%=l.getDueDate()%>" readonly></td>
			<td align="center">
			<input type="submit" value="Extend">
			</td>
			
			</form>
			</tr>
	  <%}
	} %>
	
</table>

<br/>
<a href="admin.html">Previous Page</a><br/>





</body>
</html>