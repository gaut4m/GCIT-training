package com.gcit.lms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.database.JDBC;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.BCopies;
import com.gcit.lms.domain.Branch;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet({"/updateBranch","/updateCopies"})
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianServlet() {
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
		String forwardPath = "/branch.jsp";
		switch (reqUrl) {
		
		case "/updateBranch":
			//TODO Call Edit on JDBC
			i = updateBranch(request);
			if(i == 1)
				request.setAttribute("message", "Branch name: "+request.getParameter("branchName")+", Branch Address: "+request.getParameter("branchAddr")+" succesfully updated.");
			else
				request.setAttribute("message", "Branch details not updated. Try Again!!");
			
			
			break;
		case "/updateCopies":
			i = updateCopies(request);
			if(i == 1)
				request.setAttribute("message", "Book with Id "+request.getParameter("bookId")+" for branch with Id "+request.getParameter("branchId")+"  no.of copeies is updated.");
			else
				request.setAttribute("message", "book title not updated. Try Again!!");
			forwardPath = "/updatecopies.jsp";
			
			break;

		default:
			break;
		}

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
	}

	private int updateCopies(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int branchId = new Integer(request.getParameter("branchId"));
		int bookId = new Integer(request.getParameter("bookId"));
		int addCopies = new Integer(request.getParameter("addCopies"));
		
		BCopies b =new BCopies();
		b.setBranchId(branchId);
		b.setBookId(bookId);
		b.setAddCopies(addCopies);
		
		JDBC jdbc = new JDBC();
		return jdbc.updateCopies(b);
	}

	private int updateBranch(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int branchId = new Integer(request.getParameter("branchId"));
		String branchName = request.getParameter("branchName");
		String branchAddr = request.getParameter("branchAddr");
		
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddr);
		
		JDBC jdbc = new JDBC();
		return jdbc.updateBranch(branch);
	}

}
