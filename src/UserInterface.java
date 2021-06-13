/**
 * UserInterface.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {
	private static final String vgFile = ("product.txt"),
			custFile = ("customers.txt"), empFile = ("employees.txt");
	//maybe change to videoGames.txt, once we decide on a String txt
	//name it's good to make it final (taught in 36B)
	//(if we don't want to end up changing it later on)
	
    public static void main(String[] args) {
        final int customerSize = 5, employeeSize = 3;
        Scanner input = new Scanner(System.in);
        HashTable<Customer> customersHT = new HashTable<>(customerSize * 2);
        HashTable<Employee> employeesHT = new HashTable<>(employeeSize * 2);
        BST<VideoGame> vgByTitle = new BST<>();
        BST<VideoGame> vgByDate = new BST<>();
        String email;
        int userType;
        //Heap<Order> shippedOrders = new Heap<>();
        //Heap<Order> orderHeap = new Heap<>(); //need to finish some methods in heap to call this
		try {
			fileToVG(input, vgByTitle, vgByDate);
			fileToCust(input, customersHT);
			fileToEmp(input, employeesHT);
		} catch (FileNotFoundException e) {
			System.out.println("File(s) not found, please make sure it is in the project"
					+ "folder and rereun the program.");
		}
       
        //fileToOrders(input, orderHeap);
        System.out.println("Welcome to [Insert Video Game Store Title Here]! \n");
        System.out.println("Please note that we don't offer refunds after you place your orders!");
        //maybe mention cash or credit/debit card only
        System.out.println("What type of user are you?\n"
        		+ "1. Customer\n"
        		+ "2. Employee");
        
        System.out.print("\nPlease enter 1 or 2: ");
        userType = input.nextInt();
        if (userType == 1) {
            custInterface(input, customersHT, vgByTitle, vgByDate);
        } else {
            empInterface(input, vgByDate, vgByDate);
        }
    }

	public static void custInterface(Scanner input, HashTable<Customer> custHT,
			BST<VideoGame> vgByTitle, BST<VideoGame> vgByDate) {
		String username = "", fName, lName, email, pw, addr, city, state,
				choice = "";
		int zip;
		input.nextLine(); // clear buffer from reading
		System.out.println("\nWelcome to our store, please login here!");
		System.out.print("Enter your email address: ");
		email = input.nextLine();
		System.out.print("Enter your password: ");
		pw = input.nextLine();
		Customer tempC = new Customer(email, pw);
		Customer currentC;
		if (!(custHT.contains(tempC))) {
			System.out.println("\nWe don't have your account on file...\n");
			System.out.println("Let's create an account for you!");
			System.out.print("Enter your username: ");
			username = input.nextLine();
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
			System.out.println("\nYou have succesfully created an account, "
					+ fName + " " + lName + "!\n");
			currentC = new Customer(username, fName, lName, email, pw, addr,
					city, state, zip);
			custHT.insert(currentC);
		} else {
			currentC = custHT.get(tempC);
			System.out.println("\nWelcome back, " + currentC.getFirstName()
					+ " " + currentC.getLastName() + "!\n");
		}

		while (!choice.equalsIgnoreCase("X")) {
			displayCustMenu();
			System.out.print("Enter your choice: ");
			choice = input.nextLine();
			switch (choice.toUpperCase()) {
				case "1":
					//Place Orders (buying video game)
					//- Overnight Shipping, Rush Shipping, Standard Shipping
					//pirority attribute for each video game order? or overall?
					break;
				case "2":
					listVideoGames(input, vgByTitle, vgByDate);
					break;
				case "3":
					searchVideoGame(input, currentC, vgByTitle);
					break;
				case "4":
					//4. View Purchases
					//currentC.viewShippedOrders();
					//currentC.viewUnshippedOrders();
					break;
				case "5":
					//Remove video game from unshippedorderlist
					//maybe if block for mentioning that it has to be not shipped yet
					//if unshippedorderlist is empty, run if block, else
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

	public static void empInterface(Scanner input, BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) {
		String choice = "";
		input.nextLine(); //clear buffer from reading an Int
		while (!choice.equalsIgnoreCase("X")) {
			displayEmpMenu();
			System.out.print("Enter your choice: ");
			choice = input.nextLine();
			switch (choice.toUpperCase()) {
				case "1":
					//1. View Orders by Priority
					break;
				case "2":
					//Display unsorted customer information, 
					//including first and last name, address, phone number, order history
					break;
				case "3":
					//Search for Customer
					break;
				case "4":
					//Ship an Order (Remove from Heap) 
					break;
				case "5":
					listVideoGames(input, vgByTitle, vgByDate);
					break;
				case "6":
					//Add New Product
					break;
				case "7":
					//Remove a Product
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

    public static void placeOrder(Scanner input, Customer customerC) {

    }

	public static void searchVideoGame(Scanner input, Customer customerC,
			BST<VideoGame> vgByTitle) {
		TitleComparator tc = new TitleComparator();
		String userSearch;
		VideoGame searchVG;
		System.out.println("\nWhich video game would you like to search for?");
		System.out.print("\nEnter the title: ");
		userSearch = input.nextLine();
		searchVG = new VideoGame(userSearch);
		searchVG = vgByTitle.search(searchVG, tc);
		if (searchVG != null) {
			System.out.println("\nWe were able to find this video game: \n\n"
					+ searchVG);
		} else {
			System.out.println("Sorry, we don't have this "
					+ "video game in our system yet!");
		}

	}

    public static void viewPurchases(Scanner input, Customer customerC) { //shopping cart?
		String viewChoice;
    	System.out.println("1 to view unshipped orders | 2 to view shipped orders");
		viewChoice = input.nextLine();
		if (viewChoice.equals("1")) {
			// System.out.println(customer.getShipped); no getter for shipped or unshipped list
		} else {

		}
    }

    public static void listVideoGames(Scanner input, BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) {
    	String choice = "";
    	System.out.println("\nHow would you like to sort the avaliable video games?\n"
    			+ "1. By Title\n"
    			+ "2. By Release Date");
    	System.out.print("\nEnter your choice: ");
		choice = input.nextLine();
		//while (!(choice.equals("1") && choice.equals("2"))) {
			if (choice.equals("1")) {
				vgByTitle.inOrderPrint();
			} else if (choice.equals("2")) { //for typos
				vgByDate.inOrderPrint();
			} else {
				System.out.println("Invalid Input, Please enter only 1 or 2 next time!");
			}
		}
    

    public static void shipOrder() {
    	//emp calls this, based on heap
    	//remove this from the user? remove vg
    }

    public static void addVG(BST<VideoGame> vgByTitle, BST<VideoGame> vgByDate) {

	}

	public static void removeVG(BST<VideoGame> vgByTitle, BST<VideoGame> vgByDate) {

	}

    public static void displayCustMenu() {
		System.out.println("\nPlease select from the following options:\n\n"
				+ "1. Place Order\n"
				+ "2. List Video Games\n"
				+ "3. Search for Video Game\n"
				+ "4. View Purchases\n"
				+ "5. Remove a video Game from your shopping cart\n"
				+ "X. Exit\n"); // TODO: finalize output

    }

    public static void displayEmpMenu() {
        System.out.println("\nPlease select from the following options:\n\n"
                + "1. View Orders by Priority\n"
                + "2. Display Customer Info\n"
                + "3. Search for Customer\n"
                + "4. Ship Orders\n"
                + "5. List Video Games\n"
                + "6. Add New Product\n"
                + "7. Remove a Product\n"
                + "X. Exit\n"); //TODO: finalize output
    }

	public static void fileToCust(Scanner input,
			HashTable<Customer> customersHT) throws FileNotFoundException {
    	String username, fName, lName, email, pw, address, city, state;
		int zip;
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
				input.nextLine();  //clear buffer
				input.nextLine();
			}
			Customer newC = new Customer(username, fName, lName, email, pw,
					address, city, state, zip);
			customersHT.insert(newC);
		}
		input.close();
    }

	public static void fileToEmp(Scanner input, HashTable<Employee> empHT)
			throws FileNotFoundException {
		String fName, lName, email, pw;
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
			empHT.insert(newE);
		}
		input.close();
    }

	public static void fileToVG(Scanner input, BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) throws FileNotFoundException {
		String title, dev, genre, ESRB, pform;
		double price;
		int rDate, mcScore;
		TitleComparator tComp = new TitleComparator();
		DateComparator dComp = new DateComparator();
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
			vgByTitle.insert(newVG, tComp);
			vgByDate.insert(newVG, dComp);
		}
		input.close();
	}

    public static void fileToOrders(Scanner input, Heap<Order> orderHeap) {
    	//do this after order is finished
    }

    public static void employeeToFile(HashTable<Employee> employees) {

    }

    public static void customerToFile(HashTable<Customer> customers) {

    }

    public static void ordersToFile(Heap<Order> orderHeap) {

	}

}
