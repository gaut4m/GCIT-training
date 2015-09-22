<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.gcit.lms.domain.Author"%>
 <%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
AdministratorService admin = new AdministratorService();


	List<Author> authors = new ArrayList<Author>();

	authors = (List<Author>)admin.getService("authors","",-1,-1);
	
	List<Publisher> pubs = new ArrayList<Publisher>();
	pubs = (List<Publisher>)admin.getService("pubs", "", -1,-1);
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

<form action="#" method="post" id="demoForm" class="demoForm">
    <fieldset>
        <legend>Demo: Get Selected Options</legend>
    
        <p>
            <select name="demoSel[]" id="demoSel" size="4" multiple>
                <option value="scroll">Scrolling Divs JavaScript</option>
                <option value="tooltip">JavaScript Tooltips</option>
                <option value="con_scroll">Continuous Scroller</option>
                <option value="banner">Rotating Banner JavaScript</option>
                <option value="random_img">Random Image PHP</option>
                <option value="form_builder">PHP Form Generator</option>
                <option value="table_class">PHP Table Class</option>
                <option value="order_forms">PHP Order Forms</option>
            </select>
            <input type="submit" value="Submit" />
            <textarea name="display" id="display" placeholder="view select list value(s) onchange" cols="20" rows="4" readonly></textarea>
        </p>
        
    </fieldset>
</form>

<form action="addBook" onSubmit="return validateForm()" method="post">



<table border=1 width=400px align="center">
<col width=200px>
<col width=200px>
<tr>

<td>Select Author:</td><td>
<select id="authorId" name="authorId" multiple>
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
	<option value="<%=p.getPublisherId() %>" ><%=p.getPublisherName() %></option>	
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

<script type="text/javascript">
(function() {
    
    function getSelectedOptions(sel, fn) {
        var opts = [], opt;
        
        // loop through options in select list
        for (var i=0, len=sel.options.length; i<len; i++) {
            opt = sel.options[i];
            
            // check if selected
            if ( opt.selected ) {
                // add to array of option elements to return from this function
                opts.push(opt);
                
                // invoke optional callback function if provided
                if (fn) {
                    fn(opt);
                }
            }
        }
        
        // return array containing references to selected option elements
        return opts;
    }
    
    // anonymous function onchange for select list with id demoSel
    document.getElementById('authorId').onchange = function(e) {
        // get reference to display textarea
        var display = document.getElementById('display');
        display.innerHTML = ''; // reset
        
        // callback fn handles selected options
        getSelectedOptions(this, callback);
        
        // remove ', ' at end of string
        var str = display.innerHTML.slice(0, -2);
        display.innerHTML = str;
    };
    
    document.getElementById('demoForm').onsubmit = function(e) {
        // reference to select list using this keyword and form elements collection
        // no callback function used this time
        var opts = getSelectedOptions( this.elements['demoSel[]'] );
        
        alert( 'The number of options selected is: ' + opts.length ); //  number of selected options
        
        return false; // don't return online form
    };
    
    // example callback function (selected options passed one by one)
    function callback(opt) {
        // can access properties of opt, such as...
        //alert( opt.value )
        //alert( opt.text )
        //alert( opt.form )
        
        // display in textarea for this example
        var display = document.getElementById('display');
        display.innerHTML += opt.value + ', ';
    }
    
}());

</script>
</html>