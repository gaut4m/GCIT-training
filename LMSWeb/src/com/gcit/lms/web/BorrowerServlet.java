package com.gcit.lms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gcit.lms.database.JDBC;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Loans;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet({"/verifyCard","/logOut","/returnBook","/selectBranch","/checkOut"})
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		int i=0;
		String forwardPath = "/borrower.jsp";
		HttpSession session=null;
		
		switch (reqUrl) {
		
		case "/verifyCard":
			//TODO Call Edit on JDBC
			Borrower br = verifyCard(request);
			if(br != null){
				forwardPath = "/bindex.jsp";
				session = request.getSession(true);
				session.setAttribute("cardNo", new Integer(request.getParameter("cardNo")));
				session.setAttribute("name", br.getName());
				session.setAttribute("Addr", br.getAddress());
				session.setAttribute("phone", br.getPhone());
			}
				else{
					
					forwardPath = "/borrower.jsp";
					request.setAttribute("message", "CardNo not found. Try Again!!");
				}
				
			
			break;
		case "/logOut":
			session = request.getSession(false);
			if( null != session.getAttribute("cardNo"))
			{
			request.getSession().invalidate();
	        
			}
			request.setAttribute("message", "logged out.");
			forwardPath = "/borrower.jsp";
			break;
		case "/returnBook":
			try{
			i = returnBook(request);
			
			if(i > 0)
				request.setAttribute("message", "Book returned SuccesfullySucessfully");
				else
					request.setAttribute("message", "Book not returned, Opertation Failed!!");
			}catch(Exception e)
			{
				e.getStackTrace();
			}
			forwardPath = "/checkin.jsp";
			
			break;
		case "/checkOut":
			try{
				i = checkOut(request);
				
				if(i > 0)
					request.setAttribute("message", "Book checked out Sucessfully from branchId "+request.getParameter("branchId"));
					else
						request.setAttribute("message", "Book not checked out, Opertation Failed!!");
				}catch(Exception e)
				{
					e.getStackTrace();
				}
				forwardPath = "/bindex.jsp";
				
				break;
			
		case "/selectBranch":
			String str = request.getParameter("branchId");
			System.out.println("branch:"+str);
			
			String[] tmp = str.split("\\.");
			System.out.println("branchId:"+tmp[0]);
			
			request.setAttribute("branchId", new Integer(tmp[0]));
			request.setAttribute("branchName", tmp[1]);
			forwardPath = "/checkout.jsp";
			break;
			
		default:
			break;
		}

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	private int checkOut(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int bookId = new Integer(request.getParameter("bookId"));
		int branchId = new Integer(request.getParameter("branchId"));
		int cardNo =  new Integer(request.getParameter("cardNo"));
		Book book =new Book();
		Borrower br = new Borrower();
		Branch branch = new Branch();
		book.setBookId(bookId);
		br.setCardNo(cardNo);
		branch.setBranchId(branchId);
		Loans loan =new Loans();
		loan.setBook(book);
		loan.setBorrower(br);
		loan.setBranch(branch);
		BorrowerService user =new BorrowerService();
		return user.checkOut(loan);
	}

	private int returnBook(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int bookId = new Integer(request.getParameter("bookId"));
		int branchId = new Integer(request.getParameter("branchId"));
		int cardNo = new Integer(request.getParameter("cardNo"));
		Book book =new Book();
		Borrower br = new Borrower();
		Branch branch = new Branch();
		book.setBookId(bookId);
		br.setCardNo(cardNo);
		branch.setBranchId(branchId);
		Loans loan =new Loans();
		loan.setBook(book);
		loan.setBorrower(br);
		loan.setBranch(branch);
		BorrowerService user =new BorrowerService();
		return user.returnBook(loan);
		
	}

	private Borrower verifyCard(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int cardNo = new Integer(request.getParameter("cardNo"));
		BorrowerService user = new BorrowerService();
		return user.verifyCard(cardNo);	
	}

}
