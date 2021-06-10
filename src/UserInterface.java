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
	
    public static void main(String[] args) throws IOException{
        final int customerSize = 5, employeeSize = 3;
        Scanner input = new Scanner(System.in);
        HashTable<Customer> customersHT = new HashTable<>(customerSize * 2);
        HashTable<Employee> employeesHT = new HashTable<>(employeeSize * 2);
        BST<VideoGame> vgbyTitle = new BST<>();
        BST<VideoGame> vgbyDate = new BST<>();
       
        //Heap<Order> orderHeap = new Heap<>(); need to finish some methods in heap to call this

        fileToVG(input, vgbyTitle, vgbyDate);
        fileToCust(input, customersHT);
        fileToEmp(input, employeesHT);
        //fileToOrders(input, orderHeap);

        String email;
        int typeOfUser;
        System.out.println("What type of user are you? (1 Customer | 2 Employee): ");
        typeOfUser = input.nextInt();
        if (typeOfUser == 1) {
            custInterface(input);
        } else {
            empInterface(input);
        }
        // read in files to hashtables
        // sign in
        // call a method for either employee or customer
    }

    public static void custInterface(Scanner input) {
        String choice = "";
        while (!choice.equalsIgnoreCase("X")) {
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
                + "P. Place Order\n"
                + "B. View Orders by Priority\n"
                + "S. Search for Customer\n"
                + "O. Ship Orders\n"
                + "A. Add New Product\n"
                + "X. Exit\n"); //TODO: temp output
    }

    public static void fileToCust(Scanner input, HashTable<Customer> customersHT) throws FileNotFoundException {
    	String username, fName, lName, email, pw, address, city, state;
		int zip;
    	File file = new File(custFile);
		input = new Scanner(file);
		
		while (input.hasNextLine()) {
			username = input.nextLine();
			//System.out.println("uName: " +username);
			fName = input.nextLine();
			//System.out.println("fname: " +fName);
			lName = input.nextLine();
			//System.out.println("lname: " +lName);
			email = input.nextLine();
			//System.out.println("email: " +email);
			pw = input.nextLine();
			//System.out.println("pw: " + pw);
			address = input.nextLine();
			//System.out.println("add: " + address);
			city = input.nextLine();
			//System.out.println("city: " + city);
			state = input.nextLine();
			//System.out.println("state: " + state);
			zip = input.nextInt();
			//System.out.println("zip:" + zip);
			if (input.hasNextLine()) {
				input.nextLine();  //clear buffer
				input.nextLine();
			}
			Customer newC = new Customer(fName, lName, email, pw, address, city, state, zip);
			//System.out.println(newC);
			customersHT.insert(newC);
		
		}
		input.close();
    }

	public static void fileToEmp(Scanner input, HashTable<Employee> empHT)
			throws FileNotFoundException {
    	//File file = new File(empFile);
		//input = new Scanner(file);
		//while loop to read in
		//input.close();
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
			// System.out.println(title);
			dev = input.nextLine();
			// System.out.println(dev);
			rDate = input.nextInt();
			// System.out.println(rDate);
			price = input.nextDouble();
			// System.out.println(price);
			input.nextLine(); // clear buffer
			genre = input.nextLine();
			// System.out.println(genre);
			ESRB = input.nextLine();
			// System.out.println(ESRB);
			mcScore = input.nextInt();
			// System.out.println(mcScore);
			input.nextLine();
			pform = input.nextLine();
			//System.out.println(pform);
			if (input.hasNextLine()) {
				input.nextLine();
			}
			VideoGame newVG = new VideoGame(title, dev, rDate, price, genre,
					ESRB, mcScore, pform);
			//System.out.println(newVG); //needs toString to be finished first
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
