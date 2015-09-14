package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.gcit.lms.dao.BCopiesDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.LoansDAO;
import com.gcit.lms.domain.BCopies;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Loans;

public class BorrowerService {

	public Borrower verifyCard(int cardNo)  {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		try {
			conn = ConnectionUtil.getConnection();
		
		
		BorrowerDAO bdao = new BorrowerDAO(conn);
		
		
			
			return bdao.getBorrower(cardNo);
					
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn != null)
			{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		return null;
		
	}

	public int returnBook(Loans loan) throws Exception{
		// TODO Auto-generated method stub
		Connection conn=null;
		int i=0;
		
			conn = ConnectionUtil.getConnection();
		
			try {
				LoansDAO bdao = new LoansDAO(conn);
		
		
			
			
			if(bdao.returnBook(loan)>0)
			{
				
					BCopies bc =new BCopies();
					bc.setBook(loan.getBook());
					bc.setBranch(loan.getBranch());
					bc.setAddCopies(1);
					BCopiesDAO bcDAO = new BCopiesDAO(conn);
					if( bcDAO.updateBCopies(bc)>0){
						conn.commit();
						return 1;
					}
					else
						conn.rollback();
					 
			}
			else
				conn.rollback();
					
		
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			if(conn != null)
			{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		return 0;
	}

	public int checkOut(Loans loan) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=null;
			
			conn = ConnectionUtil.getConnection();
		
			try {
				LoansDAO bdao = new LoansDAO(conn);
		
		
			
			
			if(bdao.loanBook(loan)>0)
			{
				
					BCopies bc =new BCopies();
					bc.setBook(loan.getBook());
					bc.setBranch(loan.getBranch());
					bc.setAddCopies(-1);
					BCopiesDAO bcDAO = new BCopiesDAO(conn);
					if( bcDAO.updateBCopies(bc)>0){
						conn.commit();
						return 1;
					}
					else
						conn.rollback();
					 
			}
			else
				conn.rollback();
					
		
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			if(conn != null)
			{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		return 0;
	}

}
