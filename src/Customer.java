public class Customer extends User {
	
	private String address, city, state, username;
	private int zip;
	//private double cash;
	private List<Order> unshippedOrders, shippedOrders;
	
	Customer() { //ask prof parrish if this is necessary

	}

	public Customer(String email, String password) {
		super(email, password);
		this.address = "address unknown";
		this.city = "city unknown";
		this.state = "state unknown";
		this.zip = 00000;
	}

	public Customer(String username, String firstName, String lastName, String email, String password, 
			String address, String city, String state, int zip) {
		//call this one when the customers doesn't have any existing orders
		super(firstName, lastName, email, password);
		this.username = username;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.unshippedOrders = new List<>();
		this.shippedOrders = new List<>();
	}
	
	public Customer(String username, String firstName, String lastName, String email, String password, 
			String address, String city, String state, int zip,
			List<Order> unshippedOrders, List<Order> shippedOrders) {
		//call this one when the customers does have both shipped and unshipped orders
		
		super(firstName, lastName, email, password);
		this.username = username;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.unshippedOrders = new List<Order>(unshippedOrders);
		this.shippedOrders = new List<Order>(shippedOrders);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public static User login(String username, String email, String password,
			HashTable<User> users) throws NullPointerException {
		if (users == null) {
			throw new NullPointerException("Customer login(): cannot find customer list\n");
		}
		if(!users.contains(new Customer(email, password))) {
    		System.out.println("Invalid email or password!\nPlease type again\n");
    		return null;
    	}else {
    		System.out.println("Successfully login!\n");
    		return users.get(new Customer(email, password));
    	}
    }	
	
	public static void loginAsGuest() {
		System.out.println("You have already logged in as a guest");
	}
	
	public static VideoGame searchGameByTitle(String title, BST<VideoGame> byTitle,
			TitleComparator tc) {
		return byTitle.search(new VideoGame(title), tc);

	}
	
	public static VideoGame searchGameByDate(String title, BST<VideoGame> byDate,
			DateComparator dc) {
		return byDate.search(new VideoGame(title), dc);
	}
	
	public void placeUnshippedOrder(Order order) {
        this.unshippedOrders.addLast(order);
    }

    public void placeShippedOrder(Order order) {
        this.shippedOrders.addLast(order);
    }
	
	public void viewUnshippedOrders() {
		if(unshippedOrders.isEmpty()) {
			System.out.println("No unshipped orders now");
		} else {
			System.out.println(unshippedOrders);
		}
	}
	
	public void viewShippedOrders() {
		if(unshippedOrders.isEmpty()) {
			System.out.println("No shipped orders now");
		} else {
			System.out.println(shippedOrders);
		}
	}
	
	public String toString() {
		String result = "Address: " + address + "\n"
				+ "City: " + city + "\n"
    		    + "State: " + state + "\n"
    		    + "Zip: " + zip + "\n"
    		    + "Unshipped Orders: " + unshippedOrders + "\n"
    		    + "Shipped Orders: " + shippedOrders + "\n"
				+ super.toString();
		return result;
	}
}
