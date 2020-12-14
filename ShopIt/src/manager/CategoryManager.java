package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Category;
import service.ConnexionDB;

public class CategoryManager {
	
	static String GET_All_CATEGORIES = "SELECT * FROM category;";
	public static ArrayList<Category> getCategories() {

		ArrayList<Category> categories = new ArrayList<Category>();

		try {
			String query = GET_All_CATEGORIES;
			ConnexionDB.getPreparedStatementDB(query);
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			ResultSet rs;
			rs = ps.executeQuery();

			while (rs.next()) {
				categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return categories;
	}

	public static ArrayList<Category> getCategoryById(String id) {
		ArrayList<Category> category = new ArrayList<Category>();

		try {
	//ConnexionDB.connect();
			String query = "SELECT * FROM category WHERE id = " + id;
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				category.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return category;
	}
	static public int addCategory(Category category) {
		int code = isExist2(category);

		if (code == 1) {
			try {
				///ConnexionDB.connect();
				// String query = "INSERT INTO category (name, description, order, isActive)
				// VALUES (?, null, ?, ?);";
				String query = "INSERT INTO category (`name`, `description`, `order`) VALUES (?, ?, ?);";

				PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);

				ps.setString(1, category.getName());
				ps.setString(2, category.getDescription());
				ps.setInt(3, category.getOrder());
				ps.executeUpdate(); // MDB.commit();

				/*
				 * ps.setString(1, "test"); ps.setString(2, "Test description"); ps.setInt(3,
				 * 1); ps.setInt(4, 0); ps.executeUpdate();
				 */
				System.out.println(" Successfuly created");
			} catch (SQLException ex) {

			} finally {
				ConnexionDB.closeConnectionDB();
			}

		}
		return code;
	}
	public static int isExist(int category) {
		int isExist = -1;
		try {
			//ConnexionDB.connect();
			String query = "SELECT 'exist' FROM category WHERE id = ?";
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);

			ps.setInt(1, category);
			ResultSet rs = ps.executeQuery();

			isExist = (rs.first() ? 0 : 1);
		} catch (SQLException e) {
			isExist = -1;
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return isExist;
	}
	
	public static int isExist2(Category category) {
		int isExist = -1;
		try {
			//ConnexionDB.connect();
			String query = "SELECT id FROM category WHERE name = ?";
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);

			ps.setString(1, category.getName());
			ResultSet rs = ps.executeQuery();

			isExist = (rs.first() ? 0 : 1);
		} catch (SQLException e) {
			isExist = -1;
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return isExist;
	}

}
