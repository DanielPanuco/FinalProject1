/**
 * UserInterface.java
 * @author Henry Choy
 * @author Mario P
 * CIS 22C, Final Project
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        BST<VideoGame> vgbyTitle = new BST<>();
        BST<VideoGame> vgbyDate = new BST<>();
       
        //Heap<Order> orderHeap = new Heap<>(); need to finish some methods in heap to call this
		try {
			fileToVG(input, vgbyTitle, vgbyDate);
			fileToCust(input, customersHT);
			fileToEmp(input, employeesHT);
		} catch (FileNotFoundException e) {
			System.out.println("File(s) not found, please make sure it is in the project"
					+ "folder and rereun the program.");
		} catch (IOException e) { //unreachable catch block?
			System.out.println("Unable to read your input file(s), please double check if"
					+ "it's corrupt and rereun the program.");
		}
       
        //fileToOrders(input, orderHeap);

        String email;
        int userType;
        System.out.println("Welcome to [Insert Video Game Store Title Here]! \n");
        //Probably will write something about being cash only and no refunds
        System.out.println("What type of user are you?\n"
        		+ "1. Customer\n"
        		+ "2. Employee");
        
        System.out.print("\nPlease enter 1 or 2: ");
        userType = input.nextInt();
        if (userType == 1) {
            custInterface(input, customersHT);
        } else {
            empInterface(input);
        }
        //input.nextLine(); //clear buffer
        // read in files to hashtables
        // sign in
        // call a method for either employee or customer
    }

    public static void custInterface(Scanner input, HashTable<Customer> custHT) {
		String username = "", fName, lName, email, password, address, city, state,
				choice = "";
        int zip;
        input.nextLine(); //clear buffer from reading
        while (!choice.equalsIgnoreCase("X")) {
        	System.out.println("\n Welcome to our store, please login here!");
        	System.out.print("Enter your username: ");
     		username = input.nextLine();
            System.out.print("Enter your email address: ");
    		email = input.nextLine();
    		System.out.print("Enter your password: ");
    		password = input.nextLine();
    		Customer tempC = new Customer(username, email, password);
    		Customer returningC;
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
    			email= input.nextLine();
    			System.out.print("Enter your address: ");
    			address = input.nextLine();
    			System.out.print("Enter your city: ");
    			city = input.nextLine();
    			System.out.print("Enter your state: ");
    			state = input.nextLine();
    			System.out.print("Enter your zipcode: ");
    			zip = input.nextInt();
    			input.nextLine(); //clear buffer
    			System.out.println("\nYou have succesfully created an account, " + fName + " " + lName + "!\n");
    			returningC = new Customer(username, fName, lName, email, password, address, city, state, zip);
    			custHT.insert(returningC);
    		} else {
    			returningC = custHT.get(tempC);
    			System.out.println("\nWelcome back, " + returningC.getFirstName()
    					+ " " + returningC.getLastName() + "!\n");
    		}
    		displayCustMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
            	//I'll decide if I think having num or letters is more fitting
                case "A":
                    break;
                case "B":
                    break;
                case "C":
                    break;
                case "D":
                    break;
                case "X":
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\nInvalid menu option."
                            + " Please enter A-D or X to exit.");
                    break;
            }
        }
    }

    public static void empInterface(Scanner input) {
        String choice = "";
        input.nextLine(); //clear buffer from reading an Int
        while (!choice.equalsIgnoreCase("X")) {
            displayEmpMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    break;
                case "B":
                    break;
                case "C":
                    break;
                case "D":
                    break;
                case "X":
                    System.out.println("\nGoodbye!");
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

    public static void searchVideoGame(Scanner input, Customer customerC) {

    }

    public static void viewPurchases(Scanner input, Customer customerC) {

    }

    public static void listVideoGames() {

    }

    public static void shipOrder() {

    }

    public static void displayCustMenu() {
        System.out.println("\nPlease select from the following options:\n\n"
                + "P. Place Order\n"
                + "B. List Video Games\n"
                + "S. Search for Video Game\n"
                + "V. View Purchases\n"
                + "X. Exit\n"); //TODO: temp output
    }

    public static void displayEmpMenu() {
        System.out.println("\nPlease select from the following options:\n\n"
                + "B. View Orders by Priority\n"
                + "S. Search for Customer\n"
                + "O. Ship Orders\n"
                + "A. Add New Product\n"
                + "R. Remove a Product\n"
                + "X. Exit\n"); //TODO: temp output
    }

    public static void fileToCust(Scanner input, HashTable<Customer> customersHT) throws FileNotFoundException {
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
			Customer newC = new Customer(username, fName, lName, email, pw, address, city, state, zip);
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
    }

    public static void ordersToFile(Heap<Order> orderHeap) {

    }

    public static void employeeToFile(HashTable<Employee> employees) {

    }

    public static void customerToFile(HashTable<Customer> customers) {

    }
}
