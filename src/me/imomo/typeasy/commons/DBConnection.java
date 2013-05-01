package me.imomo.typeasy.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Database Connection Utility
 * version 1.0	2013/04/30
 * @author Mo
 *
 */
public class DBConnection {
	private static String driver,url,user,pwd;
	/* Get database configuration from DBConfig.properties */
	static {
		Properties p = new Properties();
		try {
			p.load(DBConnection.class.getClassLoader().getResourceAsStream("DBConfig.properties"));
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			user = p.getProperty("user");
			pwd = p.getProperty("pwd");
		} catch (Exception e) {
			System.exit(0);
		}
	}
	
	/**
	 * Get database connection
	 * @return database connection
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, pwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
