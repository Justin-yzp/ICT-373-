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
 * Represents a paying customer who subscribes to magazines and supplements,
 * with additional payment and association details.
 */
public class PayingCustomer extends Customer {

    /**
     * Retrieves the selected payment method for the customer.
     *
     * @return The selected payment method for the customer.
     */
    private PaymentMethod selectedPaymentMethod; // The selected payment method for the customer.

    /**
     * Enum representing different payment methods.
     */
    public enum PaymentMethod {

        /**
         * Credit card payment method.
         */
        CREDIT_CARD,
        /**
         * Bank card payment method.
         */
        BANK_CARD
    }
    private String bank; // The bank associated with the customer's payment method.
    private List<Customer> associateCustomer; // List of associated customers.

    /**
     * Constructor for PayingCustomer class.
     *
     * @param selectedPaymentMethod The selected payment method for the
     * customer.
     * @param bank The bank associated with the customer's payment method.
     * @param associateCustomer List of associated customers.
     * @param name Name of the paying customer.
     * @param email Email of the paying customer.
     * @param magazine List of magazines subscribed by the paying customer.
     * @param supplement List of supplements subscribed by the paying customer.
     */
    public PayingCustomer(PaymentMethod selectedPaymentMethod, String bank, List<Customer> associateCustomer, String name, String email, List<Magazine> magazine, List<Supplement> supplement) {
        super(name, email, magazine, supplement);
        this.selectedPaymentMethod = selectedPaymentMethod;
        this.bank = bank;
        this.associateCustomer = associateCustomer;
    }

    /**
     * Retrieves the selected payment method for the customer.
     *
     * @return The selected payment method for the customer.
     */
    public PaymentMethod getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    /**
     * Retrieves the bank associated with the customer's payment method.
     *
     * @return The bank associated with the customer's payment method.
     */
    public String getBank() {
        return bank;
    }

    /**
     * Retrieves the list of associated customers.
     *
     * @return The list of associated customers.
     */
    public List<Customer> getAssociateCustomer() {
        return associateCustomer;
    }

