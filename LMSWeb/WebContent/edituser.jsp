<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	AdministratorService admin = new AdministratorService();
int cardNo = new Integer(request.getParameter("cardNo"));
	
	Borrower b = new Borrower();
	b = (Borrower)admin.getServiceById("cardNo",cardNo);
%>

</head>
<body>
<h1>Edit Borrower</h1>
	<h2>Edit Borrower Details</h2>
	<form action="editUser" method="post">
		<input type="text" name="cardNo" value="<%=cardNo%>" style="display:none">
		Enter Borrower Name: <input type="text" name="name" value="<%=b.getName()%>"><br/>
		Enter Borrower Address: <input type="text" name="address" value="<%if(b.getAddress()!=null)out.println(b.getAddress());%>"><br/>
		Enter Borrower Phone: <input type="text" name="phone" value="<%if(b.getPhone()!=null)out.println(b.getPhone());%>"><br/>
		<input type="submit" value="Submit">
	</form>
<a href="viewusers.jsp">Previous Page</a><br/>

</body>
</html>