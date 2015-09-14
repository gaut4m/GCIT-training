<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>

<%
	AdministratorService admin = new AdministratorService();
	
	
	List<Genre> genres = new ArrayList<Genre>();
	genres = (List<Genre>)admin.getService("genres");
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
    
    var e = document.getElementById("genreDetails"+a);
    
   

	
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
<h2> View Existing Genres</h2>
<table>
	<tr>
	
	<th>Genre Name</th>
	<th>Books</th>
	<th>Update Genre</th>
	<th> Delete Genre</th>
	</tr>
	<%for(Genre g: genres){ %>
		<tr>
			<form  action="editGenre" id="myform"  method="post">
			<td align="center" ><input type="text"  id="genreId" name="genreId" value="<%=g.getGenreId()%>" style="display:none">
			<%=g.getGenreName()%></td>
			<td align="center">
			<select>
			<%
				if(null != g.getBooks()){for(Book b:g.getBooks()){
				%>
				<option><%=b.getTitle() %></option>
				
			<% }} %>
			</select>
			</td>
			<td align="center"><button type="button" onclick="update(<%=g.getGenreId() %>)">EDIT</button></td>
			
			<td align="center"><button type="button" onclick="javascript:location.href='deleteGenre?genreId=<%=g.getGenreId()%>'">DELETE</button></td>
			<tbody id="genreDetails<%=g.getGenreId() %>" name="genreDetails<%=g.getGenreId() %>"  style="display:none">
			
			<td align="center">
			Update Name:
			</td>
			<td align="center">
			
			<input type="text" id="genreName" name="genreName" value="">
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
<a href="genre.jsp">Previous Page</a><br/>
</body>
</html>