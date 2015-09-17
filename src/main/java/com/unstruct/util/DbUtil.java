package com.unstruct.util;

import java.sql.*;


public class DbUtil {

	private static String dbUrl="jdbc:mysql://localhost:3306/db_studentInfo";
	private static String dbUserName="root";
	private static String dbPassword="";
	private static String jdbcName="com.mysql.jdbc.Driver";

	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName(jdbcName);
			conn=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static PreparedStatement prepare(Connection conn,String sql){
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
				conn=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement stmt){
		if(stmt!=null){
			try {
				stmt.close();
				stmt=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Connection conn=DbUtil.getConnection();
		System.out.println("ok");
		DbUtil.close(conn);
		
	}

}
