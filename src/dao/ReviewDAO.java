package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.AddressBean;
import beans.ReviewBean;

public class ReviewDAO {

	private DataSource ds;

	public ReviewDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// INSERTS THE BOOK REVIEW INTO THE REVIEWS TABLE IN DATABASE
	public void insertCustomerReview(String bid, String customerEmail, String date, String reviewTitle, String reviewDescription, int bookRating) throws
	SQLException{

		String query = "INSERT INTO REVIEWS (BID,EMAIL,DAY,REVIEWTITLE,REVIEWDESCRIPTION,RATING) VALUES ('"+bid+"', '"+customerEmail+"', '"+date+"','"+reviewTitle+"', '"+reviewDescription+"', "+bookRating+")";

		Connection con = this.ds.getConnection();
		Statement p = con.createStatement();
		int r = p.executeUpdate(query);
		System.out.println("VisitEvent Info");
		System.out.println(r);
		p.close();
		con.close();

	}

	// Retrieves the reviews of the book with bid provided and returns a map
	public Map<String, ReviewBean> retrieveCustomerReview(String bid) throws SQLException{

		String query = "select * from reviews where bid = '"+bid+"' ";

		Map<String,ReviewBean> rv = new HashMap<String, ReviewBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String originalbid = r.getString("bid");					
			String originalemail = r.getString("email");
			String originalDate = r.getString("day");	
			String originalReviewTitle = r.getString("reviewTitle");
			String originalReviewDescription = r.getString("reviewdescription");
			int originalRating = r.getInt("rating");

			rv.put(originalemail, new ReviewBean(originalbid, originalemail, originalDate, originalReviewTitle, originalReviewDescription, originalRating));

		}
		r.close();
		p.close();
		con.close();
		return rv;
	}


}
