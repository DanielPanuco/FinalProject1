/**
 * UserInterface.java
 * @author
 * @author
 * CIS 22C, Final Project
 */

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        final int customerSize = 5;
        final int employeeSize = 3;
        Scanner input = new Scanner(System.in);
        HashTable<Customer> customers = new HashTable<>(customerSize * 2);
        HashTable<Employee> employees = new HashTable<>(employeeSize * 2);
        BST<VideoGame> byTitle = new BST<>();
        BST<VideoGame> byDate = new BST<>();

        //Heap<Order> orderHeap = new Heap<>(); need to finish some methods in heap to call this

        fileToVideoGame(input, byTitle, byDate);
        fileToCustomer(input, customers);
        fileToEmployee(input, employees);
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

    public static void fileToCustomer(Scanner input, HashTable<Customer> customers) {

    }

    public static void fileToEmployee(Scanner input, HashTable<Employee> employees) {

    }

    public static void fileToVideoGame(Scanner input, BST<VideoGame> byTitle, BST<VideoGame> byDate) {

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
