<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%JDBC jdbc = new JDBC();
	List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>

<script>
function update(a)
{
    
    var e = document.getElementById("bookDetails"+a);
    
   

	
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
<table>
	<tr>
	<th>book ID</th>
	<th>title</th>
	<th>Edit title</th>
	<th>Delete book</th>
	</tr>
	<%for(Book b: books){ %>
		<tr>
			<form  action="editAuthor" id="myform" onsubmit="return validateForm(<%=b.getBookId()%>)" method="post">
			<td align="center"><input type="text" style="text-align:center;" id="bookId" name="bookId" value="<%=b.getBookId() %>" size="4" readonly></td>
			<td align="center"><%=b.getTitle() %></td>
			<td align="center"><button type="button" onclick="update(<%=b.getBookId()%>)">EDIT</button></td>
			<td align="center"><button type="button" onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>'">DELETE</button></td>
			<tbody id="bookDetails<%=b.getBookId()%>" name="bookDetails<%=b.getBookId()%>"  style="display:none">
			
			<td align="center">
			Update Name:
			</td>
			<td align="center">
			
			<input type="text" id="title" name="title" value="">
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
<a href="book.jsp">Previous Page</a><br/>
</body>
</html>