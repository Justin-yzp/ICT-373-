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
 * Represents a magazine subscription, including details such as ID, name, cost,
 * and associated supplements.
 */
public class Magazine {

    private int magazineId;
    private String magazineName;
    private float magazineCost;
    private List<Supplement> magazineSupplement;

    /**
     * Represents a magazine.
     *
     * @param magazineId The unique identifier of the magazine.
     * @param magazineName The name of the magazine.
     * @param magazineCost The cost of the magazine.
     * @param magazineSupplement The list of supplements associated with the
     * magazine.
     */
    public Magazine(int magazineId, String magazineName, float magazineCost, List<Supplement> magazineSupplement) {
        this.magazineId = magazineId;
        this.magazineName = magazineName;
        this.magazineCost = magazineCost;
        this.magazineSupplement = magazineSupplement;
    }

    /**
     * Retrieves the ID of the magazine.
     *
     * @return The ID of the magazine.
     */
    public int getMagazineId() {
        return magazineId;
    }

    /**
     * Retrieves the name of the magazine.
     *
     * @return The name of the magazine.
     */
    public String getMagazineName() {
        return magazineName;
    }

    /**
     * Retrieves the base cost of the magazine.
     *
     * @return The base cost of the magazine.
     */
    public float getMagazineCost() {
        return magazineCost;
    }

    /**
     * Calculates the total cost of the magazine, including associated
     * supplements.
     *
     * @return The total cost of the magazine.
     */
    public float getTotalMagazineCost() {
        float totalCost = magazineCost; // Start with the magazine's base cost

        // If there are supplements, add their costs
        if (magazineSupplement != null) {
            for (Supplement supplement : magazineSupplement) {
                totalCost += supplement.getSupplementCost();
            }
        }

        return totalCost;
    }

    /**
     * Retrieves the list of supplements associated with the magazine.
     *
     * @return The list of supplements associated with the magazine.
     */
    public List<Supplement> getMagazineSupplement() {
        return magazineSupplement;
    }

    /**
     * Sets the ID of the magazine.
     *
     * @param magazineId The ID of the magazine.
     */
    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }

    /**
     * Sets the name of the magazine.
     *
     * @param magazineName The name of the magazine.
     */
    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    /**
     * Sets the base cost of the magazine.
     *
     * @param magazineCost The base cost of the magazine.
     */
    public void setMagazineCost(float magazineCost) {
        this.magazineCost = magazineCost;
    }

    /**
     * Sets the list of supplements associated with the magazine.
     *
     * @param magazineSupplement The list of supplements associated with the
     * magazine.
     */
    public void setMagazineSupplement(List<Supplement> magazineSupplement) {
        this.magazineSupplement = magazineSupplement;
    }

    /**
     * Adds a supplement to the magazine.
     *
     * @param newSupplement The supplement to add.
     */
    public void addSupplement(Supplement newSupplement) {
        if (magazineSupplement == null) {
            magazineSupplement = new ArrayList<>();
        }
        magazineSupplement.add(newSupplement);
    }

    /**
     * Displays details of the magazine and its associated supplements.
     */
    public void display() {
        System.out.println();
        System.out.println("--------------------");
        System.out.println("Magazine Details");
        System.out.println("Magazine ID: " + magazineId);
        System.out.println("Magazine Name: " + magazineName);
        System.out.println("Magazine Cost: " + magazineCost);
        System.out.println();

        if (magazineSupplement != null) {
            System.out.println("Magazine Supplements Details:");
            for (Supplement supplement : magazineSupplement) {
                supplement.display();
            }
        } else {
            System.out.println("No supplements available for this magazine.");
        }
    }

    /**
     * Main method to test the Magazine class.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        testDisplayMagazineDetails();
        testCalculateTotalMagazineCost();
        testAddSupplementToMagazine();
        testModifyMagazineDetails();
        testNullSupplementListHandling();
    }

    public static void testDisplayMagazineDetails() {
        List<Supplement> supplements = new ArrayList<>();
        supplements.add(new Supplement(1, "Supplement 1", 78));
        supplements.add(new Supplement(2, "Supplement 2", 14));
        supplements.add(new Supplement(3, "Supplement 3", 12));

        Magazine myMagazine = new Magazine(1, "My Magazine", 19.99f, supplements);
        myMagazine.display();
    }

    public static void testCalculateTotalMagazineCost() {
        List<Supplement> supplements = new ArrayList<>();
        supplements.add(new Supplement(1, "Supplement 1", 78));
        supplements.add(new Supplement(2, "Supplement 2", 14));
        supplements.add(new Supplement(3, "Supplement 3", 12));

        Magazine myMagazine = new Magazine(1, "My Magazine", 19.99f, supplements);
        float totalCost = myMagazine.getTotalMagazineCost();
        System.out.println("Total Magazine Cost: " + totalCost);
    }

    public static void testAddSupplementToMagazine() {
        Magazine myMagazine = new Magazine(1, "My Magazine", 19.99f, null);
        myMagazine.addSupplement(new Supplement(4, "Supplement 4", 10));
        myMagazine.display();
    }

    public static void testModifyMagazineDetails() {
        Magazine myMagazine = new Magazine(1, "My Magazine", 19.99f, null);
        myMagazine.setMagazineId(2);
        myMagazine.setMagazineName("Modified Magazine");
        myMagazine.setMagazineCost(29.99f);
        myMagazine.display();
    }

    public static void testNullSupplementListHandling() {
        Magazine myMagazine = new Magazine(1, "My Magazine", 19.99f, null);
        myMagazine.display();
    }
}
