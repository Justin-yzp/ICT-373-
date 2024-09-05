package com.mycompany.assignment2v2;

/**
 * Title: Assignment2 Author: Yin Zhanpeng Date: 29/3/2024 File Name:
 * Assignment2
 *
 * <p>
 * Program Description:</p>
 * <p>
 * This program simulates the weekly and monthly notifications for customers
 * subscribed to magazines and supplements. It allows user interactions for
 * managing subscriptions and customer information.</p>
 *
 * <p>
 * Assumptions/Conditions:</p>
 * <ol>
 * <li>The customers have a list of Supplements and Magazines.</li>
 * <li>Supplements can be individual or included within magazines.</li>
 * <li>Magazines contain their own list of Supplements.</li>
 * <li>The program allows the user to add supplements to both magazines and
 * customers.</li>
 * <li>Paying customers have a list of associate customers.</li>
 * <li>Both the paying and associate customers have their own
 * subscriptions.</li>
 * <li>The program only simulates weekly and monthly notifications.</li>
 * <li>Associate customers of a paying customer can be removed.</li>
 * <li>Removing a paying customer also removes its associated customers.</li>
 * <li>Customers contain magazines, not the other way around.</li>
 * <li>There should be at least one paying customer.</li>
 * <li>Textual data inputs for customer names, addresses, email addresses,
 * supplement names, etc., are handled.</li>
 * <li>Payment methods are represented as strings or simple identifiers.</li>
 * <li>The GUI is designed for desktop use and is not optimized for mobile
 * devices.</li>
 * <li>Billing history is displayed in a simple tabular format.</li>
 * <li>Customer address details are limited to basic information.</li>
 * <li>The program handles a reasonable number of entities without significant
 * performance degradation.</li>
 * <li>Basic error handling is implemented for scenarios such as invalid input
 * formats and file I/O errors.</li>
 * <li>The GUI layout is implemented using JavaFX controls and layouts.</li>
 * <li>The program is developed and tested on the Java SE 8 platform using
 * NetBeans IDE.</li>
 * <li>Data persistence is achieved through serialization.</li>
 * </ol>
 */
import java.util.Scanner;

/**
 * This class provides functionality to add a new magazine to a customer in the
 * magazine service.
 */
public class AddNewCustomerToMagazineService {

    /**
     * Runs the process of adding a new magazine to a customer in the database.
     *
     * @param database The database containing customer and magazine
     * information.
     */
    public static void run(Database database) {
        Scanner scanner = new Scanner(System.in);

        // List out all customers (both paying and associate)
        System.out.println("List of All Customers:");
        int index = 1;
        for (PayingCustomer payingCustomer : database.getPayingCustomers()) {
            System.out.println(index + ". " + payingCustomer.getName() + " (Paying Customer)");
            index++;
            for (Customer associateCustomer : payingCustomer.getAssociateCustomers()) {
                System.out.println(index + ". " + associateCustomer.getName() + " (Associate Customer)");
                index++;
            }
        }

        // Select customer
        int selectedCustomerIndex = getUserSelection("Enter the index of the customer: ", index);

        // Find selected customer
        Customer selectedCustomer = findCustomerByIndex(database, selectedCustomerIndex);

        // List out magazines
        System.out.println("List of Magazines:");
        for (int i = 0; i < database.getMagazines().size(); i++) {
            System.out.println((i + 1) + ". " + database.getMagazines().get(i).getMagazineName());
        }

        // Select magazine
        int selectedMagazineIndex = getUserSelection("Enter the index of the magazine to add: ", database.getMagazines().size());
        Magazine selectedMagazine = database.getMagazines().get(selectedMagazineIndex - 1);

        // Add magazine to selected customer
        selectedCustomer.addMagazine(selectedMagazine);
        System.out.println("Magazine added successfully to " + selectedCustomer.getName());
        selectedCustomer.display();
    }

    /**
     * Prompts the user for a selection and ensures it is within a valid range.
     *
     * @param prompt The message to display prompting the user for input.
     * @param size The maximum valid selection value.
     * @return The user's valid selection.
     */
    private static int getUserSelection(String prompt, int size) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(prompt);
                selection = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (selection >= 1 && selection <= size) {
                    validInput = true;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + size + ".");
                }
            } catch (Exception e) {
                scanner.nextLine(); // Consume invalid input
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return selection;
    }

    /**
     * Finds a customer in the database based on their index.
     *
     * @param database The database containing customer information.
     * @param index The index of the customer to find.
     * @return The customer corresponding to the given index, or null if not
     * found.
     */
    private static Customer findCustomerByIndex(Database database, int index) {
        int currentIndex = 1;
        for (PayingCustomer payingCustomer : database.getPayingCustomers()) {
            if (currentIndex == index) {
                return payingCustomer;
            }
            currentIndex++;
            for (Customer associateCustomer : payingCustomer.getAssociateCustomers()) {
                if (currentIndex == index) {
                    return associateCustomer;
                }
                currentIndex++;
            }
        }
        return null;
    }
}
