package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PurchaseOrderDAO {

	private DataSource ds;

	public PurchaseOrderDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void insertPurchaseOrderInfo(String lName, String fName, String status, int addressID, long orderNumber) throws
	SQLException{
		String query = "INSERT INTO PO (LNAME,FNAME,STATUS,ADDRESSID,ID) VALUES ('"+lName+"', '"+fName+"', '"+status+"',"+addressID+", "+orderNumber+")";
				 
				Connection con = this.ds.getConnection();
				Statement p = con.createStatement();
				int r = p.executeUpdate(query);
				System.out.println("insertPurchaseOrderInfo");
				System.out.println(r);
				p.close();
				con.close();
				   
	}
	
}
