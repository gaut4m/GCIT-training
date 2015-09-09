package com.gcit.lms.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.database.JDBC;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.domain.Book;;


/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/addAuthor", "/editAuthor","/addBook","/deleteAuthor","/deleteBook","/editBook","/extendDate"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdminServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		System.out.println("request url:"+reqUrl);
		String forwardPath = "/admin.html";
		int i = 0;
		switch (reqUrl) {
		
		case "/deleteAuthor":
			//TODO Call Delete on jDBC
			i = deleteAuthor(request);
			if(i > 0)
				request.setAttribute("message", "Author with Id"+request.getParameter("authorId")+" succesfully Deleted.");
			else
				request.setAttribute("message", "Author cannot be Deleted. Still Has dependancies.");
			forwardPath = "/viewauthors.jsp";
			break;
		case "/deleteBook":
			i = deleteBook(request);
			if(i > 0)
				request.setAttribute("message", "book with Id"+request.getParameter("bookId")+" succesfully Deleted.");
			else
				request.setAttribute("message", "book cannot be Deleted. Still Has dependancies.");
			forwardPath = "/viewbooks.jsp";
			break;
		
		}
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	private int deleteBook(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int bookId = new Integer(request.getParameter("bookId"));
		Book book = new Book();
		book.setBookId(bookId);
		
		JDBC jdbc = new JDBC();
		return jdbc.deleteBook(book);
		
	}

	private int updateAuthor(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int authorId = new Integer(request.getParameter("authorId"));
		String authorName = request.getParameter("authorName");
		System.out.println(authorName);
		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName);
		
		JDBC jdbc = new JDBC();
		return jdbc.updateAuthor(author);
		
	}

	private int deleteAuthor(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		int authorId = new Integer(request.getParameter("authorId"));
		Author author = new Author();
		author.setAuthorId(authorId);
		
		JDBC jdbc = new JDBC();
		return jdbc.deleteAuthor(author);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		int i=0;
		String forwardPath = "/admin.html";
		switch (reqUrl) {
		case "/extendDate":
			i = extendDate(request);
			System.out.println("extendDate"+i);
			if(i > 0)
				request.setAttribute("message", "Due Date extended Sucessfully");
				else
					request.setAttribute("message", "Due Date not extended, Opertation Failed!!");
			forwardPath = "/date.jsp";
			
			break;
		case "/addAuthor":
			i=addAuthor(request);
			if(i != 0)
				request.setAttribute("message", "Author Added Sucessfully");
				else
					request.setAttribute("message", "Author was not Added, Opertation Failed!!");
			forwardPath = "/author.jsp";
			break;
		case "/addBook":
			i=addBook(request);
			if(i != 0)
			request.setAttribute("message", "Book Added Sucessfully");
			else
				request.setAttribute("message", "Book was not Added, Opertation Failed!!");
			forwardPath = "/book.jsp";
			break;
		case "/editAuthor":
			//TODO Call Edit on JDBC
			i = updateAuthor(request);
			if(i == 1)
				request.setAttribute("message", "Author with Id"+request.getParameter("authorId")+" succesfully updated.");
			else
				request.setAttribute("message", "Author name not updated. Try Again!!");
			forwardPath = "/viewauthors.jsp";
			
			break;
		case "/editBook":
			i = updateBook(request);
			if(i == 1)
				request.setAttribute("message", "Book with Id"+request.getParameter("bookId")+" succesfully updated.");
			else
				request.setAttribute("message", "book title not updated. Try Again!!");
			forwardPath = "/viewbooks.jsp";
			
			break;

		default:
			break;
		}

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	private int extendDate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int bookId = new Integer(request.getParameter("bookId"));
		int branchId = new Integer(request.getParameter("branchId"));
		int cardNo = new Integer(request.getParameter("cardNo"));
		System.out.println("method");
		
		JDBC jdbc = new JDBC();
		return jdbc.extendDate(bookId,branchId,cardNo);
		
		
		
	}

	private int updateBook(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int bookId = new Integer(request.getParameter("bookId"));
		String title = request.getParameter("title");
		System.out.println(title);
		Book book = new Book();
		book.setBookId(bookId);
		book.setTitle(title);
		
		JDBC jdbc = new JDBC();
		return jdbc.updateBook(book);
		
		
	}

	private int addBook(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String selectAuthor = request.getParameter("authorId");
		String selectPub = request.getParameter("pubId");
		String authorName=null;
		String pubName=null;
		String pubAddr = null;
		String pubPhone=null;
		int pubId =0;
		int authorId =0;
		JDBC jdbc = new JDBC();
		Publisher pub = new Publisher();
		Author author = new Author();
		System.out.println("Author:"+selectAuthor);
		System.out.println("pub:"+selectPub);
		if(selectAuthor.equals("new"))
		{
			System.out.println("new author");
			author.setAuthorName(request.getParameter("authorName"));
			authorId = jdbc.addAuthor(author);
			System.out.println(authorId);
			if(authorId ==0)
				return 0;
			
			
		}
		else
		{
			authorId = new Integer( selectAuthor);
		}
		if(selectPub.equals("new"))
		{
			
			System.out.println("new pub");
			pubName = request.getParameter("pubName");
			pubAddr = request.getParameter("pubAddr");
			pubPhone = request.getParameter("pubPhone");
			pub.setPubName(pubName);
			pub.setPubAddress(pubAddr);
			pub.setPubPhone(pubPhone);
			pubId = jdbc.addPublisher(pub);
			System.out.println(pubId);
			if(pubId ==0)
				return 0;
			
		}
		else if(selectPub.equals("select"))
		{
			pubId=0;
		}
		else
			pubId=new Integer(selectPub);
		
		author.setAuthorId(authorId);
		pub.setPubId(pubId);
		
		Book book = new Book();
		book.setTitle(request.getParameter("title"));
		
		//return jdbc.addBook(book, author, pub);
		return 0;
	}

	private int addAuthor(HttpServletRequest request) {
		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);
		
		JDBC jdbc = new JDBC();
		return jdbc.addAuthor(author);
	}

}
