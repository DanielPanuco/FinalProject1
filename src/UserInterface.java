/**
 * UserInterface.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */

import java.io.*;
import java.lang.reflect.Array; //TODO: unused
import java.text.DecimalFormat; //TODO: unused
import java.util.ArrayList;
import java.util.PriorityQueue; //TODO: unused

import java.util.Scanner;

public class UserInterface {
	private static final String vgFile = ("product.txt"),
			custFile = ("customers.txt"), empFile = ("employees.txt");
	public static String fName, lName, email, addr, city, state, pw, title,
						 username, fullNameKey, emailPWKey;
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
		String userType;
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
		input = new Scanner(System.in);
		System.out.println("Welcome to Triforce Games! \n");
		//System.out.println("Please note that we don't offer refunds after you place your orders!");
		//maybe mention credit/debit card only
		System.out.println("[Please select your user type]\n"
				+ "1. Customer\n"
				+ "2. Employee");
		System.out.print("\nPlease enter 1 or 2: ");
		userType = input.nextLine();
		if (userType.equals("1")) {
			custInterface(custHT, custByName, vgByTitle, vgByDate, priorityQueue);
		} else {
			empInterface(vgByTitle, vgByDate, custHT, custByName, empHT, priorityQueue);
		}
		try {
			customerToFile(custHT);
			setVgFile(vgByTitle);
		} catch (IOException e) {
			e.getMessage(); //TODO: write a custom message for this
		}
	}

	public static void custAccSetup(HashTable<Customer> custHT,
			HashTable<Customer> custByName) {
		String ans;
		String createAcc = "\nLet's create an account for you!\n";
		String enterUsername = "Enter your username: "; //only do this if it's clear
		String createPW = "Create a password: ";
		System.out.println("\nWelcome to our store, please login here!");
		System.out.println("\n[Choose your customer type]\n"
				+ "1. Guest\n"
				+ "2. New Customer\n"
				+ "3. Existing Customer");
		System.out.print("\nPlease enter 1, 2, or 3: ");
		ans = input.nextLine();
		if (ans.equals("1")) {
			System.out.println("\nPlease start by filling out "
					+ "your shipping info!");
			createAccount();
			emailPWKey = email + pw;
			fullNameKey = fName + lName;
			currentC = new Customer(fName, lName, email, addr,
					city, state, zip);
			custHT.insert(currentC, emailPWKey);
			custByName.insert(currentC, emailPWKey);
			System.out.println("\nThank you for filling out your shipping info, "
							+ fName + " " + lName + "!");
		} else if (ans.equals("2")) {
			createAccount();
			System.out.println(createAcc);
			System.out.print(enterUsername);
			username = input.nextLine();
			System.out.print(createPW);
			pw = input.nextLine();
			emailPWKey = email + pw;
			fullNameKey = fName + lName;
			currentC = new Customer(username, fName, lName, email, pw, addr,
					city, state, zip);
			custHT.insert(currentC, emailPWKey);
			custByName.insert(currentC, emailPWKey);
			System.out.println("\nYou have successfully created an account, "
					+ fName + " " + lName + "!\n");
		} else if (ans.equals("3")){
			System.out.print("\nEnter your email address: ");
			email = input.nextLine();
			System.out.print("Enter your password: ");
			pw = input.nextLine();
			emailPWKey = email + pw;
			fullNameKey = fName + lName;
			Customer tempC = new Customer(email, pw);
			boolean signinStatus = custHT.contains(tempC, emailPWKey);
			if (!(signinStatus)) {
				System.out.println("\nIt appears we don't have "
						+ "your account on file...\n");
				System.out.println(createAcc);
				System.out.print(enterUsername);
				username = input.nextLine();
				System.out.print(createPW);
				pw = input.nextLine();
				createAccount();
				currentC = new Customer(username, fName, lName, email, pw, addr,
						city, state, zip);
				custHT.insert(currentC, emailPWKey);
				custByName.insert(currentC, fullNameKey);
				System.out.println("\nYou have successfully created an account, "
						+ fName + " " + lName + "!\n");
			} else {
				currentC = custHT.get(tempC, emailPWKey);
				System.out.println("\nWelcome back, " + currentC.getFirstName() + " "
						+ currentC.getLastName() + "!\n");
			}
		}
	}

	public static void createAccount() {
		System.out.print("\nEnter your first name: ");
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
									 BST<VideoGame> vgByDate, Heap<Order> priorityQueue) {
		String choice = "", ans;
		custAccSetup(custHT, custByName);
		while (!choice.equalsIgnoreCase("X")) {
			displayCustMenu();
			System.out.print("Enter your choice: "); //TODO: will convert this to a member string
			choice = input.nextLine();
			switch (choice.toUpperCase()) {
				case "1":
					placeOrder(vgByTitle, priorityQueue);
					break;
				case "2":
					listVG(vgByTitle, vgByDate);
					break;
				case "3":
					searchVG(vgByTitle);
					//TODO: prob a good idea to have an option for the user to buy this title
					//pass in the title into a variation of our placeOrder method
					break;
				case "4":
					viewOrders();
					break;
				case "5":
					System.out.println("\nWould you like to sign out?\n");
					System.out.print("Enter (Y/N): ");
					ans = input.nextLine();  //TODO: buffer issue, enter twice
					if (ans.equalsIgnoreCase("Y")) {
						custAccSetup(custHT, custByName);
					}
					break;
				case "X":
					System.out.println("\nGoodbye! We hope to see you again!");
					break;
				default:
					System.out.println("\nInvalid menu option."
							+ " Please enter 1-5 or X to exit.");
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
		while (!(empHT.contains(currentEmp, emailPWKey))) {
			System.out.println("\nPlease make sure you entered your correct"
					+ " case sensitive email and password!");
			// TODO: extra, give them X tries, count with a num,
			//if they exceed X tries the program will terminate.
			System.out.print("\nEnter your email address: ");
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
					System.out.println(custHT); //TODO: call right method
					break;
				case "3":
					searchingCust(custByName);
					break;
				case "4":
					shipOrder(priorityQueue);
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
					break;
				default:
					System.out.println("\nInvalid menu option."
							+ " Please enter 1-8 or X to exit.");
					break;
			}
		}
	}

	public static void searchingCust(HashTable<Customer> custByName) {
		System.out.print("\nPlease type in the first name of the customer "
						+ "you are searching for: ");
		fName = input.nextLine();
		System.out.print("\nPlease type in the last name of the customer "
						+ "you are searching for: ");
		lName = input.nextLine();
		Customer cust = Employee.searchCustomer(fName, lName, custByName);
		if (cust == null) {
			System.out.println("Customer doesn't exist!");
		} else {
			System.out.println("Customer has been found:\n");
			cust.displayCustomer();
		}
	}
	public static void placeOrder(BST<VideoGame> vgByTitle, Heap<Order> priorityQueue) {
		double orderPrice; //TODO: unused
		Order placeOrder;
		String userInput;
		int numChoice;
		String divider = "----------------------------------"; // for future potential use
		List<VideoGame> unshippedVG = new List<>();
		vgByTitle.inOrderPrint();
		System.out.print("Enter the number of games you would like to purchase: ");
		userInput = input.nextLine();
		do {
			try {
				numChoice = Integer.parseInt(userInput);
				if (numChoice >= 1) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			System.out.print("Input must be a number greater than 0: ");
			userInput = input.nextLine();
		} while (true);

		for (int i = 0; i < numChoice; i++) {
			if (numChoice == 1) {
				System.out.print("\nEnter the case sensitive title of the video game you'd like to purchase: ");
			} else {
			System.out.print("\nEnter the case sensitive title of the video game purchase #" + (i + 1) + ": ");
			}
			userInput = input.nextLine();
			VideoGame tempVG = new VideoGame(userInput);
			tempVG = vgByTitle.search(tempVG, tc);
			while (tempVG == null) {
				System.out.print("Please confirm correct spelling. Enter case sensitive title: ");
				userInput = input.nextLine();
				tempVG = new VideoGame(userInput);
				tempVG = vgByTitle.search(tempVG, tc);
			}
			unshippedVG.addLast(tempVG);
		}
		System.out.println("\nWhich shipping option would you like to choose?\n");
		System.out.println("1. Standard Shipping (5 Days): $4.95 *Free for orders over $35!*"
				+ "\n2. Rush Shipping (2 Days): $7.95" + "\n3. Overnight Shipping (1 Day): $14.95\n");
		System.out.print("Please choose your desired shipping speed: ");
		userInput = input.nextLine();
		do {

			try {
				numChoice = Integer.parseInt(userInput);

				if (numChoice >= 1 && numChoice <= 3) {
					break;
				}

			} catch (NumberFormatException e) {
			}

			System.out.print("Input must be a number between 1 and 3: ");
			userInput = input.nextLine();
		} while (true);

		switch (numChoice) {
		case 1:
			numChoice = 5;
			break;
		case 2:
			numChoice = 2;
			break;
		case 3:
			numChoice = 2;
			break;
		// TODO: Missing a default case
		}
		System.out.println("\nThank you, your order is being processed!\n");
        placeOrder = new Order(currentC, unshippedVG, numChoice, false);
        //placeOrder.displayPriceCalculation(unshippedVG, numChoice);
        priorityQueue.insert(placeOrder);
        currentC.placeUnshippedOrder(placeOrder);
	}

	public static void viewOrders() {
		String ans = "";
		System.out.println("\n[Viewing Order(s) Submenu]");
		System.out.println("\nWhich would you like to view?\n\n"
				+ "U: My Unshipped Orders\n"
				+ "S: My Shipped Orders\n");
		System.out.print("Enter your choice: "); //TODO: extra add while loop
		ans = input.nextLine();
		if (ans.equalsIgnoreCase("U")) {
			if (!currentC.getUsername().equals("NA")) {
				System.out.println("\n\t\t\t[" + currentC.getUsername()
						+ "'s Unshipped Orders]\n");
				currentC.viewUnshippedOrders();
			} else {
				System.out.println("\n\t\t\t[" + currentC.getFirstName() + " "
						+ currentC.getLastName() + "'s Unshipped Orders]\n");
				currentC.viewUnshippedOrders();
			}
			
		} else if (ans.equalsIgnoreCase("S")) { // for typos
			if (!currentC.getUsername().equals("NA")) {
				System.out.println("\n\t\t\t[" + currentC.getUsername()
						+ "'s Shipped Orders]\n");
				currentC.viewShippedOrders();
			} else {
				System.out.println("\n\t\t\t[" + currentC.getFirstName() + " "
						+ currentC.getLastName() + "'s Shipped Orders]\n");
				currentC.viewShippedOrders();
			}
		} else {
			System.out.println(
					"Invalid Input, Please enter only U or S next time!");
		}
	}

	public static void shipOrder(Heap<Order> priorityQueue) {
		if (priorityQueue.getHeapSize() == 0) {
			System.out.println("\nThere are no orders to ship!");
			} else {
		System.out.println("\nShipping an order...\n");
		Order tempOrder = priorityQueue.getMax();
		//TODO: check pq's size to see if there are orders to ship
		priorityQueue.remove(1);
		List<VideoGame> tempOrderVG = tempOrder.getOrderContents();
		System.out.println("Date ordered: " + tempOrder.getCurrentDate());
		System.out.println("Shipping speed: " + tempOrder.getShippingSpeed());
		tempOrderVG.placeIterator();
		while (!tempOrderVG.offEnd()) {
			tempOrderVG.getIterator().printContent();
			tempOrderVG.advanceIterator();
		}
		//TODO: System.out.println("Total price: " + ); do we want to print total price here?
		currentC = tempOrder.getCustomer();
		currentC.displayCustomer();
		currentC.removeUnshippedOrder(tempOrder);
		currentC.placeShippedOrder(tempOrder);
		System.out.println("\nThe order has been shipped!"); //TODO: specify who's order by using getters
			}
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
		String choice = "";
		System.out.println("\nHow would you like to sort the"
				+ " available video games?\n"
				+ "1. By Title\n"
				+ "2. By Release Date");
		System.out.print("\nEnter your choice: ");
		choice = input.nextLine();
		System.out.println();
		while (!(choice.equals("1") || choice.equals("2"))) {
			// TODO: Is this fixed now?
			System.out.println("Wrong input\nEnter 1 to search by title | 2 to search by date: ");
			choice = input.nextLine();
		}
		if (choice.equals("1")) {
			vgByTitle.inOrderPrint();
		} else if (choice.equals("2")) {
			vgByDate.inOrderPrint();
		} else {
			System.out.println("Invalid Input, Please enter only 1 or 2!");
		}
	}


	public static void addVG(BST<VideoGame> vgByTitle,
							 BST<VideoGame> vgByDate) {
		String dev, genre, ESRB, pform;
		double price;
		int rDate, mcScore;

		System.out.print("\nPlease enter the title of the video game: ");
		title = input.nextLine();
		VideoGame tempVG = new VideoGame(title);
		tempVG = vgByTitle.search(tempVG, tc);
		if (tempVG == null) {
			System.out.print("Please enter the developer of " + title + ": ");
			dev = input.nextLine();
			System.out.print("Please enter the release date (YYYYMMDD): ");
			rDate = input.nextInt();
			System.out.print("Please enter the price: $");
			price = input.nextDouble();
			input.nextLine();
			System.out.print("Please enter the genre: ");
			genre = input.nextLine();
			System.out.print("Please enter the ESRB (Entertainment Software Rating Board) Rating: ");
			ESRB = input.nextLine();
			System.out.print("Please enter the Metacritic Score: ");
			mcScore = input.nextInt();
			input.nextLine();
			System.out.print("Please enter the platform: ");
			pform = input.nextLine();
			VideoGame newVG = new VideoGame(title, dev, rDate, price, genre, ESRB,
					mcScore, pform);
			Employee.addProduct(vgByTitle, vgByDate, newVG, tc, dc);
		} else {
			System.out.println(
					"\nThis video game already exists in our system!");
		}
	}

	public static void removeVG(BST<VideoGame> vgByTitle,
								BST<VideoGame> vgByDate) {
		System.out.print("\nPlease type in the title of the Video Game you want to remove: ");
		title = input.nextLine();
		VideoGame vg = vgByTitle.search(new VideoGame(title), tc);
		if (vg != null) {
			Employee.removeProduct(vgByTitle, vgByDate, vg, tc, dc);
			System.out.println(title + " has been successfully removed from our product catalog!");
		} else {
			System.out.println("Cannot find " + title + "in our product catalog, please try again!");
		}
	}

	public static void displayCustMenu() {
		System.out.println("\n[Customer Main Menu]\n\n"
				+ "Please select from the following options:\n\n"
				+ "1. Place Order\n"
				+ "2. List Video Games\n"
				+ "3. Search for Video Game\n"
				+ "4. View Unshipped and Shipped Orders");
		if (currentC.getUsername().equalsIgnoreCase("NA")) {
			System.out.println("5. Sign out as a Guest\n"
								+ "X. Exit\n");
		} else {
			System.out.println("5. Sign out of your account\n"
								+ "X. Exit\n");	
		} // TODO: finalize output
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
				+ "8. Sign Out of Your Account\n"
				+ "X. Exit\n"); //TODO: finalize output
	}

	public static void fileToCustandPQ(HashTable<Customer> custHT,
			HashTable<Customer> custByName, BST<VideoGame> vgByTitle,
			Heap<Order> priorityQueue) throws FileNotFoundException {
		String address;
		long priority;
		int numGames, uNumOrders, sNumOrders = 0, uShipSpeed = 0, sShipSpeed = 0;
		String orderDate = ""; // for orders
		File file = new File(custFile);
		input = new Scanner(file);
		while (input.hasNextLine()) {
			username = input.nextLine();
			fName = input.nextLine();
			lName = input.nextLine();
			email = input.nextLine();
			pw = input.nextLine();
			address = input.nextLine();
			city = input.nextLine();
			state = input.nextLine();
			zip = input.nextInt();
			if (input.hasNextLine()) {
				input.nextLine();
			}
			Customer newC = new Customer(username, fName, lName, email, pw,
					address, city, state, zip);
			uNumOrders = input.nextInt();
			for (int i = 0; i < uNumOrders; i++) {
				List<VideoGame> unshippedVG = new List<>();
				uShipSpeed = input.nextInt();
				input.nextLine();
				orderDate = input.nextLine();
				priority = input.nextLong();
				numGames = input.nextInt();
				input.nextLine();
				for (int j = 0; j < numGames; j++) {
					title = input.nextLine();
					VideoGame tempVG = new VideoGame(title);
					tempVG = vgByTitle.search(tempVG, tc);
					unshippedVG.addLast(tempVG);
				}
				Order unShippedOrder = new Order(newC, orderDate, unshippedVG,
						uShipSpeed, false, priority);
				newC.placeUnshippedOrder(unShippedOrder);
				priorityQueue.insert(unShippedOrder);
			}
			sNumOrders = input.nextInt();
			if (input.hasNextLine()) {
				input.nextLine();
			}
			for (int i = 0; i < sNumOrders; i++) {
				List<VideoGame> shippedVG = new List<>();
				sShipSpeed = input.nextInt();
				input.nextLine();
				orderDate = input.nextLine();
				priority = input.nextLong();
				numGames = input.nextInt();
				input.nextLine();
				for (int j = 0; j < numGames; j++) {
					title = input.nextLine();
					VideoGame tempVG = new VideoGame(title);
					tempVG = vgByTitle.search(tempVG, tc);
					shippedVG.addLast(tempVG);
				}
				Order shippedOrder = new Order(newC, orderDate, shippedVG,
						sShipSpeed, true, priority);
				newC.placeShippedOrder(shippedOrder);
			}
			if (input.hasNextLine()) {
				input.nextLine();
			}
			emailPWKey = email + pw;
			fullNameKey = fName + lName;
			custHT.insert(newC, emailPWKey);
			custByName.insert(newC, fullNameKey);
		}
		input.close();
	}

	public static void fileToEmp(HashTable<Employee> empHT)
			throws FileNotFoundException {
		File file = new File(empFile);
		input = new Scanner(file);
		while (input.hasNextLine()) {
			fName = input.nextLine();
			lName = input.nextLine();
			email = input.nextLine();
			pw = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}
			Employee newE = new Employee(fName, lName, email, pw);
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

	public static void viewPriorityQueue(Heap<Order> priorityQueue) {
		//TODO: viewPriorityQueue needs testing
		ArrayList<Order> tempOrder = priorityQueue.sort();
		System.out.println("\nPrinting orders in order of priority: \n\n");
		for (int i = tempOrder.size() - 1; i > 0; i--) {
			System.out.println("Temp Order " + i + ": " + tempOrder.get(i));
		}
	}

	public static void customerToFile(HashTable<Customer> custHT) throws IOException {
		FileWriter myWriter = new FileWriter(custFile);
		myWriter.write(custHT.toString());
		myWriter.close();
	}

	public static void setVgFile(BST<VideoGame> vgByTitle) throws IOException {
		  ArrayList<VideoGame> tempal = vgByTitle.inOrderToAL();
	        String fileOutput = "";
	        for (int i = 0; i < tempal.size(); i++) {
	            fileOutput += tempal.get(i).toText();
	            fileOutput += "\n";
	        }
	        //System.out.println("testing file output: \n" + fileOutput);
		FileWriter vgWriter = new FileWriter(vgFile);
		vgWriter.write(fileOutput);
		vgWriter.close();
	}
}