    /**
     * Sets the selected payment method for the customer.
     *
     * @param selectedPaymentMethod The selected payment method for the
     * customer.
     */
    public void setSelectedPaymentMethod(PaymentMethod selectedPaymentMethod) {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    /**
     * Sets the bank associated with the customer's payment method.
     *
     * @param bank The bank associated with the customer's payment method.
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * Sets the list of associated customers.
     *
     * @param associateCustomer The list of associated customers.
     */
    public void setAssociateCustomer(List<Customer> associateCustomer) {
        this.associateCustomer = associateCustomer;
    }

    /**
     * Adds an associated customer to the list.
     *
     * @param customer The associated customer to add.
     */
    public void addAssociateCustomer(Customer customer) {
        if (this.associateCustomer == null) {
            this.associateCustomer = new ArrayList<>();
        }
        this.associateCustomer.add(customer);
    }

    /**
     * Sends a weekly notification to the paying customer with subscription
     * details.
     */
    @Override
    public void weeklyNotification() {
        float totalCost = 0;

        // Display customer details
        System.out.println();
        System.out.println("Paying Customer Details:");
        System.out.println("Customer Name: " + this.getName());
        System.out.println("Customer Email: " + this.getEmail());
        System.out.println("Selected Payment Method: " + this.selectedPaymentMethod);
        System.out.println("Bank: " + this.bank);
        System.out.println();

        // List the magazine and its supplements
        if (this.getMagazines() != null && !this.getMagazines().isEmpty()) {
            System.out.println("Magazines and their associated supplements:");
            for (Magazine mag : this.getMagazines()) {
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
        if (this.getSupplement() != null && !this.getSupplement().isEmpty()) {
            System.out.println("Standalone Supplements:");
            for (Supplement supp : this.getSupplement()) {
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
     * Sends a monthly notification to the paying customer with subscription
     * details, including associated customers.
     */
    public void monthlyNotification() {
        // Display customer details
        System.out.println("Monthly Notification for Paying Customer:");
        System.out.println("Customer Name: " + this.getName());
        System.out.println("Customer Email: " + this.getEmail());
        System.out.println("Selected Payment Method: " + this.selectedPaymentMethod);
        System.out.println("Bank: " + this.bank);
        System.out.println();

        // Calculate and display total cost for the paying customer
        float totalCost = calculateAndDisplayCustomerCost(this);

        // Display associate customers and their associated magazines and supplements
        if (this.associateCustomer != null && !this.associateCustomer.isEmpty()) {
            for (Customer associate : this.associateCustomer) {
                // Calculate and display total cost for the associate customer
                totalCost += calculateAndDisplayCustomerCost(associate);
            }
        } else {
            System.out.println("No associate customers.");
        }

        // Display total cost for the paying customer and their associate customers
        System.out.println("Total Cost for the Month (Including Associate Customers): " + totalCost);
    }

    /**
     * Helper method to calculate and display the total cost for a customer
     * (magazines + supplements).
     *
     * @param customer The customer for whom to calculate the total cost.
     * @return The total cost for the customer.
     */
    private float calculateAndDisplayCustomerCost(Customer customer) {
        float customerTotalCost = 0;

        // Display customer name and email
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Email: " + customer.getEmail());

        // Display magazines and their associated costs
        if (customer.getMagazines() != null && !customer.getMagazines().isEmpty()) {
            System.out.println("Magazines and their associated costs:");
            for (Magazine mag : customer.getMagazines()) {
                float magazineCost = mag.getMagazineCost();
                System.out.println("Magazine ID: " + mag.getMagazineId() + ", Cost: " + magazineCost);
                customerTotalCost += magazineCost;

                // Display supplements for this magazine
                List<Supplement> magazineSupplements = mag.getMagazineSupplement();
                if (magazineSupplements != null && !magazineSupplements.isEmpty()) {
                    System.out.println("Supplements in this Magazine:");
                    for (Supplement supplement : magazineSupplements) {
                        float supplementCost = supplement.getSupplementCost();
                        System.out.println("\tSupplement ID: " + supplement.getSupplementId() + ", Cost: " + supplementCost);
                        customerTotalCost += supplementCost;
                    }
                }
            }
        } else {
            System.out.println("No magazines associated with this customer.");
        }

        // Display standalone supplements and their costs
        if (customer.getSupplement() != null && !customer.getSupplement().isEmpty()) {
            System.out.println("Standalone Supplements and their costs:");
            for (Supplement supp : customer.getSupplement()) {
                float supplementCost = supp.getSupplementCost();
                System.out.println("Supplement ID: " + supp.getSupplementId() + ", Cost: " + supplementCost);
                customerTotalCost += supplementCost;
            }
        } else {
            System.out.println("No standalone supplements associated with this customer.");
        }

        // Display total cost for the customer
        System.out.println("Total Cost for the Customer: " + customerTotalCost);
        System.out.println();

        return customerTotalCost;
    }

    /**
     * Displays the details of the paying customer, including associated
     * magazines, supplements, and customers.
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Paying Customer Details:");
        System.out.println("Customer Name: " + this.getName());
        System.out.println("Customer Email: " + this.getEmail());
        System.out.println("Selected Payment Method: " + this.selectedPaymentMethod);
        System.out.println("Bank: " + this.bank);
        System.out.println();

        // Display associated magazines
        if (this.getMagazines() != null && !this.getMagazines().isEmpty()) {
            System.out.println("-----------------------------------------");
            System.out.println("Magazines and their associated supplements:");
            System.out.println("-----------------------------------------");
            for (Magazine mag : this.getMagazines()) {
                mag.display();
            }
        } else {
            System.out.println("No magazines associated with this customer.");
        }
        System.out.println("---------------------------------");

        // Display associated supplements
        if (this.getSupplement() != null && !this.getSupplement().isEmpty()) {
            System.out.println("---Supplement Details---:");
            for (Supplement supp : this.getSupplement()) {
                supp.display();
            }
        } else {
            System.out.println("No supplements associated with this customer.");
        }

        // Display associated customers
        if (this.associateCustomer != null && !this.associateCustomer.isEmpty()) {
            System.out.println();
            System.out.println("-----------------------------------------------");
            System.out.println("Associated Customers:");

            for (Customer customer : this.associateCustomer) {
                customer.display();
            }
        } else {
            System.out.println("No associated customers.");
        }
    }

    public static void main(String[] args) {
        // Create magazines
        List<Magazine> magazines = new ArrayList<>();
        magazines.add(new Magazine(1, "Magazine 1", 15.0f, null));
        magazines.add(new Magazine(2, "Magazine 2", 20.0f, null));

        // Create supplements
        List<Supplement> supplements = new ArrayList<>();
        supplements.add(new Supplement(1, "Supplement 1", 10.0f));
        supplements.add(new Supplement(2, "Supplement 2", 12.0f));

        // Create a paying customer
        PayingCustomer payingCustomer = new PayingCustomer(
                PayingCustomer.PaymentMethod.CREDIT_CARD,
                "My Bank",
                null,
                "John Doe",
                "john.doe@example.com",
                magazines,
                supplements
        );

        // Add associated customers
        List<Magazine> associatedMagazines = new ArrayList<>();
        associatedMagazines.add(new Magazine(3, "Associated Magazine 1", 18.0f, null));
        associatedMagazines.add(new Magazine(4, "Associated Magazine 2", 22.0f, null));

        List<Supplement> associatedSupplements = new ArrayList<>();
        associatedSupplements.add(new Supplement(3, "Associated Supplement 1", 8.0f));
        associatedSupplements.add(new Supplement(4, "Associated Supplement 2", 15.0f));

        Customer associatedCustomer = new Customer("Alice", "alice@example.com", associatedMagazines, associatedSupplements);
        payingCustomer.addAssociateCustomer(associatedCustomer);

        // Display details
        System.out.println("Displaying Paying Customer Details:");
        payingCustomer.display();

        // Send weekly notification
        System.out.println("\nSending Weekly Notification to Paying Customer:");
        payingCustomer.weeklyNotification();

        // Send monthly notification
        System.out.println("\nSending Monthly Notification to Paying Customer:");
        payingCustomer.monthlyNotification();
    }

}
