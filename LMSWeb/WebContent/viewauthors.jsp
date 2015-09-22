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

	authors = (List<Author>)admin.getService("authors","",1, 10);
	
	
		 int count = admin.getCount("authors", "");
		
		int pages = 1;

		if(count%10==0){
			pages = count/10;
		}else{
			pages = (count/10)+1;
		}
	 
	
	
	
%>

<!DOCTYPE html>
<!-- saved from url=(0050)http://getbootstrap.com/examples/carousel/#contact -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./template_files/icon.ico">

    <title>GCIT LMS</title>

    <!-- Bootstrap core CSS -->
    <link href="./template_files/bootstrap.min.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./template_files/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="./template_files/carousel.css" rel="stylesheet">
  <style type="text/css"></style>
  <script>
function update(a,b)
{
    
	$('.modal-body #authorId ').val(a);
	$('.modal-body #authorName').val(b);
   // var e = document.getElementById("authorDetails"+a);
    
	//if(e.style.display == 'none')
	 //      e.style.display = '';
   // else
	   //    e.style.display = 'none';
	$('#myModal').modal('show'); 
	
}

function searchAuthors(){
	$.ajax({
		  url: "searchAuthors",
		  data: { searchString: $('#searchString').val()}
	, success: function(data){
		$('#populateAuthors').html(data);
    }});
		  
}
function pageAuthors(a){
	$.ajax({
		  url: "pageAuthors",
		  data: { searchString: $('#searchString').val(),pageNo:a}
		})
		  .done(function( data ) {
		    $('#authorsTable').html(data);
		  });
}

function validateForm(a)
{}
</script>
  </head>
<!-- NAVBAR
================================================== -->
  <body>
  
    <div class="navbar-wrapper">
      <div class="container">

         <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="index.jsp">GCIT LMS</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                  <li ><a href="index.jsp">Home</a></li>
           <li class="active" class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="author.jsp">Author Management</a></li>
            <li><a href="book.jsp">Book Management</a></li>
            <li><a href="publisher.jsp">Publisher Management</a></li>
            <li><a href="users.jsp">Borrower Management</a></li>
            <li><a href="branchdetails.jsp">Branch Management</a></li>
            <li><a href="genre.jsp">Genre Management</a></li>
                  </ul>
                </li>
            <li><a href="borrower.jsp">Borrower</a></li>
            <li><a href="librarian.jsp">Librarian</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Contact</a></li>
              </ul>
            </div>
          </div>
        </nav>

      </div>
    </div>

<br>
<br>
<br>
<br>
<div class="container">
<div class="container-fluid">
      <div class="row">
      
        <div class="col-sm-3 col-md-2 sidebar">
        <br>
          <ul class="nav nav-sidebar">
            <li ><a href="author.jsp">Overview <span class="sr-only">(current)</span></a></li>
            <li ><a href="addauthor.jsp">Add Author</a></li>
            <li class="active"><a href="viewauthors.jsp">View Authors</a></li>
          </ul>
        </div>
        <div class="col-sm-7  col-md-10  main">
        
		<input type="text" class="col-md-8" id="searchString"
		placeholder="Enter Author name to search">
		<input type="button" value="Search!" onclick="javascript:searchAuthors();">

          <h1 class="sub-header">View Authors</h1>
			 ${message}
			 
			 <div id="populateAuthors">
			 
			 <nav>
  	<ul class="pagination">
  		<% for(int i=1;i<=pages;i++){%>
    <li><a href="javascript:pageAuthors(<%=i%>);"><%=i%></a></li>
    <%} %>
  </ul>
</nav>
           <div class="table-responsive">
            <table class="table table-striped" id="authorsTable">

	<thead>
	<tr>
	
	<th>Author Name</th>
	<th>Books Written</th>
	<th>Update Author</th>
	<th> Delete Author</th>
	</tr>
	</thead>
	<%for(Author a: authors){ %>
		<tr>
			
			<td  ><input type="text"  id="id" name="id" value="<%=a.getAuthorId()%>" style="display:none">
			<%=a.getAuthorName()%></td>
			<td >
			<select>
			<%for(Book b:a.getBooks()){
				%>
				<option><%=b.getTitle() %></option>
				
			<% } %>
			</select>
			</td>
			<td ><button type="button" class="btn btn-sm btn-info" onclick="update(<%=a.getAuthorId() %>,'<%=a.getAuthorName()%>')"  data-toggle="modal">EDIT</button></td>
			
			<td ><button type="button" class="btn btn-sm btn-danger"  onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td>
			
			</tr>
	<%} %>
	
</table>
</div>
</div>
          </div>
        
      </div>
    </div>

    </div><!-- /.container -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"> Update Author Name</h4>
      </div>
      <div class="modal-body">
      		<form  action="editAuthor" id="myform"  method="post">
			<input type="text"  id="authorId" name="authorId" value="" style="display:none">
			<input type="text" id="authorName" name="authorName" value="">
			<input type="submit" value="Submit">
			</form>
      </div>
    </div>
  </div>
</div>


	 <!-- FOOTER -->
      <footer class="navbar-fixed-bottom container">
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>© 2014 Company, Inc. · <a href="http://getbootstrap.com/examples/carousel/#">Privacy</a> · <a href="http://getbootstrap.com/examples/carousel/#">Terms</a></p>
      </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./template_files/jquery.min.js"></script>
    <script src="./template_files/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="./template_files/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./template_files/ie10-viewport-bug-workaround.js"></script>
  

</body></html>