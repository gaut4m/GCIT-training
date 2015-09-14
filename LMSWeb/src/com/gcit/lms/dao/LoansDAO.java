package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BCopies;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Loans;

public class LoansDAO extends BaseDAO {

	public LoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public int loanBook(Loans loan) throws ClassNotFoundException,
			SQLException {
	
		return save("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) value (?,?,?,CURDATE(),CURDATE()+INTERVAL 7 DAY)",
				new Object[] { loan.getBook().getBookId(), loan.getBranch().getBranchId(),loan.getBorrower().getCardNo() });
	}

	public int returnBook(Loans loan) throws ClassNotFoundException,
			SQLException {
		
		return save("update tbl_book_loans set dateIn= CURDATE() where branchId=? and bookId=? and cardNo = ?",
				new Object[] { loan.getBranch().getBranchId(), loan.getBook().getBookId(),loan.getBorrower().getCardNo() });
		
			
		
	}
	
	public int extendLoan(int bookId, int branchId, int cardNo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return save("update tbl_book_loans set dueDate= dueDate + INTERVAL 7 DAY where branchId=? and bookId=? and cardNo = ?",
				new Object[] { branchId, bookId, cardNo });

	}
	
	
	

	public List<Loans> getLoans(int cardNo) throws ClassNotFoundException,
			SQLException {
		List<Loans> loans = new ArrayList<Loans>();
		loans = readAll("select tbl_book.bookId,title,tbl_book_loans.branchId,tbl_library_branch.branchName,dateOut,dueDate,dateIn from tbl_book,tbl_book_loans,tbl_library_branch where tbl_book_loans.bookId = tbl_book.bookId and tbl_book_loans.branchId= tbl_library_branch.branchId and cardNo = ? and dateIn is null;",
				new Object[] { cardNo });

		
		return loans;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Loans> loans = new ArrayList<Loans>();

		try {
			while(rs.next())
			{
				Loans loan =new Loans();
				Book book = new Book();
				Branch branch =new Branch();
				
				book.setBookId(rs.getInt("bookId"));
				branch.setBranchId(rs.getInt("branchId"));
				branch.setBranchName(rs.getString("branchName"));
				book.setTitle(rs.getString("title"));
				loan.setDateOut(rs.getDate("dateOut"));
				loan.setDueDate(rs.getDate("dueDate"));
				loan.setBook(book);
				loan.setBranch(branch);
				
				loans.add(loan);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loans;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
