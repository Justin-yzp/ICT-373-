/*
 * Title: Assignment 1 - Question 2
 * Author: Yin Zhanpeng
 * Date: 25/2/2024
 * File Name: Assignment1Q2.java
 * 
 * Assumptions/Conditions:
 * 1. The customers have a list of Supplements and Magazines.
 * 2. The Supplements can be individual or inside the magazines.
 * 3. The magazines also contain their own List of Supplements.
 * 4. The program allows the user to add supplements to both the magazines and the customer.
 * 5. The paying customer has a list of associated customers.
 * 6. Both the paying customer and the associated customers have their own subscriptions.
 * 7. This program only simulates weekly and monthly notifications.
 * 8. It is possible to remove associated customers of a paying customer.
 * 9. If a paying customer is removed, its associated customers will also be removed.
 * 10. The customer contains the magazine and not vice versa.
 * 11. The paying customer should have at least one associated customer.
 */
package com.mycompany.assignment;

import java.util.Scanner;

/**
 * Represents a menu for managing customer subscriptions and related operations.
 * Provides methods to display various options and receive user input.
 */
public class Menu {

    /**
     * Constructs a Menu object.
     */
    public Menu() {
        // Default constructor does not require any additional comments.
    }

    /**
     * Displays the menu options and prompts the user for input.
     *
     * @return The user's choice as an integer.
     */
    public static int displayMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Display all the Paying Customers and their Associate Customer");
        System.out.println("2. create a new Paying Customer");  // done
        System.out.println("3. Remove Customer");
        System.out.println("4. create Associate Customer to Paying customer");
        System.out.println("5. Remove Associate Customer to Paying customer");
        System.out.println("6. Add Magazine to Customer");  //done
        System.out.println("7. Remove Magazine from Customer");
        System.out.println("8. Add Supplement to Customer");  // done
        System.out.println("9. Remove Supplement to Customer");
        System.out.println("10. Display Weekly Notification for 1 Customer");
        System.out.println("11. Display Monthly Notification for 1 Paying Customer");
        System.out.println("12. Display 4 Weekly Notification for all Customer");
        System.out.println("13. Display Monthly Notification for all Paying Customer");
        System.out.println("14. Quit");

        System.out.print("Enter your choice (1-10): ");
        int choice = scanner.nextInt();

        // Validate input
        while (choice < 1 || choice > 14) {
            System.out.println("Invalid choice. Please enter a number between 1 and 11.");
            System.out.print("Enter your choice (1-10): ");
            choice = scanner.nextInt();
        }

        return choice;
    }

}
