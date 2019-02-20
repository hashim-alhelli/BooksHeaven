package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AddressDAO {

	private DataSource ds;

	public AddressDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	public void insertAddressInfo(String street, String city, String province, String country, String zip,String phone) throws
	SQLException{
		String query = "INSERT INTO ADDRESS (STREET,CITY,PROVINCE,COUNTRY,ZIP,PHONE) VALUES ('"+street+"', '"+city+"', '"+province+"','"+ country+"','"+zip+"','"+phone+"')";
				 
				Connection con = this.ds.getConnection();
				Statement p = con.createStatement();
				int r = p.executeUpdate(query);
				System.out.println("Address Info");
				System.out.println(r);
				p.close();
				con.close();
				   
	}
}
