package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministratorService;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet({"/PageServlet","/searchAuthors", "/pageAuthors","/searchPublishers", "/pagePublishers"})
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		System.out.println("request url:"+reqUrl);
		String forwardPath = "/admin.html";
		int i = 0;
		switch (reqUrl) {	
	case "/searchAuthors":
		searchAuthors(request, response);
		break;
		
	case "/pageAuthors":
		pageAuthors(request, response);
		break;
	case "/searchPublishers":
		searchPublishers(request, response);
		break;
		
	case "/pagePublishers":
		pagePublishers(request, response);
		break;
		
		}
		
	}
	
	
	
	private void pagePublishers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String searchString = request.getParameter("searchString");
		AdministratorService service = new AdministratorService();
		List<Publisher> pubs = new ArrayList<Publisher>();
		try {
			pubs = (List<Publisher>)service.getService("pubs",searchString,pageNo, 10);
			
			StringBuilder str = new StringBuilder();
			
				str.append("<thead><tr><th>Publisher Name</th><th>Publisher Address</th><th>Publisher Phone</th>"
						+ "<th> List of Published Books</th><th>Edit Publisher</th>"
						+ "<th>Delete Publisher</th></tr></thead>");
				
				for(Publisher p: pubs){ 
				str.append("<tr><td align='center' ><input type='text'  id='publisherId' name='publisherId'"
						+ " value='"+p.getPublisherId()+"' style='display:none'>"
						+p.getPublisherName()+"</td><td align='center'>"+p.getPublisherAddress() 
						+"</td><td align='center'>"+p.getPublisherPhone() 
						+"</td><td align='center'><select>");
				
				for(Book b:p.getBooks()){
						str.append("<option>"+b.getTitle() +"</option>");
						} 
					str.append("</select></td><td align='center'><button type='button' class='btn btn-sm btn-info' "
							+ "data-toggle='modal' data-target='#myModal1' href='editpublisher.jsp?publisherId="
							+p.getPublisherId() +"'>EDIT</button></td><td align='center'><button type='button' "
							+ "class='btn btn-sm btn-danger' onclick='javascript:location.href='deletePublisher?publisherId="
							+p.getPublisherId()+"''>DELETE</button></td></tr>");
				} 
				
			
				
			response.getWriter().append(str);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void searchPublishers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String searchString = request.getParameter("searchString");
		AdministratorService service = new AdministratorService();
		List<Publisher> pubs = new ArrayList<Publisher>();
		try {
			pubs = (List<Publisher>)service.getService("pubs",searchString,1, 10);
			int count = service.getCount("pubs", searchString);
			
			int pages = 1;

			if(count%10==0){
				pages = count/10;
			}else{
				pages = (count/10)+1;
			}
			StringBuilder str = new StringBuilder();
			str.append("<nav><ul class='pagination'>");
			for(int i=1;i<=pages;i++){
			str.append("<li><a href='javascript:pagePublishers("+i+");'>"+i+"</a></li>");
			    }
				str.append("</ul></nav><div class='table-responsive'><table class='table table-striped' id='publisherDetails'>"
						+ "<thead><tr><th>Publisher Name</th><th>Publisher Address</th><th>Publisher Phone</th>"
						+ "<th> List of Published Books</th><th>Edit Publisher</th>"
						+ "<th>Delete Publisher</th></tr></thead>");
				
				for(Publisher p: pubs){ 
				str.append("<tr><td align='center' ><input type='text'  id='publisherId' name='publisherId'"
						+ " value='"+p.getPublisherId()+"' style='display:none'>"
						+p.getPublisherName()+"</td><td align='center'>"+p.getPublisherAddress() 
						+"</td><td align='center'>"+p.getPublisherPhone() 
						+"</td><td align='center'><select>");
				
				for(Book b:p.getBooks()){
						str.append("<option>"+b.getTitle() +"</option>");
						} 
					str.append("</select></td><td align='center'><button type='button' class='btn btn-sm btn-info' "
							+ "data-toggle='modal' data-target='#myModal1' href='editpublisher.jsp?publisherId="
							+p.getPublisherId() +"'>EDIT</button></td><td align='center'><button type='button' "
							+ "class='btn btn-sm btn-danger' onclick='javascript:location.href='deletePublisher?publisherId="
							+p.getPublisherId()+"''>DELETE</button></td></tr>");
				} 
				
			str.append("</table></div></div>");
				
			response.getWriter().append(str);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pageAuthors(HttpServletRequest request,
			HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String searchString = request.getParameter("searchString");
		AdministratorService service = new AdministratorService();
		List<Author> authors = new ArrayList<Author>();
		try {
			authors = (List<Author>)service.getService("authors",searchString,pageNo, 10);
			StringBuilder str = new StringBuilder();
			str.append("<thead><tr><th>Author ID</th><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr></thead>");
			for(Author a: authors){ 
				str.append("<tr><td><input type='text'  id='id' name='id' value='"+a.getAuthorId()+"' style='display:none'>"
			+a.getAuthorName()+"</td><td ><select>");
				for(Book b:a.getBooks()){
								str.append("<option>"+b.getTitle() +"</option>");
				                }
				str.append("</select></td><td ><button type='button' class='btn btn-sm btn-info' "
						+ "onclick='update("+a.getAuthorId() +",'"+a.getAuthorName()+"')'  "
								+ "data-toggle='modal'>EDIT</button></td><td >"
								+ "<button type='button' class='btn btn-sm btn-danger'"
								+ "  onclick='javascript:location.href='deleteAuthor?authorId="+a.getAuthorId()
								+"'>DELETE</button></td></tr>");
					}
			response.getWriter().append(str);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void searchAuthors(HttpServletRequest request, HttpServletResponse response) {
		String searchString = request.getParameter("searchString");
		AdministratorService service = new AdministratorService();
		List<Author> authors = new ArrayList<Author>();
		try {
			authors = (List<Author>)service.getService("authors",searchString,1, 10);
			
			int count = service.getCount("authors", searchString);
			
			int pages = 1;

			if(count%10==0){
				pages = count/10;
			}else{
				pages = (count/10)+1;
			}
			StringBuilder str = new StringBuilder();
			str.append("<nav><ul class='pagination'>");
			 
			for(int i=1;i<=pages;i++){
			    str.append("<li><a href='javascript:pageAuthors("+i+");'>"+i+"</a></li>");
			    }
			
			
			str.append("</ul></nav><div class='table-responsive'>"
					+ "<table class='table table-striped' id='authorsTable'>"
					+ "<thead><tr><th>Author ID</th><th>Author Name</th><th>Edit Author</th>"
					+ "<th>Delete Author</th></tr></thead>");
			for(Author a: authors){ 
				str.append("<tr><td><input type='text'  id='id' name='id' value='"+a.getAuthorId()+"' style='display:none'>"
			+a.getAuthorName()+"</td><td ><select>");
				for(Book b:a.getBooks()){
								str.append("<option>"+b.getTitle() +"</option>");
				                }
				str.append("</select></td><td ><button type='button' class='btn btn-sm btn-info' "
						+ "onclick='update("+a.getAuthorId() +",'"+a.getAuthorName()+"')'  "
								+ "data-toggle='modal'>EDIT</button></td><td >"
								+ "<button type='button' class='btn btn-sm btn-danger'"
								+ "  onclick='javascript:location.href='deleteAuthor?authorId="+a.getAuthorId()
								+"'>DELETE</button></td></tr>");
					}
			str.append("</table></div></div>");
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
