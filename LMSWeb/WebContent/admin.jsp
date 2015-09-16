<!DOCTYPE html>
<!-- saved from url=(0047)http://getbootstrap.com/examples/justified-nav/ -->
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

    <!-- Custom styles for this template -->
    <link href="./template_files/justified-nav.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./template_files/ie-emulation-modes-warning.js"></script><style type="text/css"></style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <!-- The justified navigation menu is meant for single line per list item.
           Multiple lines will require custom code not provided by Bootstrap. -->
      <div class="masthead">
        <h3 class="text-muted">GCIT Library Management System</h3>
        <nav>
          <ul class="nav nav-justified">
            <li ><a href="index.jsp">Home</a></li>
            <li class="active"><a  href="admin.jsp">Administrator</a></li>
            <li><a href="borrower.jsp">Borrower</a></li>
            <li><a href="librarian.jsp">Librarian</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Contact</a></li>
          </ul>
        </nav>
      </div>

      <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="admin.jsp">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="author.jsp">Add/Update/Delete/View Authors</a></li>
            <li><a href="book.jsp">Add/Update/Delete/View Books</a></li>
            <li><a href="publisher.jsp">Add/Update/Delete/View Publishers</a></li>
            <li><a href="users.jsp">Add/Update/Delete/View Borrowers</a></li>
            <li><a href="branchdetails.jsp">Add/Update/Delete/View Branches</a></li>
            <li><a href="genre.jsp">Add/Update/Delete/View Genres</a></li>
          </ul>
        </div>
        
      </div>
    </div>

      <!-- Site footer -->
      <footer class="footer">
        <p>© Company 2014</p>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./template_files/ie10-viewport-bug-workaround.js"></script>
  

</body></html>