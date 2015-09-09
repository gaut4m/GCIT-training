<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.domain.Loans"%>
    <%@ page import="com.gcit.lms.domain.Branch"%>
    <%@ page import="com.gcit.lms.domain.Borrower"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
    Borrower br =new Borrower();
if(null == session.getAttribute("cardNo")){
	  // User is not logged in.
	response.sendRedirect(request.getContextPath() + "/borrower.jsp");
	
	}else{
	  // User IS logged in.
	  br.setCardNo((Integer)session.getAttribute("cardNo"));
	  br.setName((String)session.getAttribute("name"));
	  
		
	}
%>
<%JDBC jdbc = new JDBC();
	List<Branch> branches = new ArrayList<Branch>();
	branches = jdbc.getBranch(null);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function myFunction() {
    var branch = document.getElementById("branchId").value;
   
    
   
if(branch == "select"){
	
	return false;
    
}
   alert(branch);
}
</script>
</head>
<body>


<form action="selectBranch"  onsubmit="myFunction()" method="post">
Select Branch:
<select id="branchId" name="branchId" >
<option value="select"> ----Select----</option>
<%for(Branch a: branches){ %>
	<option value="<%=a.getBranchId()+"."+a.getBranchName() %>" ><%=a.getBranchName() %></option>	
<%} %>
</select>
<input type="submit" value="Select" />
</form>
<a href="bindex.jsp">Previous Page</a><br/>
</body>
</html>