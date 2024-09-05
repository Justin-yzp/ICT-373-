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

/**
 * Represents a supplement that can be associated with a magazine subscription.
 */
public class Supplement {

    private int supplementId; // The ID of the supplement.
    private String supplementName; // The name of the supplement.
    private float supplementCost; // The cost of the supplement.

    /**
     * Constructs a supplement with the specified ID, name, and cost.
     *
     * @param supplementId The ID of the supplement.
     * @param supplementName The name of the supplement.
     * @param supplementCost The cost of the supplement.
     */
    public Supplement(int supplementId, String supplementName, float supplementCost) {
        this.supplementId = supplementId;
        this.supplementName = supplementName;
        this.supplementCost = supplementCost;
    }

    /**
     * Retrieves the ID of the supplement.
     *
     * @return The ID of the supplement.
     */
    public int getSupplementId() {
        return supplementId;
    }

    /**
     * Retrieves the name of the supplement.
     *
     * @return The name of the supplement.
     */
    public String getSupplementName() {
        return supplementName;
    }

    /**
     * Retrieves the cost of the supplement.
     *
     * @return The cost of the supplement.
     */
    public float getSupplementCost() {
        return supplementCost;
    }

    /**
     * Sets the ID of the supplement.
     *
     * @param supplementId The ID of the supplement.
     */
    public void setSupplementId(int supplementId) {
        this.supplementId = supplementId;
    }

    /**
     * Sets the name of the supplement.
     *
     * @param supplementName The name of the supplement.
     */
    public void setSupplementName(String supplementName) {
        this.supplementName = supplementName;
    }

    /**
     * Sets the cost of the supplement.
     *
     * @param supplementCost The cost of the supplement.
     */
    public void setSupplementCost(float supplementCost) {
        this.supplementCost = supplementCost;
    }

    /**
     * Displays the details of the supplement, including its ID, name, and cost.
     */
    public void display() {
        System.out.println();
        System.out.println("Supplement ID: " + supplementId);
        System.out.println("Supplement Name: " + supplementName);
        System.out.println("Supplement Cost: " + supplementCost);

    }

    public static void main(String[] args) {
        // Creating some sample supplements
        Supplement supplement1 = new Supplement(1, "Vitamin C", 10.0f);
        Supplement supplement2 = new Supplement(2, "Fish Oil", 15.5f);

        // Displaying the details of the supplements
        System.out.println("Details of Supplement 1:");
        supplement1.display();

        System.out.println("\nDetails of Supplement 2:");
        supplement2.display();

        // Testing the setter methods
        supplement1.setSupplementName("Multivitamin");
        supplement1.setSupplementCost(12.75f);

        // Displaying the updated details of supplement 1
        System.out.println("\nUpdated Details of Supplement 1:");
        supplement1.display();
    }

}
