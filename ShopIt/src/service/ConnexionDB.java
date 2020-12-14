package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ConnexionDB {

	static private java.sql.Connection connection = null;

	/*
	 * 1. Charger le driver 2. Definir l'url de connexion 3. Etablir la connexion 4.
	 * Creer l'object Statement (ou PreparedStatement) 5. Executer la requete 6.
	 * Traiter les resultats 7. Fermer la connexion
	 */
	static public PreparedStatement getPreparedStatementDB(String query) throws SQLException {

		String mysqlURL = "jdbc:mysql://localhost:3306/shopit?serverTimezone=UTC";
		String login = "root";
		String password = "abc123...";

		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(mysqlURL, login, password);

			preparedStatement = connection.prepareStatement(query);

		} catch (ClassNotFoundException ex) {
			// TODO error hanlding
			// Reflection
		}

		return preparedStatement;
	}

	static public PreparedStatement getPreparedStatementDB(String query, int id) throws SQLException {

		String mysqlURL = "jdbc:mysql://localhost:3306/shopit?serverTimezone=UTC";
		String login = "root";
		String password = "abc123...";

		PreparedStatement preparedStatement = null;

		try {
			if (connection == null || connection.isClosed())
				Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(mysqlURL, login, password);
			preparedStatement = connection.prepareStatement(query);

			if (id == 1)
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		} catch (ClassNotFoundException ex) {
			// TODO error hanlding
			// Reflection
		}

		return preparedStatement;
	}

	public static PreparedStatement getPreparedStatementDB(String query, String column) throws SQLException {
		return getPreparedStatementDB(query, new String[] { column });
	}

	public static PreparedStatement getPreparedStatementDB(String query, String[] columns) throws SQLException {

		String mysqlURL = "jdbc:mysql://localhost:3306/shopit?serverTimezone=UTC";
		String login = "root";
		String password = "abc123...";

		PreparedStatement preparedStatement = null;

		try {
			if (connection == null || connection.isClosed())
				Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(mysqlURL, login, password);
			preparedStatement = connection.prepareStatement(query, columns);
		} catch (ClassNotFoundException ex) {
			// TODO error hanlding
			// Reflection
		}

		return preparedStatement;
	}

	static public void closeConnectionDB() {
		try {
			connection.close();
		} catch (SQLException ex) {
			// TODO Exception handling
		}
	}

}