public class UserInterface {
    public static void main(String[] args) {
        final int userSize =100;
        //HashTable<Customer> customers = new HashTable<>(userSize * 2);
        String email;
        // read in files to hashtables
        // sign in
        // call a method for either employee or customer
        int test;
        String pull;
        String test1;
        while (!choice.equalsIgnoreCase("X")) {
			printMenu();
			System.out.print("Enter your choice: ");
			choice = input.nextLine();
			switch (choice.toUpperCase()) {
				case "A":
					addFund(funds, currentC, input);
					break;
				case "B":
					sellFund(currentC, input);
					break;
				case "C":
					addCash(currentC, input);
					break;
				case "D":
					displayFunds(currentC, input);
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

    // method here for customer

    // method for employee
        // switch will have another menu seperate from customer

    // all the other methods

    // display menu employee
    // display menu customer
}
