public class Customer extends User {
	
	private String address;
	private String city;
	private String state;
	private String zip;
	private List<Order> unshippedOrders;
	private List<Order> shippedOrders;
	
	Customer() {

	}

	public Customer(String email, String password) {
		super(email, password);
		this.address = "address unknown";
		this.city = "city unknown";
		this.state = "state unknown";
		this.zip = "zip unknown";
		this.unshippedOrders = new List<>();
		this.shippedOrders = new List<>();
		
	}

	public Customer(String firstName, String lastName, String email, String password, 
			String address, String city, String state, String zip) {
		super(firstName, lastName, email, password);
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.unshippedOrders = new List<>();
		this.shippedOrders = new List<>();
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public static void login(String email, String password, HashTable<Customer> customers) {
    	//overwrite
    }

	public static void loginAsGuest(String email, String password) {
		
	}
	
	public static VideoGame searchGameByTitle(String title, BST<VideoGame> byTitle) {
		return null;
	}
	
	public static VideoGame searchGameByDate(String title, BST<VideoGame> byDate) {
		return null;
	}
	
	public void placeOrder(Order order) {
		//this.unshippedOrders.addLast(order);
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
		return null;
	} 

}
