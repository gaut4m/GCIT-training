<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>

<%
	AdministratorService admin = new AdministratorService();
	
	
	List<Author> authors = new ArrayList<Author>();
	authors = (List<Author>)admin.getService("authors");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
${message}
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
<script>
function update(a)
{
    
    var e = document.getElementById("authorDetails"+a);
    
   

	
	if(e.style.display == 'none')
	       e.style.display = '';
    else
	       e.style.display = 'none';

   
	
	}
function validateForm(a)
{
	
	
	
	
	
	}
</script>
</head>
<body>
<h2> View Existing Authorss</h2>
<table>
	<tr>
	
	<th>Author Name</th>
	<th>Books Written</th>
	<th>Update Author</th>
	<th> Delete Author</th>
	</tr>
	<%for(Author a: authors){ %>
		<tr>
			<form  action="editAuthor" id="myform"  method="post">
			<td align="center" ><input type="text"  id="authorId" name="authorId" value="<%=a.getAuthorId()%>" style="display:none">
			<%=a.getAuthorName()%></td>
			<td align="center">
			<select>
			<%for(Book b:a.getBooks()){
				%>
				<option><%=b.getTitle() %></option>
				
			<% } %>
			</select>
			</td>
			<td align="center"><button type="button" onclick="update(<%=a.getAuthorId() %>)">EDIT</button></td>
			
			<td align="center"><button type="button" onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td>
			<tbody id="authorDetails<%=a.getAuthorId() %>" name="authorDetails<%=a.getAuthorId() %>"  style="display:none">
			
			<td align="center">
			Update Name:
			</td>
			<td align="center">
			
			<input type="text" id="authorName" name="authorName" value="">
			</td>
			<td align="center">
			<input type="submit" value="Submit">
			</td>
			</tbody>
			</form>
			</tr>
	<%} %>
	
</table>
<br/>
<a href="author.jsp">Previous Page</a><br/>
</body>
</html>