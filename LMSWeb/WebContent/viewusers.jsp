<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gcit.lms.domain.Borrower"%>

<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.domain.Branch" %>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	AdministratorService admin = new AdministratorService();
	
	
	List<Borrower> br = new ArrayList<Borrower>();
	br = (List<Borrower>)admin.getService("users");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
${message}
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>

</head>
<body>
<h2> View Existing Borrowers</h2>
<table>
	<tr>
	
	<th>Name</th>
	<th>Address</th>
	<th>Phone</th>
	
	<th>Edit Borrower</th>
	<th>Delete Borrower</th>
	</tr>
	<%for(Borrower b: br){ %>
		<tr>
			
			<td align="center" ><input type="text"  id="cardNo" name="cardNo" value="<%=b.getCardNo()%>" style="display:none">
			<%=b.getName()%></td>
			<td align="center"><%=b.getAddress() %></td>
			<td align="center"><%=b.getPhone() %></td>
			<td align="center"><button type="button" onclick="javascript:location.href='edituser.jsp?cardNo=<%=b.getCardNo() %>'">EDIT</button></td>
			<td align="center"><button type="button" onclick="javascript:location.href='deleteUser?cardNo=<%=b.getCardNo()%>'">DELETE</button></td>
			
			</tr>
	<%} %>
	
</table>
<br/>
<a href="users.jsp">Previous Page</a><br/>
</body>
</html>