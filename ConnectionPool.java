package com.tyss.lms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.PartialResultException;

public class ConnectionPool {

	static ArrayList<Connection> connection_pool = new ArrayList<Connection>();
	static final int POOL_SIZE = 5;
	private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_project?user=postgres&password=12345";
	static {
		for (int i = 0; i < POOL_SIZE; i++) {
			connection_pool.add(setConnection());
		}

	}

	public static Connection setConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static Connection getConnection() {

		if (connection_pool.size() > 0) {
			
			return connection_pool.remove(0);
		}
		return getConnection();

	}

	public static void addConnection(Connection connection) {
		if (connection_pool.size() < POOL_SIZE) {
			connection_pool.add(connection);
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
