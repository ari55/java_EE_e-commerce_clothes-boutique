package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.User;
import service.ConnexionDB;

public class UserManager {
	public static ArrayList<User> getUserById(String id) {
		ArrayList<User> user = new ArrayList<User>();

		try {
			String query = "SELECT user.id, user.lastName, user.firstName, user.email, user.password,"
					+ "address.id, address.no, address.appt, address.street, address.zip, address.city, address.state, address.country"
					+ " FROM user INNER JOIN address on user.ship_address_id = address.id WHERE user.id = ?";
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return user;
	}
		static String getUsers = "select id,firstName,lastName,email,idPrivilege from user;";

	public static ArrayList<User> getAllUsers() {

		ArrayList<User> users = new ArrayList<User>();
		try {
			String query = getUsers;
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User unUser = new User();
				unUser.setId(rs.getInt(1));
				unUser.setLastName(rs.getString(2));
				unUser.setFirstName(rs.getString(3));
				unUser.setEmail(rs.getString(4));
				unUser.setIdPrivilege(rs.getInt(5));
				users.add(unUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}
		return users;
	}

}
