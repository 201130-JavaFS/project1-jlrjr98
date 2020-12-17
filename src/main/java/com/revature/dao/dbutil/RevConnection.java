package com.revature.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RevConnection {
	
	private static Connection connection;

	private RevConnection() {
		super();
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		try {
			Class.forName(DbUtilProps.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url=DbUtilProps.URL;			
		String username=System.getenv("postgresUserName");
		String password=System.getenv("postgresPassword");
		connection=DriverManager.getConnection(url, username, password);
		return connection;
		
	}
	
	

}
