package beans;

public class PurchaseOrderBean {


	String fName;
	String lName;
	String status;// is it processed or not
	int addressID;
	long orderNumber;
	
	public PurchaseOrderBean(String lName, String fName, String status, int addressID, long orderNumber)
	{
		this.fName = fName;
		this.lName = lName;
		this.status = status;
		this.orderNumber= orderNumber;
		this.addressID = addressID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	
	
}
