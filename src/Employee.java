/**
 * Employee.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */
public class Employee extends User {
		
	public Employee(String email, String password) {
		super(email, password);
	}
	
	public Employee(String firstName, String lastName, 
			String email, String password) {
		super(firstName, lastName, email, password);
	}

	public static Customer searchCustomer(String firstName,
										  String lastName, HashTable<Customer> customersByName) {
		String fullNameKey = firstName + lastName;
		Customer tempCust = new Customer(firstName, lastName, "NA");
		return customersByName.get(tempCust, fullNameKey);
	}
	
	public static void shipOrder(Heap<Order> orders, Order order) {
		//need to know the priority
	}
	
	public static void addProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame,
			TitleComparator tc, DateComparator dc) {
		byTitle.insert(videogame, tc);
		byDate.insert(videogame, dc);
	}
	
	public static void removeProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame,
			TitleComparator tc, DateComparator dc) {
		byTitle.remove(videogame, tc);
		byDate.remove(videogame, dc);
	}

	@Override public String toString() {
		return super.toString();
	}
}
