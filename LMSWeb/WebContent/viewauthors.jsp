<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%JDBC jdbc = new JDBC();
	List<Author> authors = new ArrayList<Author>();
	authors = jdbc.getAuthors();
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
<h2> View Existing Authors</h2>
<table>
	<tr>
	<th>Author ID</th>
	<th>Author Name</th>
	<th>Edit Author</th>
	<th>Delete Author</th>
	</tr>
	<%for(Author a: authors){ %>
		<tr>
			<form  action="editAuthor" id="myform" onsubmit="return validateForm(<%=a.getAuthorId() %>)" method="post">
			<td align="center" ><input type="text" style="text-align:center;" id="authorId" name="authorId" value="<%=a.getAuthorId() %>" size="4" readonly></td>
			<td align="center"><%=a.getAuthorName() %></td>
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