<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ page import="com.gcit.lms.domain.Loans"%>
    <%@ page import="com.gcit.lms.domain.Branch"%>
    <%@ page import="com.gcit.lms.domain.Author"%>
    <%@ page import="com.gcit.lms.domain.BCopies"%>
     <%@ page import="com.gcit.lms.domain.Borrower"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="com.gcit.lms.service.LibrarianService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
    Borrower br =new Borrower();
    Branch branch = new Branch();
if(null == session.getAttribute("cardNo")){
	  // User is not logged in.
	response.sendRedirect(request.getContextPath() + "/borrower.jsp");
	
	}else{
	  // User IS logged in.
	  br.setCardNo((Integer)session.getAttribute("cardNo"));
	  br.setName((String)session.getAttribute("name"));
	  branch.setBranchId((Integer)request.getAttribute("branchId"));
	  branch.setBranchName((String)request.getAttribute("branchName"));
	  
		
	}
%>
<% 
AdministratorService admin = new AdministratorService();
LibrarianService lib =new LibrarianService();
List<BCopies> copies = new ArrayList<BCopies>();

copies= lib.getAvailableBooks(branch.getBranchId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>

<body>
${message}
<h2>Check Out books From <%=branch.getBranchName() %> branch</h2>

<table>
	<tr>
	<th>Book Title</th>
	<th>Authors</th>
	<th>Publisher</th>
	<th>Available Copies</th>
	</tr>
	<%for(BCopies b: copies){ %>
		<tr>
			<form  action="checkOut" id="myform"  method="post">
			<td align="center" >
			<input type="text" id="cardNo" name="cardNo" value="<%=br.getCardNo()%>" style="display:none">
			<input type="text" id="branchId" name="branchId" value="<%=branch.getBranchId()%>" style="display:none">
			<input type="text" id="bookId" name="bookId" value="<%=b.getBook().getBookId()%>" style="display:none">
			<input type="text" style="text-align:center;" id="title" name="title" value="<%=b.getBook().getTitle()%>" readonly></td>
			<td align="center"><select>
			<%for(Author a:b.getBook().getAuthors()){
				%>
				
				<option><%=a.getAuthorName()%></option>
				
			<%} %>
			
			</select>
			</td>
			<td align="center">
			<input type="text" style="text-align:center;" id="title" name="title" value="<%=b.getBook().getPublisher().getPublisherName()%>" readonly></td>
			
			</td>
			<td align="center"><input type="text" style="text-align:center;" id="noOfCopies" name="noOfCopies" value="<%=b.getNoofCopies()%>" size="3" readonly></td>
			<td align="center"><input type="submit" value="Check Out"></td>
			</form>
			</tr>
	<%} %>
	
</table>



</body>
</html>