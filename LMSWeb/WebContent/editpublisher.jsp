<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	AdministratorService admin = new AdministratorService();
int publisherId = new Integer(request.getParameter("publisherId"));
	
	Publisher pub = new Publisher();
	pub = (Publisher)admin.getServiceById("pubById",publisherId);
%>

</head>
<body>
<h1>Edit Publisher</h1>
	<h2>Edit Publisher Details</h2>
	<form action="editPublisher" method="post">
		<input type="text" name="publisherId" value="<%=publisherId%>" style="display:none">
		Enter Publisher Name: <input type="text" name="publisherName" value="<%=pub.getPublisherName()%>"><br/>
		Enter Publisher Address: <input type="text" name="publisherAddr" value="<%if(pub.getPublisherAddress()!=null)out.println(pub.getPublisherAddress());%>"><br/>
		Enter Publisher Phone: <input type="text" name="publisherPhone" value="<%if(pub.getPublisherPhone()!=null)out.println(pub.getPublisherPhone());%>"><br/>
		 
		<input type="submit" value="Submit">
	
	</form>
<a href="viewpublisher.jsp">Previous Page</a><br/>
</body>
</html>