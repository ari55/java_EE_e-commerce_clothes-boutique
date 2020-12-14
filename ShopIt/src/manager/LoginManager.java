package manager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Adresse;
import entities.User;
import service.ConnexionDB;
import util.ConfigPassword;

public class LoginManager {
	/*
	 * public String userTotest(User user) {
	 * 
	 * Boolean statut = false; String query =
	 * "select * from user where email = ? and password = ?"; String username =
	 * user.getEmail(); String password = user.getPassword(); String dbusername =
	 * ""; // create two variable for use next process String dbpassword = ""; try {
	 * PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
	 * ps.setString(1, username); ps.setString(2, password);
	 * 
	 * ResultSet result = ps.executeQuery();
	 * 
	 * while (result.next()) { dbusername = result.getString("email"); // dbpassword
	 * = result.getString("password"); if (username.equals(dbusername) &&
	 * password.equals(dbpassword)) { return "SUCCESS LOGIN"; // if valid condition
	 * return string "SUCCESS LOGIN" } } } catch (SQLException ex) { // TODO Error
	 * Handling } finally { ConnexionDB.closeConnectionDB(); } return
	 * "WRONG USERNAME AND PASSWORD"; }
	 */

	public static User login(String id, String password) {
		User user = null;
		Adresse address = null;
		PreparedStatement ps = null;

		try {

			String query = "SELECT user.id, user.lastName, user.firstName, user.email, user.password,"
					+ "address.id, address.no, address.appt, address.street, address.zip, address.city, address.state, address.country"
					+ " FROM user INNER JOIN address on user.ship_address_id = address.id WHERE email = ? AND password = ?";
			ps = ConnexionDB.getPreparedStatementDB(query);
			ps.setString(1, id);
			try {
				ps.setString(2, ConfigPassword.SHA1(password));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();

			if (rs.next()) {
				address = new Adresse(rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), rs.getString(13));
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return user;
	}

}
