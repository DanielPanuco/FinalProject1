/**
 * Employee.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */
public class Employee extends User {
	
	public Employee(){
		super();
	}
	
	public Employee(String email, String password) {
		super(email, password);
	}
	
	public Employee(String firstName, String lastName, 
			String email, String password) {
		super(firstName, lastName, email, password);
	}
	
	public Employee(String firstName, String lastName, 
			String email, String password, int accountNum) {
		super(firstName, lastName, email, password);
		this.setAccountNum(accountNum);
	}
	
	public static Customer searchCustomer(String firstName,
			String lastName, HashTable<Customer> customersByName) {
		return customersByName.get(new Customer(firstName, lastName), firstName+lastName);
	}

	public static void displayCustomer(Customer customer) {
		System.out.println(customer);
	}
	
	public static void viewOrders(Heap<Order> orders) {
		System.out.println(orders);
	}
	
	public static void shipOrder(Heap<Order> orders, Order order) {
		//need to know the priority
	}
	
	public static void listByTitle(BST<VideoGame> byTitle) {
		if(byTitle != null) {
			byTitle.inOrderPrint();
		} else {
			System.out.println("Video Game list doesn't exist");
		}
	}
	
	public static void listByDate(BST<VideoGame> byDate) {
		if(byDate != null) {
			byDate.inOrderPrint();
		} else {
			System.out.println("Video Game list doesn't exist");
		}
	}
	
	public static void addProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame,
			TitleComparator tc, DateComparator dc) {
		byTitle.insert(videogame,tc);
		byDate.insert(videogame,dc);
	}
	
	public static void removeProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame,
			TitleComparator tc, DateComparator dc) {
		byTitle.remove(videogame, tc);
		byDate.remove(videogame, dc);
	}

	public String toString() {
		return super.toString();
	}
}
