package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;

public class BranchDAO extends BaseDAO {

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

	public void deleteBranch(Branch branch) throws ClassNotFoundException,
			SQLException {
		int i=0;
		if(checkBranch(branch)==0)
		i=save("delete from tbl_book_copies where branchId=?",
				new Object[] { branch.getBranchId() });
		if(i>0)
			save("delete from tbl_library_branch where branchId=?",
					new Object[] { branch.getBranchId() });
	}
	
	private int checkBranch(Branch branch) throws ClassNotFoundException,
	SQLException {
		// TODO Auto-generated method stub
		return check("select Count(*) from tbl_book_loans where branchId=?",
				new Object[] {branch.getBranchId()});
		
	}

	public List<Branch> getAllBranchs() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_library_branch", null);
	}

	public Branch getBranch(Branch branch) throws ClassNotFoundException,
			SQLException {
		List<Branch> branches = new ArrayList<Branch>();
		branches = readAll("select * from tbl_library_branch where branchId = ?",
				new Object[] { branch.getBranchId() });

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
}
