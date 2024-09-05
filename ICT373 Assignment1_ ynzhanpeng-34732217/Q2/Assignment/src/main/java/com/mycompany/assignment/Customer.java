
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

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer who subscribes to magazines and supplements. Each
 * customer has a name, email address, and lists of magazines and supplements
 * they are subscribed to.
 *
 * @author justin
 */
public class Customer {

    private String name;// The name of the customer
    private String email;// The email address of the customer.
    private List<Magazine> magazine;// The list of magazines subscribed by the customer.
    private List<Supplement> supplement;// The list of supplements subscribed by the customer.

    /**
     * Constructs a Customer object with the specified name, email, list of
     * magazines, and list of supplements.
     *
     * @param name The name of the customer.
     * @param email The email address of the customer.
     * @param magazine The list of magazines subscribed by the customer.
     * @param supplement The list of supplements subscribed by the customer.
     */
    public Customer(String name, String email, List<Magazine> magazine, List<Supplement> supplement) {
        this.name = name;
        this.email = email;
        this.magazine = magazine;
        this.supplement = supplement;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the email of the customer.
     *
     * @return The email of the customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the list of magazines subscribed by the customer.
     *
     * @return The list of magazines subscribed by the customer.
     */
    public List<Magazine> getMagazines() {
        if (magazine == null) {
            magazine = new ArrayList<>(); // Initialize the list if it's null
        }
        return magazine;
    }

    /**
     * Retrieves the list of standalone supplements subscribed by the customer.
     *
     * @return The list of standalone supplements subscribed by the customer.
     */
    public List<Supplement> getSupplement() {
        if (supplement == null) {
            supplement = new ArrayList<>();
        }
        return supplement;
    }

    /**
     * Calculates the total cost of all subscribed magazines.
     *
     * @return The total cost of all subscribed magazines.
     */
    public float getTotalMagazineCost() {
        float totalMagazineCost = 0;
        if (magazine != null) {
            for (Magazine mag : magazine) {
                totalMagazineCost += mag.getTotalMagazineCost();
            }
        }
        return totalMagazineCost;
    }

    /**
     * Calculates the total cost of all subscribed standalone supplements.
     *
     * @return The total cost of all subscribed standalone supplements.
     */
    public float getTotalSupplementCost() {
        float totalSupplementCost = 0;
        if (supplement != null) {
            for (Supplement supp : supplement) {
                totalSupplementCost += supp.getSupplementCost();
            }
        }
        return totalSupplementCost;
    }

    /**
     * Calculates the total cost of all subscriptions.
     *
     * @return The total cost of all subscriptions.
     */
    public float getTotalCost() {
        return getTotalMagazineCost() + getTotalSupplementCost();
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email of the customer.
     *
     * @param email The email of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the list of magazines subscribed by the customer.
     *
     * @param magazine The list of magazines subscribed by the customer.
     */
    public void setMagazine(List<Magazine> magazine) {
        this.magazine = magazine;
    }

    /**
     * Sets the list of standalone supplements subscribed by the customer.
     *
     * @param supplement The list of standalone supplements subscribed by the
     * customer.
     */
    public void setSupplement(List<Supplement> supplement) {
        this.supplement = supplement;
    }

    /**
     * Sends a weekly notification to the customer with subscription details.
     */
    public void weeklyNotification() {
        float totalCost = 0;
        System.out.println();
        System.out.println("Paying Customer Details:");
        System.out.println("Customer Name: " + this.getName());
        System.out.println("Customer Email: " + this.getEmail());

        // List the magazine and its supplements
        if (magazine != null && !magazine.isEmpty()) {
            System.out.println("Magazine and its associated supplements:");
            for (Magazine mag : magazine) {
                System.out.println("Magazine ID: " + mag.getMagazineId());
                System.out.println("Magazine Name: " + mag.getMagazineName());
                System.out.println("Magazine Cost: " + mag.getMagazineCost());
                System.out.println("Magazine Total Cost: " + mag.getTotalMagazineCost());

                List<Supplement> magazineSupplements = mag.getMagazineSupplement();
                if (magazineSupplements != null && !magazineSupplements.isEmpty()) {
                    System.out.println("Supplements:");
                    for (Supplement supplement : magazineSupplements) {
                        System.out.println("\tSupplement ID: " + supplement.getSupplementId());
                        System.out.println("\tSupplement Name: " + supplement.getSupplementName());
                        System.out.println("\tSupplement Cost: " + supplement.getSupplementCost());
                    }
                } else {
                    System.out.println("No supplements associated with this magazine.");
                }
                System.out.println("-----------------------------------------");

                totalCost += mag.getTotalMagazineCost();
            }
        } else {
            System.out.println("No magazines associated with this customer.");
        }

        // List standalone supplements
        if (supplement != null && !supplement.isEmpty()) {
            System.out.println("Standalone Supplements:");
            for (Supplement supp : supplement) {
                System.out.println("Supplement ID: " + supp.getSupplementId());
                System.out.println("Supplement Name: " + supp.getSupplementName());
                System.out.println("Supplement Cost: " + supp.getSupplementCost());

                totalCost += supp.getSupplementCost();
            }
        } else {
            System.out.println("No standalone supplements associated with this customer.");
        }

        // Display total cost
        System.out.println("Total Cost: " + totalCost);
    }

    /**
     * Displays the details of the customer and their subscriptions.
     */
    public void display() {
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("Associate Customer details");
        System.out.println("Customer Name: " + this.name);
        System.out.println("Customer Email: " + this.email);
        System.out.println();

        if (magazine != null && !magazine.isEmpty()) {
            System.out.println("-----------------------------------------");
            System.out.println("Magazine and it's associated supplements");
            System.out.println("-----------------------------------------");
            for (Magazine mag : magazine) {
                mag.display();  // Call the display method in Magazine class
            }
        } else {
            System.out.println("No magazines associated with this customer.");
        }
        System.out.println("---------------------------------");
        // Display supplement details
        if (supplement != null && !supplement.isEmpty()) {
            System.out.println("---Supplement Details---:");
            for (Supplement supp : supplement) {
                supp.display();  // Call the display method in Supplement class
            }
        } else {
            System.out.println("No supplements associated with this customer.");
        }
    }

    /**
     * Adds a standalone supplement to the customer's subscription.
     *
     * @param newSupplement The standalone supplement to add.
     */
    public void addStandaloneSupplement(Supplement newSupplement) {
        if (supplement == null) {
            supplement = new ArrayList<>();
        }
        supplement.add(newSupplement);
    }

    /**
     * Main method to test the Customer class.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Test 1: Creating a customer with magazines and displaying details
        // Testing for creating a customer object with magazines and displaying the customer details
        List<Magazine> magazines = new ArrayList<>();
        List<Supplement> supplements1 = new ArrayList<>();
        supplements1.add(new Supplement(1, "Supplement 1", 10));
        supplements1.add(new Supplement(2, "Supplement 2", 15));
        magazines.add(new Magazine(1, "Magazine 1", 25, supplements1));
        List<Supplement> supplements2 = new ArrayList<>();
        supplements2.add(new Supplement(3, "Supplement 3", 20));
        magazines.add(new Magazine(2, "Magazine 2", 30, supplements2));
        Customer customer = new Customer("Alice", "alice@example.com", magazines, null);
        customer.display(); // Displaying the customer details

        // Test 2: Adding standalone supplements and displaying updated details
        // Testing for adding standalone supplements to the customer and displaying the updated customer details
        Supplement standalone1 = new Supplement(4, "Supplement 4", 10);
        Supplement standalone2 = new Supplement(5, "Supplement 5", 15);
        customer.addStandaloneSupplement(standalone1);
        customer.addStandaloneSupplement(standalone2);
        customer.display(); // Displaying the updated customer details

        // Test 3: Calculating and displaying the total cost
        // Testing for calculating and displaying the total cost of magazines and standalone supplements
        System.out.println("Total cost of magazines and supplements: $" + customer.getTotalCost());

        // Test 4: Sending a weekly notification to the customer
        // Testing for sending a weekly notification to the customer with subscription details
        customer.weeklyNotification(); // Sending a weekly notification
    }

}
