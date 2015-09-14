package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {
	Connection conn = null;
	
	public BaseDAO(Connection conn){
		this.conn = conn;
	}
	
	
	
	public int save(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		int i = pstmt.executeUpdate();
		
		
		ResultSet rs = pstmt.getGeneratedKeys();
		
		if(rs!=null && rs.next())
			return rs.getInt(1);
		
		
		return i;
	}
	
	public int check(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs!=null && rs.next())
			return rs.getInt(1);
		
		
		return -1;
	}
	
	public List<?> readAll(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return (List<?>) extractData(rs);
	}
	
	abstract public List<?> extractData(ResultSet rs);
	
	
	public List<?> readFirstLevel(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return (List<?>) extractDataFirstLevel(rs);
	}
	
	abstract public List<?> extractDataFirstLevel(ResultSet rs);
}
