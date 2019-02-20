import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SoapRequest {
	
	
	
	private DataSource ds;

	public SoapRequest() throws ClassNotFoundException{
		try {
		 ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	public String getProductInfo(String id) throws
	SQLException{
		String query = "select * from book where bid ='" + id+"'";
		String result ="The Book was not found";	
		//String result = "<html><body>" "You scored <b>192</b> points.<br/> hi</body></html>";
		
		ArrayList<String> rv = new ArrayList<String>();
				Connection con = this.ds.getConnection();
				PreparedStatement p = con.prepareStatement(query);
				ResultSet r = p.executeQuery();
				while (r.next()){
					String bid = r.getString("bid");
					rv.add(bid);
					double price = r.getDouble("price");
					rv.add(price+"");
					String category = r.getString("category");
					rv.add(category);
					String orignalTitle = r.getString("title"); 
					//String picture = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
					rv.add(orignalTitle);
					String description = r.getString("description");
					rv.add(description);
					String author = r.getString("author");
					rv.add(author);
					
				}
				r.close();
				p.close();
				con.close();
				if(!rv.isEmpty()){// if the list is not empty them I retrieve the info else it means item not found
				result = "bid= "+ rv.get(0) +"\n" + "price= " + rv.get(1)+"\n"  + "category= " + rv.get(2)+"\n"  + "title= " 
				+ rv.get(3)+"\n"  + "decription = " + rv.get(4) +"\n" + "author = " + rv.get(5)+"\n" ;
				}
				return result;
	}


}
