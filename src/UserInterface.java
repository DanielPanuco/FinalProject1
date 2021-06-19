/**
 * UserInterface.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */

import java.io.*;
import java.lang.reflect.Array; //TODO: unused
import java.util.ArrayList;
import java.util.PriorityQueue; //TODO: unused
import java.util.Scanner;

public class UserInterface {
	private static final String vgFile = ("product.txt"),
			custFile = ("customers.txt"), empFile = ("employees.txt");
	public static String fName, lName, email, addr, city, state, pw, title,
						 username;
	public static String fullNameKey = fName + lName;
	public static String emailPWKey = email + pw; 
	public static int zip;
	public static Customer currentC = null;
	public static Employee currentEmp = null;
	public static final TitleComparator tc = new TitleComparator();
	public static final DateComparator dc = new DateComparator();
	public static final OrderComparator oc = new OrderComparator();
	private static Scanner input;
	//TODO: maybe change to videoGames.txt, once we decide on a String txt
	//name it's good to make it final (this is taught in 36B)
	//(if we don't want to end up changing it later on)
	
    public static void main(String[] args) {
        final int custSize = 5, empSize = 3;
        int userType;
        HashTable<Customer> custHT = new HashTable<>(custSize * 2);
        HashTable<Customer> custByName = new HashTable<>(custSize * 2);
        HashTable<Employee> empHT = new HashTable<>(empSize * 2);
        BST<VideoGame> vgByTitle = new BST<>();
        BST<VideoGame> vgByDate = new BST<>();
		ArrayList<Order> tempOrderAl = new ArrayList<>();
		tempOrderAl.add(null);
		Heap<Order> priorityQueue = new Heap<>(tempOrderAl, oc);
        

		try {
			fileToVG(vgByTitle, vgByDate);
			fileToCustandPQ(custHT, custByName, vgByTitle, priorityQueue);
			fileToEmp(empHT);
		} catch (FileNotFoundException e) {
			System.out.println("File(s) not found, please make sure it is in the project"
					+ "folder and rereun the program.");
		}
		//need to be called for it to happen
        //System.out.println(custHT); //test printing
        //System.out.println(custByName);
        //System.out.println(empHT);
		input = new Scanner(System.in);
        System.out.println("Welcome to [Insert Video Game Store Title Here]! \n");
        //System.out.println("Please note that we don't offer refunds after you place your orders!");
        //maybe mention credit/debit card only
        System.out.println("[Please select your user type]\n"
        		+ "1. Customer\n"
        		+ "2. Employee");
        System.out.print("\nPlease enter 1 or 2: ");
        userType = input.nextInt();
        if (userType == 1) {
            custInterface(custHT, custByName, vgByTitle, vgByDate);
        } else {
            empInterface(vgByDate, vgByDate, custHT, custByName, empHT, priorityQueue);
        }
        
        try {
			customerToFile(custHT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public static void custAccSetup(HashTable<Customer> custHT,
			HashTable<Customer> custByName, BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) {
    	String ans;
    	String createAcc ="Let's create an account for you!\n";
		String enterUsername = "Enter your username: "; //only do this if it's clear
		String createPW = "Create a password:";
		String success = "\nYou have succesfully created an account,"
							+ fName + " " + lName + "!\n";
		input.nextLine(); // clear buffer from reading
		System.out.println("\nWelcome to our store, please login here!");
		System.out.println("\n[Choose your customer type]\n"
	        		+ "1. Guest\n"
	        		+ "2. New Customer\n"
	        		+ "3. Existing Customer");
		 	System.out.print("\nPlease enter 1, 2, or 3: ");
	        ans = input.nextLine();
	        if (ans.equals("1")) {
				System.out.println("\nPlease start by filling out"
						+ "your contact info!\n");
	        	createAccount();
				currentC = new Customer(fName, lName, email, addr,
						city, state, zip);
				System.out.println("\nYou have succesfully created an account!");
	        } else if (ans.equals("2")) {
				createAccount();
				System.out.println(createAcc);
    			System.out.print(enterUsername);
    			username = input.nextLine();
    			System.out.println(createPW);
    			pw = input.nextLine();
				currentC = new Customer(username, fName, lName, email, pw, addr,
						city, state, zip);
				custHT.insert(currentC, emailPWKey);
				custByName.insert(currentC, emailPWKey);
				System.out.println(success);
	        } else if (ans.equals("3")){
	        	System.out.print("Enter your email address: ");
	    		email = input.nextLine();
	    		System.out.print("Enter your password: ");
	    		pw = input.nextLine();
	    		Customer tempC = new Customer(email, pw);
	    		if (!(custHT.contains(tempC, emailPWKey))) {
					System.out.println("\nIt appears we don't have "
							+ "your account on file...\n");
	    			System.out.println(createAcc);
	    			System.out.print(enterUsername);
	    			username = input.nextLine();
	    			System.out.println(createPW);
	    			pw = input.nextLine();
					createAccount();
	    			currentC = new Customer(username, fName, lName, email, pw, addr,
	    					city, state, zip);
	    			custHT.insert(currentC, emailPWKey);
	    			custByName.insert(currentC, fullNameKey);
					System.out.println(success);
				} else {
					currentC = custHT.get(currentC, emailPWKey);
					System.out.println("\nWelcome back, " + currentC.getFirstName() + " "
									+ currentC.getLastName() + "!\n");
				}
			}
		}

    public static void createAccount() {
		System.out.print("Enter your first name: ");
		fName = input.nextLine();
		System.out.print("Enter your last name: ");
		lName = input.nextLine();
		System.out.print("Enter your email: ");
		email = input.nextLine();
		System.out.print("Enter your address: ");
		addr = input.nextLine();
		System.out.print("Enter your city: ");
		city = input.nextLine();
		System.out.print("Enter your state: ");
		state = input.nextLine();
		System.out.print("Enter your zipcode: ");
		zip = input.nextInt();
		input.nextLine(); // clear buffer
	}
  
	public static void custInterface(HashTable<Customer> custHT,
			HashTable<Customer> custByName, BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) {
		String choice = "", ans;
		custAccSetup(custHT, custByName, vgByTitle, vgByDate); 
		while (!choice.equalsIgnoreCase("X")) {
			displayCustMenu();
			System.out.print("Enter your choice: "); //TODO: will convert this to a member string
			choice = input.nextLine();
			switch (choice.toUpperCase()) {
				case "1":
					placeOrder(vgByTitle);
					//OverniDght Shipping, Rush Shipping, Standard Shipping
					//Priority attribute for each video game order? or overall?
					break;
				case "2":
					listVG(vgByTitle, vgByDate);
					break;
				case "3":
					searchVG(vgByTitle);
					break;
				case "4":
					viewOrders();
					break;
				case "5": //TODO: this works but requires to enter for some reason, buffer issue?
					System.out.println("\nWould you like to sign out?\n");
					System.out.print("Enter (Y/N): ");
					ans = input.nextLine();
					if (ans.equalsIgnoreCase("Y")) {
						custAccSetup(custHT, custByName, vgByTitle, vgByDate);
					}
					break;
				case "X":
					System.out.println("\nGoodbye!");
					//Write to all txt files
					break;
				default:
					System.out.println("\nInvalid menu option."
							+ " Please enter A-D or X to exit.");
					break;
				}
			}
		}

		public static void empLogin(HashTable<Employee> empHT) {
			System.out.println("\n[Employee Login Menu]");
	    	System.out.println("\nWelcome back! Please login here");
	    	System.out.println("-------------------------------\n");
	    	System.out.print("Enter your email address: ");
			email = input.nextLine();
			System.out.print("Enter your password: ");
			pw = input.nextLine();
			currentEmp = new Employee(email, pw);
			while (!(empHT.contains(currentEmp, emailPWKey))) { // only works based on email
													// and password, one HT
				System.out.println("\nPlease make sure you entered your correct"
						+ " case sensitive email and password!"); 
				// TODO: extra, give them X tries, count with a num,
				//if they exceed X tries the program will terminate.
				System.out.print("Enter your email address: ");
				email = input.nextLine();
				System.out.print("\nEnter your password: ");
				pw = input.nextLine();
				currentEmp = new Employee(email, pw);
			}
			currentEmp = empHT.get(currentEmp, emailPWKey); 
			System.out.println("\nWelcome back, " + currentEmp.getFirstName()
					+ " " + currentEmp.getLastName() + "!\n");
		}
	  
		public static void empInterface(BST<VideoGame> vgByTitle,
				BST<VideoGame> vgByDate, HashTable<Customer> custHT,
				HashTable<Customer> custByName, HashTable<Employee> empHT,
				Heap<Order> priorityQueue) {
			String choice = "", ans; // TODO: EXTRA: access cust email, if title
										// = val/gen imp,
										// then print out f2p games with seperate for loop
			input.nextLine(); // clear buffer from reading an Int
			empLogin(empHT);
		while (!choice.equalsIgnoreCase("X")) {
			displayEmpMenu();
			System.out.print("Enter your choice: ");
			choice = input.nextLine();
			switch (choice.toUpperCase()) {
				case "1":
					viewPriorityQueue(priorityQueue);
					break;
				case "2":
					System.out.println(custHT); //Display unsorted customer information
					//TODO: add customers phone numbers, need to format individual printing fields
					//(avoid printing passwords)
					break;
				case "3":
					searchingCus(custByName);
					break;
				case "4":
					//Ship an Order (Remove from Heap) 
					break;
				case "5":
					listVG(vgByTitle, vgByDate);
					break;
				case "6":
					addVG(vgByTitle, vgByDate);
					break;
				case "7":
					removeVG(vgByTitle, vgByDate);
					break;
				case "8":
					System.out.println("\nWould you like to sign out?\n");
					System.out.print("Enter (Y/N): ");
					ans = input.nextLine();
					if (ans.equalsIgnoreCase("Y")) {
						empLogin(empHT);
					}
					break;
				case "X":
					System.out.println("\nGoodbye!");
					//Write to all txt files
					break;
				default:
					System.out.println("\nInvalid menu option."
							+ " Please enter A-D or X to exit.");
					break;
			}
		}
	}

	public static void searchingCus(HashTable<Customer> custByName) {
		System.out.print("Please type in the first name of the person you are searching for: ");
		fName = input.nextLine();
		System.out.print("Please type in the last name of the person you are searching for: ");
		lName = input.nextLine();
		Customer cust = Employee.searchCustomer(fName, lName, custByName);
		if(cust == null) {
			System.out.println("Customer doesn't exist!");
		} else {
			System.out.println("Customer has been found:\n"
					+ cust);
		}
	}
    public static void placeOrder(BST<VideoGame> vgByTitle) {
    	//TODO: (Nigel) should rewrite this to just create the order and call the place order method
    	List<VideoGame> unshippedVG = new List<>();
    	System.out.println("Enter the case sensitive title of the video game you would like to buy: ");
		title = input.nextLine();
		VideoGame tempVG = new VideoGame(title);
		tempVG = vgByTitle.search(tempVG, tc);
		if (tempVG == null) {
			System.out.println("Sorry, we don't carry this title yet."
					+ "Please make sure you entered the correct case sensitive title!");
		} else {
			unshippedVG.addLast(tempVG);
		}
    }
    
    public static void viewOrders() {
		String ans = "";
		System.out.println("\n[Viewing Order(s) Submenu]");
		System.out.println("Which would you like to view?\n\n"
				+ "U: My Unshipped Orders\n"
				+ "S: My Shipped Orders\n");
		System.out.print("Enter your choice: "); //TODO: extra add while loop
		ans = input.nextLine();
		if (ans.equalsIgnoreCase("U")) {
			System.out.println("\n\t[" + currentC.getUsername()
					+ "'s Unshipped Orders]\n");
			currentC.viewUnshippedOrders();
		} else if (ans.equalsIgnoreCase("S")) { // for typos
			System.out.println("\n\t[" + currentC.getUsername()
					+ "'s Shipped Orders]\n");
			currentC.viewShippedOrders();
		} else {
			System.out.println(
					"Invalid Input, Please enter only U or S next time!");
		}
	}
    
    public static void shipOrder() {
    	//emp calls this, based on heap //TODO: (Mario or Nigel) write the shipOrder() method
    	//remove this from the user? remove vg
    }
    
	public static void searchVG(BST<VideoGame> vgByTitle) {
		VideoGame searchVG;
		System.out.println("\nWhich video game would you like to search for?");
		System.out.print("\nEnter the title: ");
		title = input.nextLine();
		searchVG = new VideoGame(title);
		searchVG = vgByTitle.search(searchVG, tc);
		if (searchVG != null) {
			System.out.println("\nWe were able to find this video game: \n\n"
					+ searchVG);
		} else {
			System.out.println("Sorry, we don't have this "
					+ "video game in our system yet!");
		}
	}

    public static void listVG(BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) {
    	//TODO: EXTRA: follow inOrderPrint traversal, pass in an AL
    	//and print this AL to print like around 10ish games each time max or just less
    	String choice = "";
    	System.out.println("\nHow would you like to sort the"
    						+ "avaliable video games?\n"
    						+ "1. By Title\n"
    						+ "2. By Release Date");
    	System.out.print("\nEnter your choice: ");
		choice = input.nextLine();
		while (!(choice.equals("1") || choice.equals("2"))) {
			// TODO: Is this fixed now?
			if (choice.equals("1")) {
				vgByTitle.inOrderPrint();
			} else if (choice.equals("2")) { // for typos
				vgByDate.inOrderPrint();
			} else {
				System.out.println("Invalid Input, Please enter only 1 or 2!");
			}
		}
	}
    
	public static void addVG(BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) {
    	System.out.print("Please type in the Video Game you want to add: ");
		title = input.nextLine();
		VideoGame vg = vgByTitle.search(new VideoGame(title), tc);
		if (vg != null) {
			Employee.addProduct(vgByTitle, vgByDate, vg, tc, dc);
		} else {
			System.out.println(
					"\nCannot find the Video Game you typed in, try again");
		}
	}

	public static void removeVG(BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) {
		System.out.print("Please type in the Video Game you want to remove: ");
		title = input.nextLine();
		VideoGame vg = vgByTitle.search(new VideoGame(title), tc);
		if (vg != null) {
			Employee.removeProduct(vgByTitle, vgByDate, vg, tc, dc);
		} else {
			System.out.println(
					"\nCannot find the Video Game you typed in, try again");
		}
	}

    public static void displayCustMenu() {
		System.out.println("\n[Customer Main Menu]\n\n"
				+ "Please select from the following options:\n\n"
				+ "1. Place Order\n"
				+ "2. List Video Games\n"
				+ "3. Search for Video Game\n"
				+ "4. View Unshipped and Shipped Orders\n"
				+ "5. Sign Out of Your Acount\n"
				+ "X. Exit\n"); // TODO: finalize output
    }

    public static void displayEmpMenu() {
        System.out.println("\n[Employee Main Menu]\n\n"
				+ "Please select from the following options:\n\n"
                + "1. View Orders by Priority\n"
                + "2. Display Customer Info\n"
                + "3. Search for Customer\n"
                + "4. Ship Orders\n"
                + "5. List Video Games\n"
                + "6. Add New Product\n"
                + "7. Remove a Product\n"
                + "8. Sign Out of Your Acount\n"
                + "X. Exit\n"); //TODO: finalize output
    }

	public static void fileToCustandPQ(HashTable<Customer> custHT,
			HashTable<Customer> custByName, BST<VideoGame> vgByTitle,
			Heap<Order> priorityQueue) throws FileNotFoundException {
		String address;
		int numGames, uNumOrders, sNumOrders = 0, uShipSpeed = 0, sShipSpeed = 0;
		String orderDate = ""; // for orders
    	File file = new File(custFile);
		input = new Scanner(file);
		while (input.hasNextLine()) {
			username = input.nextLine();
			//System.out.println(username);
			fName = input.nextLine();
			//System.out.println(fName);
			lName = input.nextLine();
			//System.out.println(lName);
			email = input.nextLine();
			//System.out.println(email);
			pw = input.nextLine();
			//System.out.println(pw);
			address = input.nextLine();
			//System.out.println(address);
			city = input.nextLine();
			//System.out.println(city);
			state = input.nextLine();
			//System.out.println(state);
			zip = input.nextInt();
			//System.out.println(zip);
			if(input.hasNextLine()){
				input.nextLine();
			}
			Customer newC = new Customer(username, fName, lName, email, pw,
					address, city, state, zip);
			List<VideoGame> unshippedVG = new List<>();
			List<VideoGame> shippedVG = new List<>();
			
			uNumOrders = input.nextInt();
			System.out.println(uNumOrders);
			for (int i = 0; i < uNumOrders; i++) {
				uShipSpeed = input.nextInt();
				//System.out.println(uShipSpeed);
				//boolean would be here
				numGames = input.nextInt();
				//System.out.println(numGames);
				input.nextLine();
				orderDate = input.nextLine();
				//System.out.println(orderDate);
				for (int j = 0; j < numGames; j++) {
					title = input.nextLine();
					//System.out.println(title);
					VideoGame tempVG = new VideoGame(title);
					tempVG = vgByTitle.search(tempVG, tc);
					unshippedVG.addLast(tempVG);
				}
				
			}
			Order unShippedOrder = new Order(newC, orderDate, unshippedVG, uShipSpeed, false);
			sNumOrders = input.nextInt();
			input.nextLine();
			for (int i = 0; i < sNumOrders; i++) {
				sShipSpeed = input.nextInt();
				//System.out.println(sShipSpeed);
				//boolean would be here
				numGames = input.nextInt();
				//System.out.println(numGames);
				input.nextLine();
				orderDate = input.nextLine();
				//System.out.println(orderDate);
				for (int j = 0; j < numGames; j++) {
					title = input.nextLine();
					//System.out.println(title);
					VideoGame tempVG = new VideoGame(title);
					tempVG = vgByTitle.search(tempVG, tc);
					unshippedVG.addLast(tempVG);
				}
				
			Order shippedOrder = new Order(newC, orderDate, shippedVG, sShipSpeed, true);
			newC.placeUnshippedOrder(unShippedOrder);
			newC.placeShippedOrder(shippedOrder);
			custHT.insert(newC, emailPWKey);
			custByName.insert(newC, fullNameKey);
			ArrayList<Order> tempOrder = new ArrayList<>();
			priorityQueue.insert(unShippedOrder);
			priorityQueue.insert(shippedOrder);
			
			if (input.hasNextLine()) {
				input.nextLine();
			}
		}
			System.out.println(priorityQueue);
	}
		input.close();
}

	public static void fileToEmp(HashTable<Employee> empHT)
			throws FileNotFoundException {
		int accNum;
    	File file = new File(empFile);
		input = new Scanner(file);
		while (input.hasNextLine()) {
			fName = input.nextLine();
			lName = input.nextLine();
			email = input.nextLine();
			pw = input.nextLine();
			accNum = input.nextInt();
			if (input.hasNextLine()) {
				input.nextLine();  //clear buffer
				input.nextLine();
			}
			Employee newE = new Employee(fName, lName, email, pw, accNum);
			empHT.insert(newE, emailPWKey);
		}
		input.close();
    }

	public static void fileToVG(BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate)
			throws FileNotFoundException {
		String dev, genre, ESRB, pform;
		double price;
		int rDate, mcScore;
		File file = new File(vgFile);
		input = new Scanner(file);
		while (input.hasNextLine()) {
			title = input.nextLine();
			dev = input.nextLine();
			rDate = input.nextInt();
			price = input.nextDouble();
			input.nextLine(); // clear buffer
			genre = input.nextLine();
			ESRB = input.nextLine();
			mcScore = input.nextInt();
			input.nextLine();
			pform = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}
			VideoGame newVG = new VideoGame(title, dev, rDate, price, genre,
					ESRB, mcScore, pform);
			vgByTitle.insert(newVG, tc);
			vgByDate.insert(newVG, dc);
		}
		input.close();
	}

	public static void viewPriorityQueue(Heap<Order> priorityQueue) { //TODO viewPriorityQueue needs testing
		ArrayList<Order> tempOrder = priorityQueue.sort();
		System.out.println("Printing orders in order of priority: \n\n");
		for (int i = tempOrder.size() - 1; i > 0; i--) {
			System.out.println(tempOrder.get(i));
		}
	}

	public static void customerToFile(HashTable<Customer> customers) throws IOException {
		FileWriter myWriter = new FileWriter(custFile);
		myWriter.write(customers.toString());
		myWriter.close();
	}
}
