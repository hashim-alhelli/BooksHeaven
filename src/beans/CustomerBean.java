package beans;


public class CustomerBean {


	String fName;
	String lName;
	String email;
	String password;
	String role;
	AddressBean ab;
	
	public CustomerBean(String fName,  String lName, String email, String password,String role)
	{
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.role = role;
	
	}
	
	
	

	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}




	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public AddressBean getAb() {
		return ab;
	}


	public void setAb(AddressBean ab) {
		this.ab = ab;
	}


	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
