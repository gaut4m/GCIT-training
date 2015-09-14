package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.BCopiesDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.BCopies;
import com.gcit.lms.domain.Branch;

public class LibrarianService {

	ConnectionUtil connUtil = new ConnectionUtil();
	
	public List<BCopies> getAvailableBooks(int branchId)
	{
		
		Connection conn=null;
		try {
			conn = ConnectionUtil.getConnection();
		
		BCopiesDAO bcdao = new BCopiesDAO(conn);
		
		
			
				return bcdao.getBorrowableBooks(branchId);
			
			
			
			}
			catch(Exception e){
			e.printStackTrace();
			try {
				
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		 finally{
			 if(conn !=null){
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
	public int updateCopies(BCopies bcopies) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		BCopiesDAO bcdao = new BCopiesDAO(conn);
		
		try{
			
				i = bcdao.updateBCopies(bcopies);
			
			if(i>0)
			conn.commit();
			else
				conn.rollback();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}
	
	public List<BCopies> getCopies(int branchId)
	{
		
		Connection conn=null;
		try {
			conn = ConnectionUtil.getConnection();
		
		BCopiesDAO bcdao = new BCopiesDAO(conn);
		
		
			
				return bcdao.getBranchCopies(branchId);
			
			
			
			}
			catch(Exception e){
			e.printStackTrace();
			try {
				
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		 finally{
			 if(conn !=null){
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
	
	public Branch getBranch(int branchId) throws Exception
	{
		Connection conn = ConnectionUtil.getConnection();
		
		BranchDAO adao = new BranchDAO(conn);
		try{
			
			return adao.getBranch(branchId);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
		
	}

}
