
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	AdministratorService admin = new AdministratorService();
	
	
	List<Publisher> pubs = new ArrayList<Publisher>();
	pubs = (List<Publisher>)admin.getService("pubs");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
${message}
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>

</head>
<body>
<h2> View Existing Publishers</h2>
<table>
	<tr>
	
	<th>Publisher Name</th>
	<th>Publisher Address</th>
	<th>Publisher Phone</th>
	<th> List of Published Books</th>
	<th>Edit Publisher</th>
	<th>Delete Publisher</th>
	</tr>
	<%for(Publisher p: pubs){ %>
		<tr>
			
			<td align="center" ><input type="text"  id="publisherId" name="publisherId" value="<%=p.getPublisherId()%>" style="display:none">
			<%=p.getPublisherName()%></td>
			<td align="center"><%=p.getPublisherAddress() %></td>
			<td align="center"><%=p.getPublisherPhone() %></td>
			<td align="center">
			<select>
			<%for(Book b:p.getBooks()){
				%>
				<option><%=b.getTitle() %></option>
				
			<% } %>
			</select>
			</td>
			<td align="center"><button type="button" onclick="javascript:location.href='editpublisher.jsp?publisherId=<%=p.getPublisherId() %>'">EDIT</button></td>
			<td align="center"><button type="button" onclick="javascript:location.href='deletePublisher?publisherId=<%=p.getPublisherId()%>'">DELETE</button></td>
			<td id="authorDetails<%=p.getPublisherId()%>" name="authorDetails<%=p.getPublisherId() %>"  style="display:none">
			
			</tr>
	<%} %>
	
</table>
<br/>
<a href="publisher.jsp">Previous Page</a><br/>
</body>
</html>