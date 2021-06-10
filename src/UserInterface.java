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
	private static final String vgFile = ("product.txt"); //maybe change to videogames.txt
	//once we decide on a String txt name it's good to make it final (taught in 36B)
	//(if we don't want to end up changing it later on)
    public static void main(String[] args) throws IOException{
        final int customerSize = 5;
        final int employeeSize = 3;
        Scanner input = new Scanner(System.in);
        HashTable<Customer> customers = new HashTable<>(customerSize * 2);
        HashTable<Employee> employees = new HashTable<>(employeeSize * 2);
        BST<VideoGame> byTitle = new BST<>();
        BST<VideoGame> byDate = new BST<>();
       
        //Heap<Order> orderHeap = new Heap<>(); need to finish some methods in heap to call this

        fileToVG(input, byTitle, byDate);
        fileToCust(input, customers);
        fileToEmp(input, employees);
        //fileToOrders(input, orderHeap);

        String email;
        int typeOfUser;
        System.out.println("What type of user are you? (1 Customer | 2 Employee): ");
        typeOfUser = input.nextInt();
        if (typeOfUser == 1) {
            customerInterface(input);
        } else {
            employeeInterface(input);
        }
        // read in files to hashtables
        // sign in
        // call a method for either employee or customer
    }

    public static void customerInterface(Scanner input) {
        String choice = "";
        while (!choice.equalsIgnoreCase("X")) {
            displayCustomerMenu();
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

    public static void employeeInterface(Scanner input) {
        String choice = "";
        while (!choice.equalsIgnoreCase("X")) {
            displayEmployeeMenu();
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

    public static void displayCustomerMenu() {
        System.out.println("\nPlease select from the following options:\n\n"
                + "P. Place Order\n"
                + "B. List Video Games\n"
                + "S. Search for Video Game\n"
                + "V. View Purchases\n"
                + "X. Exit\n"); //TODO: temp output
    }

    public static void displayEmployeeMenu() {
        System.out.println("\nPlease select from the following options:\n\n"
                + "P. Place Order\n"
                + "B. View Orders by Priority\n"
                + "S. Search for Customer\n"
                + "O. Ship Orders\n"
                + "A. Add New Product\n"
                + "X. Exit\n"); //TODO: temp output
    }

    public static void fileToCust(Scanner input, HashTable<Customer> customers) {

    }

    public static void fileToEmp(Scanner input, HashTable<Employee> employees) {
    	
    }

	public static void fileToVG(Scanner input, BST<VideoGame> vgByTitle,
			BST<VideoGame> vgByDate) throws FileNotFoundException {
		File file = new File(vgFile);
		input = new Scanner(file);
		String title, dev, genre, ESRB, pform;
		double price;
		int rDate, mcScore;
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
			//System.out.println(newVG); needs constructor to be finished first
			// byTitle.insert(newVG, null); //will replace both nulls with
			// comparator
			// byDate.insert(newVG, null);

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
