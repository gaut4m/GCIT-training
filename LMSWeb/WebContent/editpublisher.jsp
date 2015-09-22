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

<div class="modal-body">
	<form action="editPublisher" method="post">
		<input type="text" name="publisherId" value="<%=publisherId%>" style="display:none">
		<table>
		<tr>
		<td>Enter Publisher Name:</td><td> <input type="text" name="publisherName" value="<%=pub.getPublisherName()%>"><br/>
		</td></tr><tr><td>Enter Publisher Address:</td><td> <input type="text" name="publisherAddr" value="<%if(pub.getPublisherAddress()!=null)out.println(pub.getPublisherAddress());%>"><br/>
		</td></tr><tr><td>Enter Publisher Phone:</td><td> <input type="text" name="publisherPhone" value="<%if(pub.getPublisherPhone()!=null)out.println(pub.getPublisherPhone());%>"><br/>
		</td></tr><tr><td></td><td><input type="submit" value="Submit"></td>
		</tr>
		</table>
	</form>
</div>