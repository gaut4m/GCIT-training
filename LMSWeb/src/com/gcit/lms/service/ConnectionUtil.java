package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String dbURL = "jdbc:mysql://localhost:3306/library";
	private static String userName = "root";
	private static String pwd = "root";

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbURL, userName, pwd);
		conn.setAutoCommit(false);
		return conn;
	}
}
