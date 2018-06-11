package com.pp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableContentRemover {
	
	private static final String DELETE_QUERY = "DELETE FROM u2base; DELETE FROM movies2directors; DELETE FROM movies2actors; DELETE FROM users;"
			+ "DELETE FROM movies; DELETE FROM directors; DELETE FROM actors;";
	
	private String dbUrl;
	private String user;
	private String password;

	public TableContentRemover(String dbUrl, String user, String password) {
		this.dbUrl = dbUrl;
		this.user = user;
		this.password = password;
	}

	public void flush() {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName("org.postgresql.Driver");

			System.out.println("Deleting data...");
			connection = DriverManager.getConnection(dbUrl, user, password);

			statement = connection.createStatement();
			statement.executeUpdate(DELETE_QUERY);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, statement);
		}
	}
	
	private void closeConnection(Connection connection, Statement statement) {
		try {
			if (statement != null) {
				connection.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
