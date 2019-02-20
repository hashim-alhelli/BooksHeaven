package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.BookBean;

public class BookDAO {

	private DataSource ds;

	public BookDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<String, BookBean> retrieveByTitle(String title) throws SQLException {
		String query = "select * from BOOK where LOWER(title) like LOWER('%" + title + "%')";
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String bid = r.getString("bid");
			double price = r.getDouble("price");
			String category = r.getString("category");
			String orignalTitle = r.getString("title");
			// String picture = r.getString("GIVENNAME") + ", " +
			// r.getString("SURNAME");
			String img_name = r.getString("img_name");
			String description = r.getString("description");
			String author = r.getString("author");
			rv.put(orignalTitle, new BookBean(bid, orignalTitle, price, category, img_name, description, author));

		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public Map<String, BookBean> retriveByCategory(String category) throws SQLException {

		String query = "select * from book where category like '%" + category + "%'";
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String bid = r.getString("bid");
			double price = r.getDouble("price");
			String originalCategory = r.getString("category");
			String orignalTitle = r.getString("title");
			String img_name = r.getString("img_name");
			String description = r.getString("description");
			String author = r.getString("author");
			// String picture = r.getString("GIVENNAME") + ", " +
			// r.getString("SURNAME");
			rv.put(orignalTitle,
					new BookBean(bid, orignalTitle, price, originalCategory, img_name, description, author));

		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public Map<String, BookBean> retrieveByID(String id) throws SQLException {
		String query = "select * from book where bid ='" + id + "'";
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String bid = r.getString("bid");
			double price = r.getDouble("price");
			String category = r.getString("category");
			String orignalTitle = r.getString("title");
			// String picture = r.getString("GIVENNAME") + ", " +
			// r.getString("SURNAME");
			String img_name = r.getString("img_name");
			String description = r.getString("description");
			String author = r.getString("author");
			rv.put(bid, new BookBean(bid, orignalTitle, price, category, img_name, description, author));

		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	/////////////////////////////////// Report//////////////////////////////////////////////////////
	/*public int retrieveTitleReport(String name) throws SQLException {
		int counter = 0;
		String query = "select * from book where title like '%" + name + "%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			if(r.getString("title").equalsIgnoreCase(name))
			{
				counter++;

			}			
		}
		r.close();
		p.close();
		con.close();
		return counter;
	}*/

}
