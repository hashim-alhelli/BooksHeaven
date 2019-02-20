package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dao.AddressDAO;
import dao.BookDAO;
import dao.CustomerDAO;
import dao.PurchaseOrderDAO;
import dao.ReviewDAO;
import dao.VisitEventDAO;
import beans.AddressBean;
import beans.BookBean;
import beans.CustomerBean;
import beans.PurchaseOrderBean;
import beans.ReviewBean;
import beans.VisitEventBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class BookStoreModel {

	BookDAO bookDAO;
	CustomerDAO customerDAO;
	AddressDAO addressDAO;
	VisitEventDAO visitEventDAO;
	ReviewDAO reviewDAO;
	PurchaseOrderDAO purchaseOrderDAO;

	// private
	// added
	private boolean cidReview = false;
	private double rating = 0;

	public BookStoreModel() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		this.bookDAO = new BookDAO();
		this.customerDAO = new CustomerDAO();
		this.addressDAO = new AddressDAO();
		this.visitEventDAO = new VisitEventDAO();
		this.reviewDAO = new ReviewDAO();
		this.purchaseOrderDAO = new PurchaseOrderDAO();

	}

	////////////////////////////////////////////////////// METHODS RELATED TO
	////////////////////////////////////////////////////// BOOK
	////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////
	public Map<String, BookBean> retriveBytitle(String title) throws Exception {

		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		if (title != null && !title.isEmpty()) {
			try {
				rv = bookDAO.retrieveByTitle(title);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return rv;

	}

	public Map<String, BookBean> retriveByCategory(String category) throws Exception {

		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		if (category != null && !category.isEmpty()) {
			try {
				rv = bookDAO.retriveByCategory(category);

				for (BookBean b : rv.values()) {

					System.out.println(b.getBid());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return rv;

	}

	public BookBean retriveByID(String id) throws Exception {

		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		if (id != null && !id.isEmpty()) {
			try {
				rv = bookDAO.retrieveByID(id);

				for (BookBean b : rv.values()) {

					System.out.println(b.getBid());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return rv.get(id);

	}

	////////////////////////////////////////////////////// METHODS RELATED TO
	////////////////////////////////////////////////////// CUSTOMER
	////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////

	public void inserttoCustomerInfo(String fName, String lName, String email, String password, String role)
			throws Exception {
		// System.out.println("BEFORE IF");
		if (fName != null && !fName.isEmpty() && lName != null && !lName.isEmpty() && email != null && !email.isEmpty()
				&& password != null && !password.isEmpty()) {
			// System.out.println("AFTER IF");

			try {
				customerDAO.insertCustomerInfo(fName, lName, email, password, role);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	////////////////////////////////////////////////////// METHODS RELATED TO
	////////////////////////////////////////////////////// ADDRESS
	////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////

	// To get the address of the customer logged in to show in payment page
	public Map<String, AddressBean> retrieveCustomerInfoByEmail(String customerEmail) throws Exception {
		// System.out.println("BEFORE IF");
		String s = "";
		Map<String, AddressBean> rv = new HashMap<String, AddressBean>();
		if (customerEmail != null && !customerEmail.isEmpty()) {

			try {
				rv = customerDAO.retrieveCustomerInfoByEmail(customerEmail);
				System.out.println("model address =" + s);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return new HashMap<String, AddressBean>(rv);
	}

	public void insertAddressInfo(String street, String city, String province, String country, String zip, String phone)
			throws Exception {
		// System.out.println("BEFORE IF");
		if (street != null && !street.isEmpty() && city != null && !city.isEmpty() && province != null
				&& !province.isEmpty() && country != null && !country.isEmpty() && zip != null && !zip.isEmpty()) {
			// System.out.println("AFTER IF");

			try {
				System.out.println("Address Info");
				addressDAO.insertAddressInfo(street, city, province, country, zip, phone);
				System.out.println("SuccessFul");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	////////////////////////////////////////////////////// METHODS RELATED TO
	////////////////////////////////////////////////////// AUTHENTICATION
	////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////

	public Map<String,CustomerBean> checkLogin(String email, String password) throws Exception {
		String message = "";
		Map<String,CustomerBean> rv = new HashMap<String, CustomerBean>();
		if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
			System.out.println("AFTER IF");

			try {
				rv = customerDAO.retrieveCustomerInfo(email, password);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return rv;
	}

	////////////////////////////////////////////////////////// METHODS RELATED
	////////////////////////////////////////////////////////// TO VISIT EVENT
	////////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////

	public void insertVisitEventInfo(String bid, String eventType) throws Exception {
		if (bid != null && !bid.isEmpty() && eventType != null && !eventType.isEmpty()) {
			try {
				System.out.println("Address Info");
				visitEventDAO.insertVisitEventInfo(bid, eventType);
				System.out.println("SuccessFul");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	////////////////////////////////////////////////////// METHODS RELATED TO
	////////////////////////////////////////////////////// REVIEWS
	////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////

	// Calls the insert review method in ReviewsDAO to insert the review in
	// Reviews table
	public void insertCustomerReview(String bid, String customerEmail, String bookRating, String reviewTitle,
			String reviewDescription) throws Exception {

		// System.out.println("BEFORE IF");
		String s = "";
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		if (customerEmail != null && !customerEmail.isEmpty()) {

			try {
				reviewDAO.insertCustomerReview(bid, customerEmail, df.format(date), reviewTitle, reviewDescription,
						Integer.parseInt(bookRating));
				System.out.println("from model reviews method= " + s);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// Calls the ReviewDAO retrieve method to get the reviews in a Map
	public Map<String, ReviewBean> retrieveCustomerReview(String bid, String cid) throws Exception {
		// System.out.println("BEFORE IF");

		Map<String, ReviewBean> reviewRV = new HashMap<String, ReviewBean>();
		if (bid != null && !bid.isEmpty()) {

			try {
				reviewRV = reviewDAO.retrieveCustomerReview(bid);
				customerReviewed(cid, reviewRV);//changed
				setRating(reviewRV);//added
				System.out.println("from model reviews method= " + reviewRV.size());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return new HashMap<String, ReviewBean>(reviewRV);
	}
	
	//added

	private void setRating(Map<String, ReviewBean> reviewRV){
		double value = 0;
		if(reviewRV.size()>0){
		for (ReviewBean book : reviewRV.values()) {
		 value = value + book.getRating();
		}
		value = value/reviewRV.size();
		}
		rating = value;
	}
	
	public double getRating(){
		System.out.println("rating"+rating);
		return rating;
	}
	private void customerReviewed(String cid, Map<String, ReviewBean> reviewRV) {
		if (reviewRV.containsKey(cid)) {
			cidReview = true;
		} else {
			cidReview = false;
		}
	}

	// added
	public boolean customerReviewed() {
		return cidReview;
	}
	
	
	
	/////////////////////////////////////// PURCHASE ORDER TABLE//////////////////////////////////////////////////////////////////
	public void insertPurchaseOrderInfo(String lName, String fName, String status, int addressID, long orderNumber) throws
	SQLException{
		purchaseOrderDAO.insertPurchaseOrderInfo(lName, fName, status, addressID, orderNumber);
		System.out.println("MODEL PURCHASE");
				   
	}
	
	/*public int retrieveTitleReport(String name) throws SQLException {
		int quantity = bookDAO.retrieveTitleReport(name);		
		System.out.println("Retrieving title report");
		return quantity;
	}*/
	

	public ArrayList<VisitEventBean> retrieveReportByCart(String Month) throws SQLException {
		//Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		ArrayList<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		rv = visitEventDAO.retrieveReportByCart(Month);
		System.out.println("modelRV" + rv.size());
		return rv;
	}
	
	public ArrayList<VisitEventBean> retrieveReportByView(String Month) throws SQLException {
		//Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		ArrayList<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		rv = visitEventDAO.retrieveReportByView(Month);
		return rv;
	}
}
