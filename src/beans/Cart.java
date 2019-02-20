package beans;

import java.util.Map;
import java.util.*;

public class Cart {

	public static Map<String,Integer> selectedBooks ;
	private Set<BookBean> set_books;// we use this list to store the book bean objects passed to us from client

	public Cart(){
		selectedBooks = new HashMap<String,Integer>();
		set_books = new HashSet<BookBean>();
	}
	
	// Adds the books to the cart
	public void add_item_to_cart(String id,int quantity,BookBean b){// add an item to the cart with specific quantity

		int tempQuantity=0;
		//System.out.println("the id in map with its value is " + id + "---->"+selectedBooks.get(id));
		if(quantity>0){
			
			if(selectedBooks.get(id) !=null)
			{
				tempQuantity = selectedBooks.get(id);
				quantity = tempQuantity + quantity;
			}
			selectedBooks.put(id, quantity);
			set_books.add(b); // we need to add the bean to the list for easier retrieval
			
		}
		else{
			selectedBooks.remove(id);
			set_books.remove(b);
		}
	}
	
	// Updates the items quantity in cartCheck page
	public void update_items_in_cart(String id,int quantity,BookBean b){// add an item to the cart with specific quantity

		//System.out.println("the id in map with its value is " + id + "---->"+selectedBooks.get(id));
		if(quantity>0){

			selectedBooks.put(id, quantity);
			set_books.add(b); // we need to add the bean to the list for easier retrieval
			
		}
		else{
			selectedBooks.remove(id);
			set_books.remove(b);
		}
	}
	
	// Removes the book from cart
	public void removeBookFromCart(String id,BookBean b){

			selectedBooks.remove(id);
			set_books.remove(b);
	}

//	public void updateQuantityCart(String id,int quantity,BookBean b){// add an item to the cart with specific quantity
//
//
//		//System.out.println("the id in map with its value is " + id + "---->"+selectedBooks.get(id));
//		if(quantity>0){
//			
//			int tempQuantity = selectedBooks.get(id);
//			quantity = tempQuantity + quantity;
//			selectedBooks.put(id, quantity);
//			//set_books.add(b); // we need to add the bean to the list for easier retrieval
//		}
////		else{
////			selectedBooks.remove(id);
////			set_books.remove(b);
////		}
//	}


	public HashSet<BookBean> get_books_in_cart(){

		return new HashSet<BookBean>(this.set_books);
	}
	
	
	public double get_cart_prices(){// get total price of the cart

		// we need to go through each cart item get its bid then get the bean for the item and multiply its price by quantity inmap

		double total = 0;
		System.out.println("set books size is " +set_books.size());
		for(BookBean b:set_books){


			int quantity_in_map = selectedBooks.get(b.getBid());// get the book quantity for that bid

			double price = b.getPrice();// get the price of that specific book

			total += price * quantity_in_map;

		}

		//Set<String> bids = map.keySet();// we get all the bids here

		return total;

	}
	
	public int getTotalQuantity(){// get total price of the cart

		// we need to go through each cart item get its bid then get the bean for the item and multiply its price by quantity inmap

		int total = 0;
		for(BookBean b:set_books)
		{


			int quantity_in_map = selectedBooks.get(b.getBid());// get the book quantity for that bid
			total = total + quantity_in_map;
		}

		return total;

	}




}
