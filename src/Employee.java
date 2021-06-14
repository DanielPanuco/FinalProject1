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
			String lastName, HashTable<Customer> customers) {
		return customers.get(new Customer(firstName, lastName));
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

	public static User login(String email, String password, HashTable<User> users) throws NullPointerException {
		if(users == null) {
			throw new NullPointerException("Employee login(): cannot find employee list\n");
		}
		if(!(users.contains(new Employee(email, password)))) {
			System.out.println("Invalid email or password!\nPlease type again\n");
			return null;
		} else {
			System.out.println("Successfully login!\n");
			return users.get(new Employee(email, password));
		}
	}	

	public String toString() {
		return super.toString();
	}
}
