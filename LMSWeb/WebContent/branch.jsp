<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>

<% JDBC jdbc = new JDBC();
List<Branch> branches = new ArrayList<Branch>();
Branch b = new Branch();

int branchId = new Integer(request.getParameter("branchId"));
b.setBranchId(branchId);
branches = jdbc.getBranch(b);
b=branches.get(0);
%>  

</head>
<body>

${message}
<h1>Branch Management</h1>
<h2>You have chosen to manage Branch: <%=b.getBranchName() %> </h2>

<p>
<a href="updatebranch.jsp?branchId=<%=b.getBranchId()%>">Update Branch Details</a><br/>
<a href="updatecopies.jsp?branchId=<%=b.getBranchId()%>">Update Book Copies</a><br/>
<a href="librarian.jsp">Previous page</a><br/>
</p>
</body>
</html>