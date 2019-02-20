package beans;

public class AddressBean {

	String street;
	String city;
	String country;
	String province;
	String zip;
	String phone;
	String fname;
	String lname;
	int id;
	
	public AddressBean(String fname, String lname, String street,  String city, String province, String country, String zip,String phone)
	{
		this.street = street;
		this.city = city;
		this.country = country;
		this.province = province;
		this.zip = zip;
		this.phone = phone;
		this.fname = fname;
		this.lname = lname;
	}
	
	public AddressBean(String street,  String city, String province, String country, String zip,String phone)
	{
		this.street = street;
		this.city = city;
		this.country = country;
		this.province = province;
		this.zip = zip;
		this.phone = phone;
		
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
