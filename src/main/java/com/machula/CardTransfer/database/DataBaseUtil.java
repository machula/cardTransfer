package com.machula.CardTransfer.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseUtil {

	public DataBaseUtil() {
		 DataBaseUtil.initClass();
	}
	public static void initClass() {
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		} catch (Exception ex) {
			System.out.println("Connection failed...");
			ex.printStackTrace();
		}
	}
	public static Connection getConnection()  throws SQLException, IOException {
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/postgreSQL.properties"))) {
			props.load(in);
		}
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");

		return DriverManager.getConnection(url, username, password);
	}
	
	public boolean closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
