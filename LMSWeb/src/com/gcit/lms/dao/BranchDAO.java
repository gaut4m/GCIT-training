package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;

public class BranchDAO extends BaseDAO {

	public BranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void createBranch(Branch branch) throws ClassNotFoundException,
			SQLException {
		save("insert into tbl_library_branch (branchName,branchAddress) values(?,?)",
				new Object[] { branch.getBranchName(),branch.getBranchAddress() });
	}

	public void updateBranch(Branch branch) throws ClassNotFoundException,
			SQLException {
		save("update tbl_library_branch set branchName = ?,branchAddress=? where branchId = ?",
				new Object[] { branch.getBranchName(),branch.getBranchAddress(), branch.getBranchId() });
	}

	public int deleteBranch(int branchId) throws ClassNotFoundException,
			SQLException {
		int i=0;
		if(checkBranch(branchId)==0){
			save("delete from tbl_book_copies where branchId=?",
				new Object[] { branchId });

		return	save("delete from tbl_library_branch where branchId=?",
					new Object[] {branchId });
		}
		
		return 0;
	}
	
	private int checkBranch(int branchId) throws ClassNotFoundException,
	SQLException {
		// TODO Auto-generated method stub
		return check("select Count(*) from tbl_book_loans where branchId=?",
				new Object[] {branchId});
		
	}

	public List<Branch> getAllBranchs() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_library_branch", null);
	}

	public Branch getBranch(int branchId) throws ClassNotFoundException,
			SQLException {
		List<Branch> branches = new ArrayList<Branch>();
		branches = readAll("select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId });

		if (branches != null && branches.size() > 0) {
			return branches.get(0);
		}
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Branch> branches = new ArrayList<Branch>();

		try {
			while (rs.next()) {
				Branch a = new Branch();
				a.setBranchId(rs.getInt("branchId"));
				a.setBranchName(rs.getString("branchName"));
				a.setBranchAddress(rs.getString("branchAddress"));
				
				
				branches.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return branches;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
}
