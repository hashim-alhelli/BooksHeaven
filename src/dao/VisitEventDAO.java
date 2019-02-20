package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.CustomerBean;
import beans.VisitEventBean;

public class VisitEventDAO {

	private DataSource ds;

	public VisitEventDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void insertVisitEventInfo(String bid, String eventType) throws SQLException {

		Date day = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String query = "INSERT INTO VisitEvent (DAY,BID,EVENTTYPE) VALUES ('" + df.format(day) + "', '" + bid + "', '"
				+ eventType + "')";

		Connection con = this.ds.getConnection();
		Statement p = con.createStatement();
		int r = p.executeUpdate(query);
		System.out.println("VisitEvent Info");
		System.out.println(r);
		p.close();
		con.close();

	}

	
	// Retrieve by EventType 
	
	public ArrayList<VisitEventBean> retrieveReportByCart(String Month) throws SQLException {
		String monthFirst = "2016-"+Month+"-01";
		String monthLast = "2016-"+Month+"-31";

		
		String query = "select COUNT(bid) as total, bid, day, eventtype from visitevent where eventtype = 'CART' and day >= '"+monthFirst+"' and day <='"+monthLast+"' GROUP BY bid, day, eventtype ORDER BY total DESC ";
		System.out.println(query);
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		//Map<String,VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		ArrayList<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		while (r.next()){
			Date date = r.getDate("day");
			String bid = r.getString("bid");
			String eventType = r.getString("eventtype");
			int count = r.getInt("total");
			rv.add( new VisitEventBean(date, bid, eventType, count));
			
		}
		System.out.println("DAO CART " + rv.size());
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	
	
	// Cart
	

	public ArrayList<VisitEventBean> retrieveReportByView(String Month) throws SQLException {
		String monthFirst = "2016-"+Month+"-01";
		String monthLast = "2016-"+Month+"-31";

		
		String query = "select COUNT(bid) as total, bid, day, eventtype from visitevent where eventtype = 'VIEW' and day >= '"+monthFirst+"' and day <='"+monthLast+"'GROUP BY bid, day, eventtype ORDER BY total DESC ";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		//Map<String,VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		ArrayList<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		while (r.next()){
			Date date = r.getDate("day");
			String bid = r.getString("bid");
			String eventType = r.getString("eventtype");
			int count = r.getInt("total");
			rv.add(new VisitEventBean(date, bid, eventType, count));
		
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	
	
}
