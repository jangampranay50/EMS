package com.cg.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection getConnection() throws ClassNotFoundException,SQLException
	{
		String className = "oracle.jdbc.driver.OracleDriver";
		String user="hr";
		String pass="hr";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		
		Class.forName(className);
		
		Connection con = DriverManager.getConnection(url, user, pass);
		
		return con;
	}
}
