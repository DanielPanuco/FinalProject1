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
	
	public static Customer searchCustomer(String email, String password, HashTable<Customer> customers) {
		return customers.get(new Customer(email, password));
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
	
	public static void addProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame) {
		//byTitle.insert(videogame,titleComparator);
		//byDate.insert(videogame,dateComparator)
	}
	
	public static void removeProduct(BST<VideoGame> byTitle, BST<VideoGame> byDate, VideoGame videogame) {
		//byTitle.remove(videogame, titleComparator);
		//byDate.remove(videogame, dateComparator);
	}
	

	public static User login(String email, String password, HashTable<User> users) throws NullPointerException {
		if(users == null) {
			throw new NullPointerException("Employee login(): cannot find employee list\n");
		}
		if(!users.contains(new Employee(email, password))) {
    		System.out.println("Invalid email or password!\nPlease type again\n");
    		return null;
    	}else {
    		System.out.println("Successfully login!\n");
    		return users.get(new Employee(email, password));
    	}
    }	
	
	public String toString() {
		return super.toString();
	}
}
