package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO {

	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public int createBorrower(Borrower borrower) throws ClassNotFoundException,
			SQLException {
		return save("insert into tbl_borrower (name,address,phone) values(?,?,?)",
				new Object[] { borrower.getName(),borrower.getAddress(),borrower.getPhone() });
	}

	public int updateBorrower(Borrower borrower) throws ClassNotFoundException,
			SQLException {
		return save("update tbl_borrower set name = ?,address=?,phone=? where cardNo = ?",
				new Object[] { borrower.getName(),borrower.getAddress(),borrower.getPhone(), borrower.getCardNo() });
	}

	public int deleteBorrower(int cardNo) throws ClassNotFoundException,
			SQLException {
		
		if(checkBorrower(cardNo) == 0)
			return save("delete from tbl_borrower where cardNo=?",
					new Object[] { cardNo });
		
		return 0;
	}

	public int checkBorrower(int cardNo) throws ClassNotFoundException,
	SQLException {
		// TODO Auto-generated method stub
		return check("select Count(*) from tbl_book_loans where cardNo=?",
				new Object[] { cardNo });
		
	}

	public List<Borrower> getAllBorrowers() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_borrower", null);
	}

	public Borrower getBorrower(int cardNo) throws ClassNotFoundException,
			SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		borrowers = readAll("select * from tbl_borrower where cardNo = ?",
				new Object[] { cardNo });

		if (borrowers != null && borrowers.size() > 0) {
			return borrowers.get(0);
		}
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Borrower> borrowers = new ArrayList<Borrower>();

		try {
			while (rs.next()) {
				Borrower a = new Borrower();
				a.setCardNo(rs.getInt("cardNo"));
				a.setName(rs.getString("name"));
				a.setAddress(rs.getString("address"));
				a.setPhone(rs.getString("phone"));
			
				borrowers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return borrowers;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
}