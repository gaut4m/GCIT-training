package com.gcit.lms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gcit.lms.database.JDBC;
import com.gcit.lms.domain.Borrower;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet({"/verifyCard","/logOut","/returnBook","/selectBranch"})
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
			
			i = returnBook(request);
			
			if(i > 0)
				request.setAttribute("message", "Book returned SuccesfullySucessfully");
				else
					request.setAttribute("message", "Book not returned, Opertation Failed!!");
			forwardPath = "/checkin.jsp";
			
			break;
		case "/selectBranch":
			String str = request.getParameter("branchId");
			System.out.println("branch:"+str);
			
			String[] tmp = str.split("\\.");
			System.out.println("branchId:"+tmp[0]);
			str = tmp[0];
			request.setAttribute("Id", new Integer(str));
			forwardPath = "/checkout.jsp";
			break;
			
		default:
			break;
		}

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	private int returnBook(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int bookId = new Integer(request.getParameter("bookId"));
		int branchId = new Integer(request.getParameter("branchId"));
		int cardNo = new Integer(request.getParameter("cardNo"));
		
		JDBC jdbc = new JDBC();
		return jdbc.returnBook(bookId,branchId,cardNo);
	}

	private Borrower verifyCard(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		int cardNo = new Integer(request.getParameter("cardNo"));
		JDBC jdbc = new JDBC();
		return jdbc.verifyCard(cardNo);
		
		
	}

}
