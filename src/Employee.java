public class Employee extends User {
	
	public Employee(){
		super();
	}
	
	public Employee(String firstName, String lastName, 
			String email, String password) {
		super(firstName, lastName, email, password);
	}
	
	public static Customer searchCustomer(String name, HashTable<Customer> customers) {
		return null;
	}
	
	public static void displayCustomer(Customer customer) {
		
	}
	
	public static void viewOrders(Heap<Order> orders) {
		
	}
	
	public static void shipOrder(Heap<Order> orders, Order order) {
		//orders.remove(order);
	}
	
	public static void listByTitle(BST<VideoGame> byTitle) {
		
	}
	
	public static void listByDate(BST<VideoGame> byDate) {
		
	}
	
	public static void addProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame) {
		
	}
	
	public static void removeProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame) {
		
	}
	
	public static void login(String email, String password) {
		
	}
	
	public String toString() {
		return null;
	}
}
