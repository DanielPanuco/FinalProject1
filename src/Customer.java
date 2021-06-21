/**
 * Customer.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */

import java.text.DecimalFormat;

public class Customer extends User {
	private String address, city, state, username;
	private int zip;
	private List<Order> unshippedOrders, shippedOrders;

	DecimalFormat df = new DecimalFormat("$###,##0.00");

	public Customer(String email, String password) {
		super(email, password);
		this.address = "address unknown";
		this.city = "city unknown";
		this.state = "state unknown";
		this.zip = 00000;
	}
	
	public Customer(String firstName, String lastName, String email)  {
		super(firstName, lastName, email);
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
		//TODO: Are we using this? if not delete it
		super(firstName, lastName, email, password);
		this.username = username;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.unshippedOrders = unshippedOrders; 
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
	
	public void placeUnshippedOrder(Order order) {
        this.unshippedOrders.addLast(order);
    }

    public void placeShippedOrder(Order order) {
        this.shippedOrders.addLast(order);
    }

	public void removeUnshippedOrder(Order order) {
		unshippedOrders.placeIterator();
		while (!unshippedOrders.offEnd()) {
			if (unshippedOrders.getIterator().equals(order)) {
				unshippedOrders.removeIterator();
				return;
			}
			unshippedOrders.advanceIterator();
		}
	}

	public void removeShippedOrder(Order order) {
		shippedOrders.placeIterator();
		while (!shippedOrders.offEnd()) {
			if (shippedOrders.getIterator().equals(order)) {
				shippedOrders.removeIterator();
				return;
			}
			shippedOrders.advanceIterator();
		}
	}
	
	public void viewUnshippedOrders() {
		String divider = "-------------------------------------------------"
				 		+"-----------------------------------------";
		String t2 = "\t\t", t3 = "\t\t\t", t4 = "\t\t\t\t";
		if (unshippedOrders.isEmpty()) {
			System.out.println("You don't have any unshipped orders!");
		} else {
			unshippedOrders.placeIterator();
			for (int i = 0; i < unshippedOrders.getLength(); i++) {
				List<VideoGame> vgList = unshippedOrders.getIterator()
						.getOrderContents();
				vgList.placeIterator();
				System.out.println(t4 + "[Order #" + (i + 1) + "]\n"
						+ " Qty\t" + "     Price" + t4 + "Title\n" + divider);
				for (int j = 0; j < vgList.getLength(); j++) {
					VideoGame currVG = vgList.getIterator();
					System.out.println((j + 1) + ":" + t2 + "  "
							+ df.format(currVG.getPrice()) + "\t" + currVG.getTitle() 
							+ " (" + currVG.getPlatform() + ")");
					vgList.advanceIterator();
				}
				
				int tempShippingSpeed = unshippedOrders.getIterator().getShippingSpeed();
				System.out.println();
				System.out.println("\n" + divider);
				unshippedOrders.getIterator().displayPriceCalculation(vgList, tempShippingSpeed);
				unshippedOrders.advanceIterator();
			}
		}
	}

	public void viewShippedOrders() {
		String divider = "----------------------------------------------------------------------------------";
		String t2 = "\t\t", t3 = "\t\t\t", t4 = "\t\t\t\t";
		if (shippedOrders.isEmpty()) {
			System.out.println("You don't have any unshipped orders!");
		} else {
			shippedOrders.placeIterator();
			for (int i = 0; i < shippedOrders.getLength(); i++) {
				List<VideoGame> vgList = shippedOrders.getIterator()
						.getOrderContents();
				vgList.placeIterator();
				System.out.println(t4 + "[Order #" + (i + 1) + "]\n"
						+ " Qty\t" + "     Price" + t4 + "Title\n" + divider);
				for (int j = 0; j < vgList.getLength(); j++) {
					VideoGame currVG = vgList.getIterator();
					System.out.println((j + 1) + ":" + t2
							+ df.format(currVG.getPrice()) + "\t" + currVG.getTitle() 
							+ " (" + currVG.getPlatform() + ")");
					vgList.advanceIterator();
				}
				int tempShippingSpeed = shippedOrders.getIterator().getShippingSpeed();
				System.out.println();
				System.out.println("\n" + divider);
				shippedOrders.getIterator().displayPriceCalculation(vgList, tempShippingSpeed);
				shippedOrders.advanceIterator();

			}
		}
	}
	
	@Override public String toString() {
		String result = username + "\n"
				+ super.toString()
				+ address + "\n"
				+ city + "\n"
    		    + state + "\n"
    		    + zip + "\n\n"
				+ unshippedOrders.getLength() + "\n"
    		    + unshippedOrders
				+ "\n"
				+ shippedOrders.getLength() + "\n"
    		    + shippedOrders
				+ "\n";
		return result;
	}
	
		public boolean equals(Object o, String temp) {
        if(o == this) {
            return true;
        } else if (!(o instanceof Customer)) {
            return false;
        } else {
            Customer cust = (Customer) o;
            if (cust.getFirstName().equals(((Customer) o).getFirstName()) && cust.getLastName().equals(((Customer) o).getLastName())) {
                return true;
            } else {
                return false;
            }
        }
    }

	public void displayCustomer() {
		System.out.println("Email: " + getEmail());
		System.out.println("Username: " + getUsername());
		System.out.println("Name: " + getFirstName() + " " + getLastName());
		System.out.println("City and state: " + getCity() + ", " + getState());
	}
}
