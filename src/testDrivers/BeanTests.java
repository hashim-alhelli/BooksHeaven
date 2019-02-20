/*package testDrivers;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import beans.AddressBean;
import beans.BookBean;
import beans.Cart;
import beans.CustomerBean;
import beans.ReviewBean;
import beans.VisitEventBean;



/////////////////////////////////////////////////////
// this class must be tested using junit4 so the jar file must be added to the library
public class BeanTests {

	@Test
	public void BookBeantest() {
		BookBean book = new BookBean("b001", "hello", 20, "romance", "b001.img", "description", "hashim");
		
		assertEquals("b001", book.getBid());
		assertEquals("hello", book.getTitle());
		assertEquals(20, book.getPrice(),0.1);
		assertEquals("romance", book.getCategory());
		assertEquals("b001.img", book.getImg_name());
		assertEquals("description", book.getDescription());
		assertEquals("hashim", book.getAuthor());
	}
	
	@Test
	public void CustomerBeantest() {
		CustomerBean customer = new CustomerBean("hashim", "al-Helli", "hashim93a@gmail.com", "123", "admin");
		
		assertEquals("hashim",customer.getfName());
		assertEquals("al-Helli", customer.getlName());
		assertEquals("hashim93a@gmail.com", customer.getEmail());
		assertEquals("123", customer.getPassword());
		assertEquals("admin", customer.getRole());
		
	}
	
	@Test
	public void ReviewBeantest() {
		ReviewBean review = new ReviewBean("bid", "hashim93a@gmail.com", "march 30 2016", "test", "testing", 5);
		
		assertEquals("bid",review.getBid());
		assertEquals("hashim93a@gmail.com", review.getEmail());
		assertEquals("march 30 2016", review.getDate());
		assertEquals("test", review.getReviewTitle());
		assertEquals("testing", review.getReviewDescription());
		assertEquals(5, review.getRating());
		
	}
	
	@Test
	public void VisitEventBeantest() {
		VisitEventBean visit = new VisitEventBean(new Date(), "b001", "visit",0);
		Date day = new Date();
		assertEquals("b001",visit.getBid());
		assertEquals(day, visit.getDay());
		assertEquals("visit", visit.getEventType());
		
	}
	
	@Test
	public void AddressBeantest() {
		AddressBean address = new AddressBean("keele", "toronto", "ontario", "canada", "444", "123");
		AddressBean address2 = new AddressBean("hashim", "hashim", "keele", "toronto", "ontario", "canada", "222", "123");
		
		assertEquals("keele",address.getStreet());
		assertEquals("toronto", address.getCity());
		assertEquals("ontario", address.getProvince());
		assertEquals("canada", address.getCountry());
		assertEquals("444", address.getZip());
		assertEquals("123", address.getPhone());
		
		
		
		assertEquals("hashim",address2.getFname());
		assertEquals("hashim",address2.getLname());
		assertEquals("keele",address2.getStreet());
		assertEquals("toronto", address2.getCity());
		assertEquals("ontario", address2.getProvince());
		assertEquals("canada", address2.getCountry());
		assertEquals("222", address2.getZip());
		assertEquals("123", address2.getPhone());
		
		
		
		
		
	}
	
	
	@Test
	public void Carttest() {
		Cart cart = new Cart();
		
		
		BookBean book = new BookBean("b001", "hello", 20, "romance", "b001.img", "description", "hashim");
		
		
		cart.add_item_to_cart("b001", 50, book);// add a book to cart
		
		assertEquals(1,cart.get_books_in_cart().size()); // check if the book was added to the cart
		
		assertEquals(true,cart.get_books_in_cart().contains(book));// the book must exist in the set
		System.out.println(cart.get_cart_prices());
		assertEquals(50, cart.getTotalQuantity());
		assertEquals(1000, cart.get_cart_prices(),0.1);
		
		
	}

}
*/