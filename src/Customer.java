import java.text.DecimalFormat;

public class Customer extends User {
	
	private String address, city, state, username;
	private int zip;
	//private double cash;
	private List<Order> unshippedOrders, shippedOrders;
	DecimalFormat df = new DecimalFormat("$###,##0.00");
	
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
		//call this one when the customers do have both shipped and unshipped orders
		
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
		if (unshippedOrders.isEmpty()) {
			System.out.println("You don't have any unshipped orders!");
		} else {
			unshippedOrders.placeIterator();
			for (int i = 0; i < unshippedOrders.getLength(); i++) {
				List<VideoGame> vgList = unshippedOrders.getIterator()
						.getOrderContents();
				vgList.placeIterator();
				System.out.println("\t\t[Order #" + (i + 1) + "]\n"
						+ " Qty\tPrice\t\t\tTitle\n"
						+ "---------------------------------------------------------");
				for (int j = 0; j < vgList.getLength(); j++) {
					System.out.println((j + 1) + ":\t"
							+ df.format(vgList.getIterator().getPrice())
							+ "\t\t" + vgList.getIterator().getTitle() + " ("
							+ vgList.getIterator().getPlatform() + ")\t\t\t");
					vgList.advanceIterator();
				}
				unshippedOrders.advanceIterator();
			}
		}
	}

	public void viewShippedOrders() {
		if (shippedOrders.isEmpty()) {
			System.out.println("You don't have any shipped orders!");
		} else {
			shippedOrders.placeIterator();
			for (int i = 0; i < shippedOrders.getLength(); i++) {
				List<VideoGame> vgList = shippedOrders.getIterator()
						.getOrderContents();
				vgList.placeIterator();
				System.out.println("\t\t[Order #" + (i + 1) + "]\n"
						+ " Qty\tPrice\t\t\tTitle\n"
						+ "---------------------------------------------------------");
				for (int j = 0; j < vgList.getLength(); j++) {
					System.out.println((j + 1) + ":\t"
							+ df.format(vgList.getIterator().getPrice())
							+ "\t\t" + vgList.getIterator().getTitle() + " ("
							+ vgList.getIterator().getPlatform() + ")");
					vgList.advanceIterator();
				}
				shippedOrders.advanceIterator();
			}
		}
	}

	@Override public String toString() {
		String result = super.toString() + "\nAddress: " + address + "\n"
				+ "City: " + city + "\n"
    		    + "State: " + state + "\n"
    		    + "Zip: " + zip + "\n"
    		    + "Unshipped Orders: " + unshippedOrders + "\n"
    		    + "Shipped Orders: " + shippedOrders + "\n";
		return result;
	}
}
