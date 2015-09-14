package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BCopies;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;

public class BCopiesDAO extends BaseDAO {

	public BCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public int createBCopies(BCopies bc) throws ClassNotFoundException,
			SQLException {
		return save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values(?,?,?)",
				new Object[] { bc.getBook().getBookId(),bc.getBranch().getBranchId(),bc.getNoofCopies() });
	}

	public int updateBCopies(BCopies bc) throws ClassNotFoundException,
			SQLException {
		return save("update tbl_book_copies set noOfCopies = noOfCopies + ? where bookId=? and branchId = ?",
				new Object[] { bc.getAddCopies(),bc.getBook().getBookId(),bc.getBranch().getBranchId()});
	}

	public int deleteBCopies(BCopies bc) throws ClassNotFoundException,
			SQLException {
		return save("delete from tbl_book_copies where bookId=? and branchId=?",
				new Object[] {bc.getBook().getBookId(),bc.getBranch().getBranchId() });
	}

	public List<BCopies> getAllBookCopiess() throws ClassNotFoundException,
			SQLException {
		return readAll("select tbl_book.bookId,tbl_branch.branchId, branchName, title,noOfCopies from tbl_book,tbl_book_copies,tbl_branch where tbl_branch.branchId = tbl_book_copies.branchId and tbl_book_copies.bookId=tbl_book.bookId", null);
	}

	public List<BCopies> getBranchCopies(int branchId) throws ClassNotFoundException,
			SQLException {
		
		return  readFirstLevel("select tbl_book.bookId,tbl_library_branch.branchId, branchName,title,noOfCopies from tbl_book,tbl_book_copies,tbl_library_branch where tbl_library_branch.branchId = ? and tbl_book_copies.bookId=tbl_book.bookId and tbl_library_branch.branchId = tbl_book_copies.branchId",
				new Object[] {branchId });
	}
	
	public List<BCopies> getBorrowableBooks(int branchId) throws ClassNotFoundException, SQLException
	{
		return  readAll("select bookId,tbl_library_branch.branchId, branchName,noOfCopies from tbl_book_copies,tbl_library_branch where tbl_library_branch.branchId = ? and tbl_library_branch.branchId = tbl_book_copies.branchId and noOfCopies > 0",
				new Object[] {branchId });
		
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<BCopies> bcopies = new ArrayList<BCopies>();
		BookDAO bdao =new BookDAO(conn);

		try {
			while (rs.next()) {
				BCopies a = new BCopies();
				Book book = new Book();
				Branch branch = new Branch();
				book.setBookId(rs.getInt("bookId"));
				try {
					book = bdao.getBook(book.getBookId());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				branch.setBranchId(rs.getInt("branchId"));
				branch.setBranchName(rs.getString("branchName"));
				a.setNoofCopies(rs.getInt("noOfCopies"));
				a.setBook(book);
				a.setBranch(branch);
				bcopies.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bcopies;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		List<BCopies> bcopies = new ArrayList<BCopies>();

		try {
			while (rs.next()) {
				BCopies a = new BCopies();
				Book book = new Book();
				Branch branch = new Branch();
				book.setBookId(rs.getInt("bookId"));
				book.setTitle(rs.getString("title"));
				branch.setBranchId(rs.getInt("branchId"));
				branch.setBranchName(rs.getString("branchName"));
				a.setNoofCopies(rs.getInt("noOfCopies"));
				a.setBook(book);
				a.setBranch(branch);
				bcopies.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bcopies;
	}
}