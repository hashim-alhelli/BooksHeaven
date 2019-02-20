package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AddressBean;
import beans.BookBean;
import beans.Cart;
import beans.CustomerBean;
import beans.ReviewBean;
import beans.VisitEventBean;
import model.BookStoreModel;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns={"/Start","/Start/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookStoreModel bsm;
	private Cart shopping_cart;
	boolean sign_in_track =false;
	private Map<String,BookBean> bookRV = new HashMap<String, BookBean>();
	Map<String,AddressBean> addressRV = new HashMap<String, AddressBean>();
	Map<String, ReviewBean> reviewRV = new HashMap<String, ReviewBean>();
	Map<String,CustomerBean> loginRV = new HashMap<String, CustomerBean>();
	ArrayList<VisitEventBean> statusViewRV  = new ArrayList<VisitEventBean>(); 
	ArrayList<VisitEventBean> statusCartRV  = new ArrayList<VisitEventBean>(); 

	long po_number;
	boolean ordered = false;
	String loggedInRole = "";
	String checkRole = "";
	
	int cartSize = 0;
	
	
	// added me 
		int hitcounter = 0;
	    // added me
		String orderStatus = "";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException
    {
    	super.init();
    	// ServletContext context = getServletContext();
    	try {
			getServletContext().setAttribute("bsm", new BookStoreModel());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 bsm = (BookStoreModel) getServletContext().getAttribute("bsm");
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        Cart shoppingCart;
        orderStatus = ""; // added me
        shoppingCart = (Cart) session.getAttribute("cart");
        if(shoppingCart == null)
        {
          shoppingCart = new Cart();
          session.setAttribute("cart", shoppingCart);
          session.setAttribute("cartSize", cartSize);
        }
		
		// Default target, go to home page
		String target = "/Home.jspx";
		
		String runReports = request.getParameter("runReports");
		
		// URL forwarding variable forward to know request is coming from which page
		
		String forward = request.getParameter("forward");
		
		// Login page parameters
		String loginEmail = request.getParameter("signInEmail");
		
		String signInPassword = request.getParameter("signInPassword");
		String signInButton = request.getParameter("signInButton");// THIS is the button in login page
		
		String category = request.getParameter("category");// get the category
		
		// Button Parameters
		String register = request.getParameter("Register");
		String update = request.getParameter("update");
		String removeBook = request.getParameter("removeBook");
		String checkout = request.getParameter("checkout");
		
		// Parameters for Register Page
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		String province = request.getParameter("province");
		String phone = request.getParameter("phone");
		
		// Billing address variables 
		String streetBilling = request.getParameter("streetBilling");
		String cityBilling = request.getParameter("cityBilling");
		String postalCodeBilling = request.getParameter("postalCodeBilling");
		String countryBilling = request.getParameter("countryBilling");
		String provinceBilling = request.getParameter("provinceBilling");
		String phoneBilling = request.getParameter("phoneBilling");
		
		// Reviews parameters 
		 String reviewTitle = request.getParameter("reviewTitle");
		 String reviewDescription = request.getParameter("reviewDescription");
		 String bookRating = request.getParameter("rating");
		 String submitReview = request.getParameter("submitReview");
		 
		String paymentRegister = request.getParameter("paymentRegister");
		
		// Category page add to cart button 
		String quantity = request.getParameter("quantity");
		String add_to_cart = request.getParameter("AddToCart");
		//String updateCartButton = request.getParameter("updateCart");
		
		//get the book title from the search		
		String book_title = request.getParameter("searchstring");
		
		//check if the image was clicked
		String image_clicked = request.getParameter("image");// THIS IS THE BOOK NAME
		
		
		// check if the user is purchasing as a guest
		String continue_guest_purchase = request.getParameter("continue_as_guest");
		String orderSubmitted = request.getParameter("submitOrder");
		
		
		
		// Checking for report button for the number of books sold based on the title of the book
		String bookSold = request.getParameter("bookSold");
		String reportBook = request.getParameter("BookReport");
		String month = request.getParameter("month");
		
		// Go to login page and show sign in fields	
		if (forward!= null && forward.equals("sign_in"))
		{
			target = "/Login.jspx";
		}
		
		// Go to login page and show register fields 
		if (register != null && !register.isEmpty())
		{
			target = "/Login.jspx";
			request.setAttribute("rButton", register);
			
		}		
		
		// Book image clicked for full description
		if(image_clicked!=null){
			target="/book_description.jspx";
			request.getSession().setAttribute("book_title", book_title);
			try {

				//System.out.println(image_clicked);
				bookRV = bsm.retriveBytitle(image_clicked);
				session.setAttribute("titleRV", bookRV);
				session.setAttribute("bookBID", bookRV.get(image_clicked).getBid());

				// Getting the reviews from database to show on description page
				String bookBID = (String) request.getSession().getAttribute("bookBID");				 
				String customerCID = (String) request.getSession().getAttribute("customerEmail");//added

				//reviewRV = bsm.retrieveCustomerReview(bookBID); // old before robin addition
				reviewRV = bsm.retrieveCustomerReview(bookBID,customerCID);//added
				double rating = bsm.getRating();//added
				request.setAttribute("rating", rating);//added


				session.setAttribute("reviewRV", reviewRV);
				//checks if user reviewed current book
				boolean reviewed = bsm.customerReviewed();
				System.out.println("book was revieeewed" + reviewed);
				request.setAttribute("reviewed", reviewed);

				// Adds the view entry in Visit Event table 
				bsm.insertVisitEventInfo(bookRV.get(image_clicked).getBid(), "VIEW");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
				
		//check for the search by title and go to it		
		if(book_title!=null){
			target="/book_title.jspx";
			request.getSession().setAttribute("book_title", book_title);
			try {

				//System.out.println(book_title);
				bookRV = bsm.retriveBytitle(book_title);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("titleRV", bookRV);
			
		}
		
		// Go to cart page
		if (forward!= null && forward.equals("view_cart"))
		{
			target = "/CartCheck.jspx";
			
			request.getSession().setAttribute("set_books", shoppingCart.get_books_in_cart());
			//System.out.println(shoppingCart.get_books_in_cart().size());
			
		}
		
		// Go to Item page and show the books according to category 
		if ((category!= null && category.matches("Art|History|Computers|Sports|Food|Romance|Finance|Science")))
		{   
			
			//forward = (String)request.getAttribute("forward");
			target = "/Item.jspx";
			try {			
				bookRV = bsm.retriveByCategory(category);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("category", category);
			request.getSession().setAttribute("categoryRV", bookRV);// this list must exist the whole session
		}
		
		
		// Creates account for user when create Account button is clicked, puts info into database 
		if (request.getParameter("createAcnt") != null)
		{ 
			target="/Login.jspx";
			
			try {
				
				bsm.insertAddressInfo(street, city, province, country, zip, phone);
				bsm.inserttoCustomerInfo(firstName, lastName, email, password,"customer");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String rError = "The email already exsits";
				request.setAttribute("rError",rError);
				e.printStackTrace();
			}
		}
		
		// If sign out is clicked then sing out and go to home page
		if (forward!= null && forward.equals("sign_out"))
		{
			target = "/Home.jspx";
			session.removeAttribute("loggedInAs");
			session.removeAttribute("loggedInRole");
			forward=null;
			
		}
		
		// Authenticates user sign in information, if user already exists returns 
		// failed message otherwise shows user's first name
		if (signInButton !=null && signInPassword != null && !signInPassword.isEmpty() && loginEmail != null && !loginEmail.isEmpty())
		{
			try {
				//String message = bsm.checkLogin(loginEmail, signInPassword);
				loginRV = bsm.checkLogin(loginEmail, signInPassword);
    
				String loginMessage = "";
				//System.out.println(message);
				//if (message != null && !message.isEmpty())
				if (loginRV != null && !loginRV.isEmpty())
				{
					target = "/Home.jspx";
					session.setAttribute("loggedInAs", loginRV.get(loginEmail).getfName());
					checkRole = loginRV.get(loginEmail).getRole();
					if(shoppingCart.get_books_in_cart().size()>0 && sign_in_track == true){ // if the user has something in his cart then he probably came from checkout page not really the case

						target="/payment.jspx";
						sign_in_track = false;
					}
				}
				else{

					target = "/Login.jspx";
					loginMessage = "hello";
					System.out.println("I am in loginmessage");
					request.setAttribute("loginMessage", loginMessage);
				}

				
				// Check for role and show the runreport button on home button accordingly 
				
				if(checkRole.equals("admin"))
				{
					System.out.println(checkRole);
				session.setAttribute("loggedInRole", checkRole);
				}
				session.setAttribute("customerEmail", loginEmail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Adding books to the cart
		if(add_to_cart!=null){

			target="/Item.jspx";
			// check if the add to cart button was clicked from the search page

			if(request.getSession().getAttribute("book_title")!=null && category==null){ 
				target="/book_title.jspx";
				//System.out.println("yes its a book_title");
			}


			try {
				// this return the map of string to bookbean
				BookBean book = bsm.retriveByID(add_to_cart);

				// get the shopping cart from session
				shoppingCart = (Cart)session.getAttribute("cart");

				shoppingCart.add_item_to_cart(add_to_cart, Integer.parseInt(quantity), book);
				cartSize = shoppingCart.getTotalQuantity();
				// Adds the cart entry in Visit Event table
				bsm.insertVisitEventInfo(add_to_cart, "CART");

				//System.out.println("the bookrv we pass to cart is "+ bookRV.get(add_to_cart).getBid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//shopping_cart.add_item_to_cart(add_to_cart, quantity, bookRV.get(add_to_cart));
			add_to_cart = null;

		}
		
		// Go to cart page when update button is clicked in cart page and update the items information accordingly 
		if(update !=null)
		{
			target = "/CartCheck.jspx";
			try {
				BookBean book = bsm.retriveByID(update);// this return the map of string to bookbean
				shoppingCart = (Cart)session.getAttribute("cart");// get the shoping cart from session
				shoppingCart.update_items_in_cart(update, Integer.parseInt(quantity), book);
				cartSize = shoppingCart.getTotalQuantity();

				//System.out.println("the bookrv we pass to cart is "+ bookRV.get(add_to_cart).getBid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			update= null;
			//request.getSession().setAttribute("total_price", shoppingCart.get_cart_prices());// save the book price

			System.out.println(shoppingCart.get_books_in_cart().size());
		}
		
		if(removeBook != null)
		{	
			target = "/CartCheck.jspx";
		
		try {
			
			BookBean book = bsm.retriveByID(removeBook);// this return the map of string to bookbean
			shoppingCart = (Cart)session.getAttribute("cart");// get the shoping cart from session
			shoppingCart.removeBookFromCart(removeBook, book);
			cartSize = shoppingCart.getTotalQuantity();

			//System.out.println("the bookrv we pass to cart is "+ bookRV.get(add_to_cart).getBid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			removeBook= null;
		}
		
		// When checkout button is clicked, goes to payment page 
		if(checkout !=null)
		{
			// we check for the user	
			// this means he clicked checkout
			sign_in_track = true;
			
			// if the customer is logged and nothing in cart then stay on cartcheck page when checkout button is clicked
			if(session.getAttribute("loggedInAs") !=null && shoppingCart.get_books_in_cart().size()==0){
				
				target = "/CartCheck.jspx";
			
			}			
			
			// if customer is logged in and press the checkout on cart page then go to payment page
			else if(session.getAttribute("loggedInAs") !=null && shoppingCart.get_books_in_cart().size()> 0){
			
				target = "/payment.jspx";
				String customerEmail = (String) session.getAttribute("customerEmail");
				System.out.println("x is "+customerEmail);
				try {
					addressRV = bsm.retrieveCustomerInfoByEmail(customerEmail);
					session.setAttribute("addressRV", addressRV);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				target = "/guestPurchase.jspx";
			}
			
		}
		
		if (request.getParameter("paymentSignIn") != null)
		{
			target = "/Login.jspx";
		}
		if (paymentRegister != null)
		{
			target = "/Login.jspx";
			request.setAttribute("rButton", paymentRegister);
		}		
		
		//if the user is continuing with guest purchase then send him to payment		
		if(continue_guest_purchase!=null){
			target="/payment.jspx";
		}
		
		// If review order button in payment page is clicked then go to ReviwAndSubmit.jspx
		if (request.getParameter("reviewOrder") !=null)
		{
			//guestcheckout get certain variables
			if ((String)session.getAttribute("loggedInAs")==null){
				request.setAttribute("name", request.getParameter("userName"));
				request.setAttribute("street", street);
				request.setAttribute("city", city);
				request.setAttribute("province", province);
				request.setAttribute("country", request.getParameter("country"));
				request.setAttribute("zip", request.getParameter("postalCode"));
				request.setAttribute("phone", request.getParameter("phone"));
				
				request.setAttribute("nameBilling", request.getParameter("userNameBilling"));
				request.setAttribute("streetBilling", request.getParameter("streetBilling"));
				request.setAttribute("cityBilling", request.getParameter("cityBilling"));
				request.setAttribute("provinceBilling", request.getParameter("provinceBilling"));
				request.setAttribute("countryBilling", request.getParameter("countryBilling"));
				request.setAttribute("phoneBilling", request.getParameter("phoneBilling"));

				System.out.println("tis guest");
			}else{
				//logged in user
				
			}
			
			target = "/ReviewAndSubmit.jspx";
			request.setAttribute("creditFirstName", request.getParameter("firstName"));
			request.setAttribute("creditLastName", request.getParameter("lastName"));
			request.setAttribute("creditNumber", request.getParameter("cardNumber"));
			request.setAttribute("creditDate", (String)request.getParameter("date"));
			
			System.out.println(city+(String)request.getParameter("city"));
		}
		
		
		if(submitReview != null)
		{
			target = "/book_description.jspx";
			// Saving the review in the database
			String customerEmail = (String) session.getAttribute("customerEmail");
			System.out.println("x is "+customerEmail);
			try {
			
			 String bookBID = (String) request.getSession().getAttribute("bookBID");
			 System.out.println("bookBID is  "+bookBID);
			 
			 String rPattern = "(<)|(>)|(<script)|(=)";
			 Pattern pattern = Pattern.compile(rPattern);
			 Matcher m = pattern.matcher(reviewTitle);
			 Matcher m2 = pattern.matcher(reviewDescription);
			 
			 boolean answer = !(m.find())&&!(m2.find());
			 //checks if review is valid
			 if(answer){
			  bsm.insertCustomerReview(bookBID, customerEmail, bookRating,  reviewTitle,  reviewDescription);
			  request.setAttribute("reviewInvalid", false);
			  request.setAttribute("reviewed", true);
			 }else{
				 request.setAttribute("reviewInvalid", true);
				 request.setAttribute("reviewed", false);
				 System.out.println("invalid");
			 }
			  // Getting the reviews from database
			  reviewRV = bsm.retrieveCustomerReview(bookBID,customerEmail);// was only bookid
			  //storing the reviews in the session
			  session.setAttribute("reviewRV", reviewRV);
			  //calculating and setting the rating
			  double rating = bsm.getRating();
			  request.setAttribute("rating", rating);
			  
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// if the order was submitted then go to orderSubmitted page
		// added me updated it
				if(orderSubmitted!=null){
					target = "/OrderSubmitted.jspx";
					
					hitcounter++;// start incrementing the counter if it reaches 3 then it is bad we reject him
					 if(hitcounter>2){
						 hitcounter=0;
						 
						 orderStatus= "rejected";
					 }
					 getServletContext().setAttribute("hitcounter", hitcounter);
					
					
					
					// creater the order number and show it to the user
					
					Random random = new Random();
					
					if(session.getAttribute("po_number")== null && ordered==false){
						po_number = Math.abs(10000000 * random.nextLong()+ 1);
						
					}
				
					
					session.setAttribute("po_number", po_number);
					//String nameFirst = (String) session.getAttribute("loggedInAs");
					try {
						
						if(orderStatus.isEmpty()) {
							// if the orderstatus is not rejected then we insert
						
						bsm.insertPurchaseOrderInfo((String)session.getAttribute("loggedInAs"), (String)session.getAttribute("loggedInAs"), "ORDERED", 1, po_number);
						
						session.removeAttribute("cart");
						cartSize = 0;
//						session.removeAttribute("total_price");
//						session.removeAttribute("set_books");
//						session.removeAttribute("selectedBooks");
						System.out.println("We inserted into PO");
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(po_number);
				
				}
				
				// end of added me
		
		// If runreport button is clicked then got to analytics
		if(runReports !=null)
		{
			target = "/Analytics.jspx";			
		}
		
		
		if (request.getParameter("bookViewReport") != null)
		{
			target = "/Analytics.jspx";	
			if (month != null)
			{
				try {
				statusCartRV = bsm.retrieveReportByCart(month);
				//System.out.println("StatusCARTRV" + statusCartRV.size());
				statusViewRV = bsm.retrieveReportByView(month);
				//System.out.println("StatusVIEWRV" + statusViewRV.size());
				request.setAttribute("statusCartRV", statusCartRV);
				for (VisitEventBean r : statusCartRV)
				{
					System.out.println(r.getBid());
				}
				request.setAttribute("statusViewRV", statusViewRV);
					System.out.println("Method Retrieving view");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		// Set the total_price at session level it can be retrieved in jspx accordingly
		session.setAttribute("cartSize", cartSize);
		session.setAttribute("total_price", shoppingCart.get_cart_prices());// save the cart total price
		session.setAttribute("set_books", shoppingCart.get_books_in_cart());
		session.setAttribute("selectedBooks", shoppingCart.selectedBooks);
		
		session.setAttribute("orderstatus", orderStatus);
		// Dispatch according to target	
		request.getRequestDispatcher(target).forward(request, response);
		session.removeAttribute("po_number");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
