package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.database.JDBC;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.BCopies;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.LibrarianService;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;;


/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/addAuthor", "/editAuthor","/addBook","/deleteAuthor","/deleteBook","/editBook","/extendDate","/editPublisher","/addPublisher",
	"/deletePublisher","/deleteUser","/addUser","/editUser","/deleteBranch","/editBranch","/editBranchCopy",
	"/addBranch","/addGenre","/deleteGenre","/editGenre"})
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
		case "/deletePublisher":
			i = deletePublisher(request);
			if(i > 0)
				request.setAttribute("message", "publisher with Id "+request.getParameter("publisherId")+" succesfully Deleted.");
			else
				request.setAttribute("message", "publisher cannot be Deleted. Still Has dependancies.");
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/deleteBranch":
			i = deleteBranch(request);
			if(i > 0)
				request.setAttribute("message", "branch with Id "+request.getParameter("branchId")+" succesfully Deleted.");
			else
				request.setAttribute("message", "branch cannot be Deleted. Still Has dependancies.");
			forwardPath = "/viewbranch.jsp";
			break;
			
		case "/deleteUser":
			i = deleteBorrower(request);
			if(i > 0)
				request.setAttribute("message", "Borrower with Id "+request.getParameter("cardNo")+" succesfully Deleted.");
			else
				request.setAttribute("message", "Borrower cannot be Deleted. Still has dependancies.");
			forwardPath = "/viewusers.jsp";
			break;
		case "/deleteGenre":
			i = deleteGenre(request);
			if(i > 0)
				request.setAttribute("message", "Genre with Id "+request.getParameter("genreId")+" succesfully Deleted.");
			else
				request.setAttribute("message", "Genre cannot be Deleted. Still has dependancies.");
			forwardPath = "/viewgenre.jsp";
			break;
			
		
		}
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}
	
	

	private int deleteGenre(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int genreId = new Integer(request.getParameter("genreId"));
		
		AdministratorService admin = new AdministratorService();
		
		try {
			return admin.deleteService("genre",genreId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	private int deleteBranch(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int branchId = new Integer(request.getParameter("branchId"));
		
		AdministratorService admin = new AdministratorService();
		
		try {
			return admin.deleteService("branch",branchId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private int deleteBorrower(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int cardNo = new Integer(request.getParameter("cardNo"));
		
		AdministratorService admin = new AdministratorService();
		
		try {
			return admin.deleteService("cardNo",cardNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private int deletePublisher(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int publisherId = new Integer(request.getParameter("publisherId"));
		
		AdministratorService admin = new AdministratorService();
		
		try {
			return admin.deleteService("pub",publisherId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
		
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
		
		AdministratorService admin = new AdministratorService();
		try {
			return admin.updateService(author);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
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
			try{
			i = extendDate(request);
			System.out.println("extendDate"+i);
			if(i > 0)
				request.setAttribute("message", "Due Date extended Sucessfully");
				else
					request.setAttribute("message", "Due Date not extended, Opertation Failed!!");
			}catch(Exception e)
			{
				e.getStackTrace();
			}
			forwardPath = "/date.jsp";
			break;
		case "/addAuthor":
			try{
			i=addAuthor(request);
			if(i != 0)
				request.setAttribute("message", "Author Added Sucessfully");
				else
					request.setAttribute("message", "Author was not Added, Opertation Failed!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String str = "Author was not Added," + e.getMessage();
			request.setAttribute("message", str );
			
		}
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
		case "/addPublisher":
			try {
				i=addPublisher(request);
				if(i > 0)
					request.setAttribute("message", "Publisher Added Sucessfully");
					else
						request.setAttribute("message", "Publisher was not Added, Opertation Failed!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String str = "Publisher was not Added," + e.getMessage();
				request.setAttribute("message", str );
				
			}
			
			forwardPath = "/publisher.jsp";
			break;
		case "/addUser":
			try {
				i=addBorrower(request);
				if(i > 0)
					request.setAttribute("message", "Borrower Added Sucessfully");
					else
						request.setAttribute("message", "Borrower was not Added, Opertation Failed!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String str = "Borrower was not Added," + e.getMessage();
				request.setAttribute("message", str );
				
			}
			
			forwardPath = "/users.jsp";
			break;
		case "/addBranch":
			try {
				i=addBranch(request);
				if(i > 0)
					request.setAttribute("message", "Branch Added Sucessfully");
					else
						request.setAttribute("message", "Branch was not Added, Opertation Failed!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String str = "Borrower was not Added," + e.getMessage();
				request.setAttribute("message", str );
				
			}
			
			forwardPath = "/branchdetails.jsp";
			break;
		case "/addGenre":
			try {
				i=addGenre(request);
				if(i > 0)
					request.setAttribute("message", "Genre Added Sucessfully");
					else
						request.setAttribute("message", "Genre was not Added, Opertation Failed!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String str = "Genre was not Added," + e.getMessage();
				request.setAttribute("message", str );
				
			}
			
			forwardPath = "/genre.jsp";
			break;
		
			
			
		case "/editAuthor":
			//TODO Call Edit on JDBC
			try{
				
			
			i = updateAuthor(request);
			if(i == 1)
				request.setAttribute("message", "Author with Id "+request.getParameter("authorId")+" succesfully updated.");
			else
				request.setAttribute("message", "Author name not updated. Try Again!!");
			}
			catch(Exception e)
			{
				String str = "operation Failed!!"+e.getMessage();
				request.setAttribute("message",str);
			}
			forwardPath = "/viewauthors.jsp";
			
			break;
		case "/editBook":
			
			i = updateBook(request);
			if(i == 1)
				request.setAttribute("message", "Book with Id "+request.getParameter("bookId")+" succesfully updated.");
			else
				request.setAttribute("message", "book title not updated. Try Again!!");
			forwardPath = "/viewbooks.jsp";
			
			break;
		case "/editPublisher":
			try{
				i = updatePublisher(request);
				if(i == 1)
					request.setAttribute("message", "Publisher with Id "+request.getParameter("publisherId")+" succesfully updated.");
				else
					request.setAttribute("message", "publisher details not updated. Try Again!!");
				
			}
			catch(Exception e)
			{
				String str = "operation Failed!!"+e.getMessage();
				request.setAttribute("message",str);
			}
			
			forwardPath = "/viewpublisher.jsp";	
			break;
		case "/editUser":
			
			try{
				i = updateBorrower(request);
			if(i == 1)
				request.setAttribute("message", "Borrower with cardNo "+request.getParameter("cardNo")+" succesfully updated.");
			else
				request.setAttribute("message", "Borrower details not updated. Try Again!!");
			
		}
		catch(Exception e)
		{
			String str = "operation Failed!!"+e.getMessage();
			request.setAttribute("message",str);
		}
		
		forwardPath = "/viewusers.jsp";	
		break;
		case "/editBranch":
			try{
				i = updateBranch(request);
			if(i == 1)
				request.setAttribute("message", "Branch with Id "+request.getParameter("cardNo")+" succesfully updated.");
			else
				request.setAttribute("message", "Branch details not updated. Try Again!!");
			
		}
		catch(Exception e)
		{
			String str = "operation Failed!!"+e.getMessage();
			request.setAttribute("message",str);
		}
		
		forwardPath = "/branchdetails.jsp";	
		break;
		case "/editBranchCopy":
			try{
			i = updateCopies(request);
			if(i == 1)
				request.setAttribute("message", "Book with Id "+request.getParameter("bookId")+" for branch with Id "+request.getParameter("branchId")+"  no.of copeies is updated.");
			else
				request.setAttribute("message", "book title not updated. Try Again!!");
			}catch(Exception e)
			{
				e.getStackTrace();
			}
			forwardPath = "/branchdetails.jsp";
			
			break;
		case "/editGenre":
			try{
				i = updateGenre(request);
				if(i == 1)
					request.setAttribute("message", "Genre with Id "+request.getParameter("genreId")+"updated");
				else
					request.setAttribute("message", "Genre name not updated. Try Again!!");
				}catch(Exception e)
				{
					e.getStackTrace();
				}
				forwardPath = "/viewgenre.jsp";
				
				break;
		default:
			break;
		}


		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	private int updateGenre(HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub
		int genreId = new Integer(request.getParameter("genreId"));
		String genreName = request.getParameter("genreName");
		
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		genre.setGenreName(genreName);
				
		AdministratorService admin =new AdministratorService();
		return admin.updateService(genre);
	}

	private int addGenre(HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub
		String genreName = request.getParameter("genreName");
		
		Genre genre = new Genre();
		genre.setGenreName(genreName);
				
		AdministratorService admin =new AdministratorService();
		return admin.createService(genre);
	}

	private int addBranch(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		
		Branch branch = new Branch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
		
		AdministratorService admin =new AdministratorService();
		return admin.createService(branch);
	}

	private int updateBranch(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int branchId = new Integer(request.getParameter("branchId"));
		String branchName = request.getParameter("branchName");
		
		String branchAddress = request.getParameter("branchAddress");
		
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
		
		
		
			AdministratorService admin =new AdministratorService();
			return admin.updateService(branch);
	}

	private int updateCopies(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int branchId = new Integer(request.getParameter("branchId"));
		int bookId = new Integer(request.getParameter("bookId"));
		int addCopies = new Integer(request.getParameter("addCopies"));
		
		BCopies bcopies =new BCopies();
		Book book = new Book();
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		book.setBookId(bookId);
		bcopies.setAddCopies(addCopies);
		bcopies.setBook(book);
		bcopies.setBranch(branch);
		
		LibrarianService lib = new LibrarianService();
		
		return lib.updateCopies(bcopies);
	}

	private int updateBorrower(HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub
		int cardNo = new Integer(request.getParameter("cardNo"));
		String name = request.getParameter("name");
		
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		Borrower br = new Borrower();
		br.setCardNo(cardNo);
		br.setName(name);
		br.setAddress(address);
		br.setPhone(phone);
		
		
			AdministratorService admin =new AdministratorService();
			return admin.updateService(br);
	}

	private int addBorrower(HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		Borrower br = new Borrower();
		br.setName(name);
		br.setAddress(address);
		br.setPhone(phone);
		
		
			AdministratorService admin =new AdministratorService();
			return admin.createService(br);
	}

	private int updatePublisher(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int pubId = new Integer(request.getParameter("publisherId"));
		String pubName = request.getParameter("publisherName");
		
		String pubAddr = request.getParameter("publisherAddr");
		String pubPhone = request.getParameter("publisherPhone");
		
		Publisher pub= new Publisher();
		pub.setPublisherId(pubId);
		pub.setPublisherName(pubName);
		pub.setPublisherAddress(pubAddr);
		pub.setPublisherPhone(pubPhone);
		
			AdministratorService admin =new AdministratorService();
			return admin.updateService(pub);
		
		
	}

	private int addPublisher(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String pubName = request.getParameter("publisherName");
		String pubAddr = request.getParameter("publisherAddr");
		String pubPhone = request.getParameter("publisherPhone");
		Publisher pub= new Publisher();
		pub.setPublisherName(pubName);
		pub.setPublisherAddress(pubAddr);
		pub.setPublisherPhone(pubPhone);
		
			AdministratorService admin =new AdministratorService();
			return admin.createService(pub);
		
		
	}

	private int extendDate(HttpServletRequest request)  throws Exception{
		// TODO Auto-generated method stub
		int bookId = new Integer(request.getParameter("bookId"));
		int branchId = new Integer(request.getParameter("branchId"));
		int cardNo = new Integer(request.getParameter("cardNo"));
		System.out.println("method");
		
		AdministratorService admin = new AdministratorService();
		JDBC jdbc = new JDBC();
		return admin.extendDate(bookId,branchId,cardNo);
		
		
		
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
		AdministratorService admin = new AdministratorService();
		Publisher pub = new Publisher();
		Author author = new Author();
		System.out.println("Author:"+selectAuthor);
		System.out.println("pub:"+selectPub);
		if(selectAuthor.equals("new"))
		{
			System.out.println("new author");
			author.setAuthorName(request.getParameter("authorName"));
			try {
				authorId = admin.createService(author);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			pub.setPublisherName(pubName);
			pub.setPublisherAddress(pubAddr);
			pub.setPublisherPhone(pubPhone);
			try {
				pubId = admin.createService(pub);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		pub.setPublisherId(pubId);
		
		Book book = new Book();
		book.setTitle(request.getParameter("title"));
		
		//return admin.createService(book);
		//return jdbc.addBook(book, author, pub);
		return 0;
	}

	private int addAuthor(HttpServletRequest request) throws Exception {
		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);
		
		AdministratorService admin = new AdministratorService();
		
			return admin.createService(author);
		
		
		
	}

}
