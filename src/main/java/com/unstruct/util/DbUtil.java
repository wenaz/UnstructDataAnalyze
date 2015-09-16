package com.unstruct.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_studentInfo";
	private String dbUserName="root";
	private String dbPassword="";
	private String jdbcName="com.mysql.jdbc.Driver";

	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
