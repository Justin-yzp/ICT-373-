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
/**
 * This class represents the main entry point of the Assignment2 application.
 */
public class Assignment2 {

    /**
     * The shared database instance used throughout the application.
     */
    static Database database = new Database();

    /**
     * The main method of the Assignment2 application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        database.populate();
        handleChoice();

    }

    /**
     * Handles user choices through a menu system, allowing users to perform
     * various actions in the application.
     */
    public static void handleChoice() {
        while (true) {
            switch (Display.menu()) {
                case 1:
                    CreateMagazine.run(database);
                    break;
                case 2:
                    CreateSupplement.run(database);
                    break;
                case 3:
                    CreateCustomer.run(database);
                    break;
                case 4:
                    AddAssociateToPayingCustomer.run(database);
                    break;
                case 5:
                    FourWeekCustomerEmailPrint.run(database);
                    break;
                case 6:
                    EndOfMonthPayingCustomerEmailPrint.run(database);
                    break;
                case 7:
                    AddNewCustomerToMagazineService.run(database);
                    break;
                case 8:
                    RemoveCustomerFromMagazineService.run(database);
                    break;
                case 9:
                    AddSupplementToCustomerOrMagazine.run(database);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return; // Exit the method and thus end the loop
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
