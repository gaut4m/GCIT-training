<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.gcit.lms.domain.Loans"%>
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
<%JDBC jdbc = new JDBC();
	
	
	 List<Loans> loans = new ArrayList<Loans>();
		 loans = jdbc.getLoans(br.getCardNo());
		 
	
	 
	 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

${message}



<h2> View Borrower Due Dates</h2>
<table>
	<tr>
	
	<th>Branch Name </th>
	<th>Book Title</th>
	<th>Check out Date</th>
	<th>Due Date </th>
	<th>Extend</th>
	</tr>
	<%
		for(Loans l: loans){%>
		<tr>
			<form  action="returnBook" id="myform"  method="post">
			
			<td align="center" >
			<input type="text" id="cardNo" name="cardNo" value="<%=br.getCardNo()%>" style="display:none">
			<input type="text" id="branchId" name="branchId" value="<%=l.getBranch().getBranchId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="branchName" name="branchName" value="<%=l.getBranch().getBranchName()%>" readonly>
			</td>
			<td align="center" >
			<input type="text"  id="bookId" name="bookId" value="<%=l.getBook().getBookId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="title" name="title" value="<%=l.getBook().getTitle()%>" readonly></td>
			<td align="center" ><input type="text" style="text-align:center;" id="dateOut" name="dateOut" value="<%=l.getDateOut()%>"  readonly></td>
			<td align="center" ><input type="text" style="text-align:center;" id="dueDate" name="dueDate" value="<%=l.getDueDate()%>" readonly></td>
			<td align="center">
			<input type="submit" value="Return">
			</td>
			
			</form>
			</tr>
	  <%}%>
	
</table>
<a href="bindex.jsp">Previous Page</a><br/>

</body>
</html>