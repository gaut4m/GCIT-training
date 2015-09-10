package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String dbURL = "jdbc:mysql://localhost:3306/library";
	private static String userName = "root";
	private static String pwd = "root";

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbURL, userName, pwd);
		return conn;
	}
	
	public int save(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		
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
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		
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
	
	public <T> List<T> readAll(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		List<T> objects = new ArrayList<T>();
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return (List<T>) extractData(rs);
	}
	
	abstract public List<?> extractData(ResultSet rs);

}
