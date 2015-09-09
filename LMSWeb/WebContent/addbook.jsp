<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.gcit.lms.domain.Author"%>
 <%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%JDBC jdbc = new JDBC();
	List<Author> authors = new ArrayList<Author>();
	authors = jdbc.getAuthors();
	List<Publisher> pubs = new ArrayList<Publisher>();
	pubs = jdbc.getPubs();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>


<script>
function myFunction() {
    var authorName = document.getElementById("authorId").value;
    var e = document.getElementById("authorDetails");
    
   
if(authorName == "new"){
	
	if(e.style.display == 'none')
	       e.style.display = '';
    
}else{
	if(e.style.display == '')
	       e.style.display = 'none';
}
   
}
function selectPub(){
	var pub = document.getElementById("pubId").value;
	
	
  
    var e = document.getElementById("pubDetails");
    
    
    if(pub == "new"){
    	
    	if(e.style.display == 'none')
    	       e.style.display = '';
        
    }else{
    	if(e.style.display == '')
    	       e.style.display = 'none';
    }
       
	
}
function validateForm() {
	 var authorName = document.getElementById("authorId").value;
	
	    var pub = document.getElementById("pubId").value;
	    var title = document.getElementById("title").value;
	    var demo="";
	    
	   if(pub == "new")
	    	{
	    	var pubName = document.getElementById("pubName").value;
	    	var pubAddr = document.getElementById("pubAddr").value;
	    	var pubPhone = document.getElementById("pubPhone").value;
	    	if( pubName == null || pubName == "")
	    		demo += "Publisher Name should not be empty. ";
	    	if(pubAddr == null || pubAddr == "")
	    		demo+= "Publisher Address should not be empty. ";
	    	if(pubPhone == null || pubAddr == "")
	    		demo+= "Publisher Phone should not be empty. ";
	    	}
	    
	   
	    if(authorName == "select")
	    	demo =demo+ "An author should be selected. ";
	    else if(authorName == "new")
	    	{
	    	var authorName = document.getElementById("authorName").value;
	    	if( authorName == null || authorName == "")
	    		demo += "author Name should not be empty. ";
	    	
	    	}
	    
	   if(title == "" || title == null)
		   demo += "Book title should not be empty. ";
	    	
	    	
	    	document.getElementById("demo").innerHTML = demo;
	   
	    	if(demo != "")
	   			return false;
	
   
}
</script>

</head>
<body>

<p id="demo" color = "red"></p>

<h1 align="center"">Add Book</h1>
<form action="addBook" onSubmit="return validateForm()" method="post">


<table border=1 width=400px align="center">
<col width=200px>
<col width=200px>
<tr>

<td>Select Author:</td><td>
<select id="authorId" name="authorId" onChange="myFunction()" >
<option value="select"> ----Select----</option>
<option value="new" >New Author</option>
<%for(Author a: authors){ %>
	<option value="<%=a.getAuthorId()%>" ><%=a.getAuthorName() %></option>	
<%} %>
</select>
</td>
</tr>

<tr>
<td>Select Publisher:</td>
<td>
<select id="pubId" name="pubId" onChange="selectPub()">
<option value="select"> ----Select----</option>
<option value="new" >New Publisher</option>
<%for(Publisher p: pubs){ %>
	<option value="<%=p.getPubId() %>" ><%=p.getPubName() %></option>	
<%} %>
</select>
</td>

</tr>


<tr id="authorDetails" name="authorDetails" style="display:none">
<td>
Author Name:</td><td > <input type="text" id="authorName" name="authorName" value="">
</td></tr>

<tbody id="pubDetails" style="display:none">


<tr>
<td>Publisher Name:</td>
<td><input type="text" id="pubName" name="pubName" value=""></td>
</tr>

<tr>
<td>Publisher Address:</td>
<td><input type="text" id="pubAddr" name="pubAddr" value=""></td>
</tr>
<tr>
<td>Publisher Phone:</td>
<td><input type="text" id="pubPhone" name="pubPhone" value=""></td>
</tr>
</tbody>
<tr>
<td>Book Name:</td>
<td><input type="text" id="title" name="title" value=""></td>
</tr>
<tr>
<td>
</td><td><input type="submit" value="Submit"></td>
</tr>
</table>

</form>
<br/>
<a href="book.jsp">Previous Page</a><br/>
</body>
</html>