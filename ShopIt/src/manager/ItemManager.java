package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Item;
import service.ConnexionDB;

public class ItemManager {

	public static ArrayList<Item> getByCategory(int category) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			String query;
			PreparedStatement ps;
			ResultSet rs;

			if (category == 1) {
				query = "SELECT * FROM product";
				ps = ConnexionDB.getPreparedStatementDB(query);
			} else {
				query = "SELECT * FROM product WHERE category = ?";
				ps = ConnexionDB.getPreparedStatementDB(query);
				ps.setInt(1, category);
			}

			rs = ps.executeQuery();

			while (rs.next())
				items.add(getItemFromResultSet(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();

		}
		return items;
	}

	public static Item getItemById(int id) {
		Item item = null;
		try {
			String query = "SELECT * FROM product WHERE id = ?";
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				item = getItemFromResultSet(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

		return item;
	}

	public static ArrayList<Item> getAll() {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			String query = "SELECT * FROM product ";
			// ConnexionDB.connect();
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			ResultSet rs;

			rs = ps.executeQuery();

			while (rs.next())
				items.add(getItemFromResultSet(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}
		return items;
	}

	public static void updateProduct(int id, int categorie, String name, String description, double price) {
		String query = "UPDATE product SET category=?,name =?,description=?,price=? WHERE id =" + id;
		try {
			// ConnexionDB.connect();

			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);

			ps.setInt(1, categorie);
			ps.setString(3, description);
			ps.setString(2, name);
			ps.setDouble(4, price);
			int rs = ps.executeUpdate();
			int pss = id;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}

	}

	public static ArrayList<Item> getPromoItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			// ConnexionDB.connect();
			String query;
			query = "SELECT * FROM product WHERE id IN (SELECT product FROM promo_product)";

			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			ResultSet rs;

			rs = ps.executeQuery();

			while (rs.next())
				items.add(getItemFromResultSet(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}
		return items;
	}

	private static Item getItemFromResultSet(ResultSet rs) {

		Item item = new Item();

		try {
			item.setId(rs.getInt("id"));
			item.setCategory(rs.getInt("category"));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setPrice(rs.getDouble("price"));
			item.setSerialNumber(rs.getString("serialNumber"));
			item.setImage(rs.getString("imgName"));
			item.setStock(rs.getInt("stockQty"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	public static ArrayList<Item> geSearchProducts(String recherche) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			String query;
			ResultSet rs;

			query = "SELECT * FROM product WHERE upper(name) LIKE upper('%" + recherche
					+ "%') OR upper(description) LIKE upper('%" + recherche + "%');";
			PreparedStatement ps = ConnexionDB.getPreparedStatementDB(query);
			rs = ps.executeQuery();
			while (rs.next())
				items.add(getItemFromResultSet(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnexionDB.closeConnectionDB();
		}
		return items;
	}

}
