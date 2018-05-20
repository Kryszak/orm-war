package com.pp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DbTableCreator {

	private static final String CREATE_ACTORS_TABLE = "CREATE TABLE IF NOT EXISTS actors(actorid bigint, a_gender varchar, a_quality integer, PRIMARY KEY(actorid));"
			+ "CREATE INDEX IF NOT EXISTS gender_idx ON actors(a_gender);"
			+ "CREATE INDEX IF NOT EXISTS quality_idx ON actors(a_quality);";

	private static final String CREATE_DIRECTORS_TABLE = "CREATE TABLE IF NOT EXISTS directors(directorid bigint, d_quality integer, avg_revenue integer, PRIMARY KEY(directorid));"
			+ "create index if not exists avg_revenue on directors (avg_revenue);"
			+ "create index if not exists d_quality on directors (d_quality);";

	private static final String CREATE_MOVIES_TABLE = "CREATE TABLE IF NOT EXISTS movies(movieid bigint, year integer, isEnglish boolean, country varchar, runningtime integer, PRIMARY KEY(movieid));"
			+ "create index if not exists country on movies(country);"
			+ "create index if not exists isEnglish on movies(isEnglish);"
			+ "create index if not exists runningtime on movies(runningtime);"
			+ "create index if not exists year on movies(year);";

	private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users(userid bigint, age varchar, u_gender varchar, occupation varchar, PRIMARY KEY (userid));"
			+ "CREATE INDEX IF NOT EXISTS age on users(age);"
			+ "CREATE INDEX IF NOT EXISTS occupation on users(occupation);"
			+ "CREATE INDEX IF NOT EXISTS u_gender on users(u_gender);";

	private static final String CREATE_MOVIES_2_ACTORS_TABLE = "CREATE TABLE IF NOT EXISTS movies2actors(movieid bigint REFERENCES movies(movieid), actorid bigint REFERENCES actors(actorid), cast_num integer, PRIMARY KEY(movieid,actorid));"
			+ "CREATE INDEX IF NOT EXISTS castnum on movies2actors(cast_num);"
			+ "CREATE INDEX IF NOT EXISTS movie on movies2actors(movieid);"
			+ "CREATE INDEX IF NOT EXISTS actor on movies2actors(actorid);";

	private static final String CREATE_MOVIES_2_DIRECTORS_TABLE = "CREATE TABLE IF NOT EXISTS movies2directors(movieid bigint REFERENCES movies(movieid), directorid bigint REFERENCES directors(directorid), genre varchar, PRIMARY KEY(movieid,directorid));"
			+ "CREATE INDEX IF NOT EXISTS genreidx on movies2directors(genre);"
			+ "CREATE INDEX IF NOT EXISTS movieidx on movies2directors(movieid);"
			+ "CREATE INDEX IF NOT EXISTS directoridx on movies2directors(directorid);";

	private static final String CREATE_USERS_2_BASE_TABLE = "CREATE TABLE IF NOT EXISTS u2base(userid bigint REFERENCES users(userid), movieid bigint REFERENCES movies(movieid), rating varchar, PRIMARY KEY(userid,movieid));"
			+ "CREATE INDEX IF NOT EXISTS ratingidx on u2base(rating);"
			+ "CREATE INDEX IF NOT EXISTS useridx on u2base(userid);"
			+ "CREATE INDEX IF NOT EXISTS u2basemovieidx on u2base(movieid);";

	private static final List<String> CREATE_TABLE_QUERIES = Arrays.asList(CREATE_ACTORS_TABLE, CREATE_DIRECTORS_TABLE,
			CREATE_MOVIES_TABLE, CREATE_USERS_TABLE, CREATE_MOVIES_2_ACTORS_TABLE, CREATE_MOVIES_2_DIRECTORS_TABLE,
			CREATE_USERS_2_BASE_TABLE);

	public void createTables(String dbUrl, String user, String password) {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName("org.postgresql.Driver");

			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(dbUrl, user, password);

			statement = connection.createStatement();

			for (String query : CREATE_TABLE_QUERIES) {
				statement.executeUpdate(query);
			}
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
