/**
 * Customer.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */

import java.text.DecimalFormat;

public class Customer extends User {
	//TODO: Are we @Overriding equals? If not it shouldn't be
	//in the abstract user parent class
	private String address, city, state, username;
	private int zip;
	//private double card; //credit card/debit card
	private List<Order> unshippedOrders, shippedOrders;
	DecimalFormat df = new DecimalFormat("$###,##0.00");
	
	Customer() { //TODO: ask prof parrish if this is necessary

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
		this.unshippedOrders = new List<>(); //TODO: are we using these lists?
		this.shippedOrders = new List<>();
	}
	
	public Customer(String firstName, String lastName, String email, 
			String address, String city, String state, int zip) { //Guest Constructor
		//good constructor should assign to every var in calss
		super(firstName, lastName, email, "NA");
		this.username = "NA";
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
		this.unshippedOrders = unshippedOrders; //we're actually using this constructor for cust w/orders
		this.shippedOrders = shippedOrders; //addorder or setter if we don't use this
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
		if (users == null) { //move to userInterface
			throw new NullPointerException("Customer login(): cannot find customer list\n");
		}
		if(!users.contains(new Customer(email, password))) {
    		System.out.println("Invalid email or password!\nPlease type again\n");
    		return null;
    	} else {
    		System.out.println("Successfully login!\n");
    		return users.get(new Customer(email, password));
    	}
    }	
	
	public static VideoGame searchVGLByTitle(String title, BST<VideoGame> byTitle) { //VGL = Video Game List
		TitleComparator tc = new TitleComparator();
		return byTitle.search(new VideoGame(title), tc);
	}
	
	public void placeUnshippedOrder(Order order) {
        this.unshippedOrders.addLast(order);
    }

    public void placeShippedOrder(Order order) {
        this.shippedOrders.addLast(order);
    }
	
	public void viewUnshippedOrders() {
		double totalP = 0;
		String divider = "---------------------------------------------------------";
		String t2 = "\t\t", t3 = "\t\t\t";
		//TODO: Can I make these be member var b/c I use them more than once
		if (unshippedOrders.isEmpty()) {
			System.out.println("You don't have any unshipped orders!");
		} else {
			unshippedOrders.placeIterator();
			for (int i = 0; i < unshippedOrders.getLength(); i++) {
				List<VideoGame> vgList = unshippedOrders.getIterator()
						.getOrderContents();
				vgList.placeIterator();
				System.out.println(t2 + "[Order #" + (i + 1) + "]\n"
						+ " Qty\tPrice" + t3 + "Title\n" + divider);
				for (int j = 0; j < vgList.getLength(); j++) {
					VideoGame currVG = vgList.getIterator();
					System.out.println((j + 1) + ":\t"
							+ df.format(currVG.getPrice()) + t2 + currVG.getTitle() 
							+ " (" + currVG.getPlatform() + ")" +t3);
					totalP += currVG.getPrice();
					vgList.advanceIterator();
				}
				unshippedOrders.advanceIterator();
			}
			System.out.println("\n" + divider +"\nTotal:  " + df.format(totalP));
		}
	}

	public void viewShippedOrders() {
		double totalP = 0;
		String divider = "---------------------------------------------------------";
		String t2 = "\t\t", t3 = "\t\t\t";
		if (shippedOrders.isEmpty()) {
			System.out.println("You don't have any shipped orders!");
		} else {
			shippedOrders.placeIterator();
			for (int i = 0; i < shippedOrders.getLength(); i++) {
				List<VideoGame> vgList = shippedOrders.getIterator().getOrderContents();
				vgList.placeIterator();
				System.out.println(t2 + "[Order #" + (i + 1) + "]\n"
						+ " Qty\tPrice" + t3 + "Title\n" + divider);
				for (int j = 0; j < vgList.getLength(); j++) {
					VideoGame currVG = vgList.getIterator();
					System.out.println((j + 1) + ":\t"
							+ df.format(currVG.getPrice()) + t2 + currVG.getTitle() 
							+ " (" + currVG.getPlatform() + ")" +t3);
					totalP += currVG.getPrice();
					vgList.advanceIterator();
				}
				shippedOrders.advanceIterator();
				System.out.println("\n" + divider +"\nTotal:  " + df.format(totalP));
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
