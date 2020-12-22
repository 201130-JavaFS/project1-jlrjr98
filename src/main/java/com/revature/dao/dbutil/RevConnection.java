package com.revature.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RevConnection {
	
	private static final Logger log = LogManager.getLogger(RevConnection.class); 
	
	private static Connection connection;

	private RevConnection() {
		super();
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		try {
			Class.forName(DbUtilProps.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error("Runtime Exception: ", e);
		}
		
		String url=DbUtilProps.URL;			
		String username=System.getenv("postgresUserName");
		String password=System.getenv("postgresPassword");
		connection=DriverManager.getConnection(url, username, password);
		return connection;
		
	}
	
	

}
