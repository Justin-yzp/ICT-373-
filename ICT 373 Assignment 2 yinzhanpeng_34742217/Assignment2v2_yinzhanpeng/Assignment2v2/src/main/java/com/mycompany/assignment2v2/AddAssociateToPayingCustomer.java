package com.mycompany.assignment2v2;

/**
 * Title: Assignment2 Author: Yin Zhanpeng Date: 29/3/2024 File
 * Name: Assignment2
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

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides functionality to add an associate customer to a paying
 * customer. It manages the interaction with the user to select from a list of
 * paying and associate customers.
 */
public class AddAssociateToPayingCustomer {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Runs the process of adding an associate customer to a paying customer. It
     * prompts the user to select a paying customer and then an associate
     * customer to be added to the paying customer's list of associates.
     *
     * @param database the database containing the list of paying and associate
     * customers.
     */
    public static void run(Database database) {
        // Display list of paying customers
        List<PayingCustomer> payingCustomers = database.getPayingCustomers();
        if (payingCustomers.isEmpty()) {
            System.out.println("There are no paying customers.");
            return;
        }
        displayCustomers("Paying", payingCustomers);

        // Ask user to select a paying customer
        int payingCustomerIndex = getValidIndex("Enter the index of the paying customer to add associates", payingCustomers.size());

        PayingCustomer selectedPayingCustomer = payingCustomers.get(payingCustomerIndex);

        // Display list of associate customers
        List<Customer> associateCustomers = database.getAssociateCustomers();
        displayCustomers("Associate", associateCustomers);

        // Ask user to select an associate customer to add
        if (!associateCustomers.isEmpty()) {
            int associateCustomerIndex = getValidIndex("Enter the index of the associate customer to add, or -1 to cancel", associateCustomers.size());
            if (associateCustomerIndex != -1) {
                Customer selectedAssociateCustomer = associateCustomers.get(associateCustomerIndex);
                selectedPayingCustomer.addAssociateCustomer(selectedAssociateCustomer);
                System.out.println("Associate added successfully to the paying customer.");
                database.removeAssociateCustomer(selectedAssociateCustomer);
            } else {
                System.out.println("Operation canceled.");
            }
        } else {
            System.out.println("There are no associate customers available to add.");
        }

        //selectedPayingCustomer.display();
    }

    /**
     * Displays a list of customers to the console.
     *
     * @param customerType the type of customer (e.g., "Paying" or "Associate").
     * @param customers the list of customers to be displayed.
     */
    private static void displayCustomers(String customerType, List<? extends Customer> customers) {
        System.out.println("List of " + customerType + " Customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getName());
        }
    }

    /**
     * Prompts the user for a valid index within a given range. It ensures that
     * the input is a valid integer within the range [1, size] inclusive.
     *
     * @param prompt the prompt message to display to the user.
     * @param size the size of the list from which the index is valid.
     * @return the valid index entered by the user, adjusted to be zero-based.
     */
    private static int getValidIndex(String prompt, int size) {
        while (true) {
            System.out.print(prompt + " (1-" + size + "): ");
            try {
                int index = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (index >= 1 && index <= size) {
                    return index - 1;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume invalid input
            }
            System.out.println("Invalid index. Please try again.");
        }
    }
}
