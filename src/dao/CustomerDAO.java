package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.AddressBean;
import beans.CustomerBean;

public class CustomerDAO {

	private DataSource ds;

	public CustomerDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<String,CustomerBean> retrieveCustomerInfo(String email, String password) throws
	SQLException{
		String message="";
	
		String query = "select * from customer where email like '%" + email + "%'";
				Map<String,CustomerBean> rv = new HashMap<String, CustomerBean>();
				Connection con = this.ds.getConnection();
				PreparedStatement p = con.prepareStatement(query);
				ResultSet r = p.executeQuery();
				while (r.next()){
					String fName = r.getString("fName");
					String lName = r.getString("lName");
					String role = r.getString("role");
					String originalPassword = r.getString("password"); 
					String originalEmail = r.getString("email");
					int v = originalEmail.compareTo(email);
					int s = originalPassword.compareTo(password);
					//String role = "customer"; 
					if (v == 0 && s == 0)
					{
						rv.put(originalEmail, new CustomerBean(fName, lName, originalEmail, password,role));
					}
					
					
				}
				r.close();
				p.close();
				con.close();
				return rv;
	}
	
	// Gets the Customer Address from the Customer table (which gets it from address table)
	public Map<String,AddressBean> retrieveCustomerInfoByEmail(String customerEmail) throws
	SQLException{
		
		String fullAddress="";

		//String query = "select address from customer, address where email like '%" + customerEmail + "%'";
		String query = "select fname,lname,email,street,city,province,country,zip,phone from customer, address where customer.email = '" +customerEmail+ "' AND customer.addressID = address.addressID";
				Map<String,AddressBean> rv = new HashMap<String, AddressBean>();
				Connection con = this.ds.getConnection();
				PreparedStatement p = con.prepareStatement(query);
				ResultSet r = p.executeQuery();
				while (r.next()){
					String originalFname = r.getString("fname");					
					String originalLname = r.getString("lname");
					String originalEmail = r.getString("email");					
					String originalStreet = r.getString("street");
					String originalCity = r.getString("city");
					String originalProvince = r.getString("province");
					String originalCountry = r.getString("country");
					String originalZip = r.getString("zip");
					String originalPhone = r.getString("phone");
					
					//fullAddress = originalStreet + originalCity + originalProvince + originalCountry + originalZip + originalPhone;
					//System.out.println("customerDAO address = "+ fullAddress);
//					int v = originalEmail.compareTo(customerEmail);
//					String role = "customer"; 
//					if(v == 0)
					rv.put(originalEmail, new AddressBean(originalFname,originalLname,originalStreet, originalCity, originalProvince, originalCountry, originalZip, originalPhone));
				
				}
				r.close();
				p.close();
				con.close();
				return rv;
	}
	
	public void insertCustomerInfo(String fName, String lName, String email, String password,String role) throws
	SQLException{
		String query = "INSERT INTO CUSTOMER (FNAME,LNAME,EMAIL,PASSWORD,ROLE) VALUES ('"+fName+"', '"+lName+"', '"+email+"','"+ password+"','"+ role+"')";
				 
				Connection con = this.ds.getConnection();
				Statement p = con.createStatement();
				int r = p.executeUpdate(query);
				System.out.println("HELLLLLLLLOOOOOO");
				System.out.println(r);
				p.close();
				con.close();
				   
	}
}
