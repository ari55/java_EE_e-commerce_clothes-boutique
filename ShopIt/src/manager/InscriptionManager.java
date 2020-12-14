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
public class InscriptionManager {

	public static int signUp(User user)  {
		int code = isExist(user);
		if (code == 1) {
			try {
				InscriptionManager.ajouterAdress(user.getShipAddress());
				String query = "INSERT INTO user (`lastName`, `firstName`, `email`, `password`, `ship_address_id`) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
				ps.setString(1, user.getLastName());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getEmail());
				try {
					ps.setString(4,ConfigPassword.SHA1( user.getPassword()));
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ps.setInt(5, user.getShipAddress().getId());

				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnexionDB.closeConnectionDB();
			}
		}
		return code;
	}

	private static int isExist(User user) {
		int isExist = -1;

		try {
			String query = "SELECT id FROM user WHERE email = ?";
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			isExist = (rs.first() ? 0 : 1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return isExist;
	}

	private static int ajouterAdress(Adresse address) {

		int id = 0;

		try {
			String query = "INSERT INTO address (`no`, `appt`, `street`, `zip`, `city`, `state`, `country`) VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query, 1);
			ps.setString(1, address.getNo());
			ps.setString(2, address.getAppartement());
			ps.setString(3, address.getRue());
			ps.setString(4, address.getZip());
			ps.setString(5, address.getVille());
			ps.setString(6, address.getEtat());
			ps.setString(7, address.getPays());
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();

			if (generatedKeys.next()) {
				address.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("erreur de creation");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return id;
	}
	
}
