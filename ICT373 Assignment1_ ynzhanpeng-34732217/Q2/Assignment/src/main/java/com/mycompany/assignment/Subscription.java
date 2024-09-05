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

import java.util.List;

/**
 * Represents a subscription associated with a paying customer. This class
 * calculates the total cost of magazines and supplements for the subscription
 * and provides methods to retrieve and set subscription details.
 */
public final class Subscription {

    /**
     * The paying customer associated with the subscription.
     */
    PayingCustomer customer;
    /**
     * The start date of the subscription.
     */
    private String startSubscriptionDate;
    /**
     * The total weekly amount for the subscription.
     */
    private double weeklyAmount;
    /**
     * The total monthly amount for the subscription.
     */
    private double monthlyamount;

    /**
     * Constructs a new Subscription object with the specified customer and
     * start date. Calculates the total weekly and monthly amounts based on the
     * associated customer's magazine and supplement costs.
     *
     * @param customer The paying customer associated with the subscription.
     * @param startSubscriptionDate The start date of the subscription.
     */
    public Subscription(PayingCustomer customer, String startSubscriptionDate) {
        this.customer = customer;
        this.startSubscriptionDate = startSubscriptionDate;
        this.weeklyAmount = CombineMagazineCost() + combineSupplementCost();
        this.monthlyamount = CombineMagazineCost() + combineSupplementCost() * 4;
    }

    /**
     * Calculates the total cost of magazines for the paying customer.
     *
     * @return The total cost of magazines for the paying customer.
     */
    public double payingCustomerMagazineCost() {
        if (customer != null) {
            return customer.getTotalMagazineCost();
        } else {
            return 0.0; // Or any other default value you prefer
        }
    }

    /**
     * Calculates the total cost of supplements for the paying customer.
     *
     * @return The total cost of supplements for the paying customer.
     */
    public double payingCustomerSupplementCost() {
        if (customer != null) {
            return customer.getTotalSupplementCost();
        } else {
            return 0.0; // Or any other default value you prefer
        }
    }

    /**
     * Calculates the total cost of magazines for associate customers.
     *
     * @return The total cost of magazines for associate customers.
     */
    public double associateCustomerMagazineCost() {
        double cost = 0;
        if (customer != null && customer.getAssociateCustomer() != null) {
            for (Customer associateCustomer : customer.getAssociateCustomer()) {
                if (associateCustomer != null) {
                    cost += associateCustomer.getTotalMagazineCost();
                }
            }
        }
        return cost;
    }

    /**
     * Calculates the total cost of supplements for associate customers.
     *
     * @return The total cost of supplements for associate customers.
     */
    public double associateCustomerSupplementCost() {
        double cost = 0;
        if (customer != null && customer.getAssociateCustomer() != null) {
            for (Customer associateCustomer : customer.getAssociateCustomer()) {
                if (associateCustomer != null) {
                    cost += associateCustomer.getTotalSupplementCost();
                }
            }
        }
        return cost;
    }

    /**
     * Combines the total magazine cost for both paying and associate customers.
     *
     * @return The combined total magazine cost.
     */
    public double CombineMagazineCost() {

        return payingCustomerMagazineCost() + associateCustomerMagazineCost();
    }

    /**
     * Combines the total supplement cost for both paying and associate
     * customers.
     *
     * @return The combined total supplement cost.
     */
    public double combineSupplementCost() {

        return associateCustomerSupplementCost() + payingCustomerSupplementCost();
    }

    /**
     * Retrieves the paying customer associated with the subscription.
     *
     * @return The paying customer associated with the subscription.
     */
    public PayingCustomer getCustomer() {
        return customer;
    }

    /**
     * Retrieves the start date of the subscription.
     *
     * @return The start date of the subscription.
     */
    public String getStartSubscriptionDate() {
        return startSubscriptionDate;
    }

    /**
     * Retrieves the total weekly amount for the subscription.
     *
     * @return The total weekly amount for the subscription.
     */
    public double getWeeklyAmount() {
        return weeklyAmount;
    }

    /**
     * Retrieves the total monthly amount for the subscription.
     *
     * @return The total monthly amount for the subscription.
     */
    public double getMonthlyamount() {
        return monthlyamount;
    }

    /**
     * Sets the paying customer associated with the subscription.
     *
     * @param customer The paying customer to set.
     */
    public void setCustomer(PayingCustomer customer) {
        this.customer = customer;
    }

    /**
     * Sets the start date of the subscription.
     *
     * @param startSubscriptionDate The start date to set.
     */
    public void setStartSubscriptionDate(String startSubscriptionDate) {
        this.startSubscriptionDate = startSubscriptionDate;
    }

    /**
     * Sets the total weekly amount for the subscription.
     *
     * @param weeklyAmount The weekly amount to set.
     */
    public void setWeeklyAmount(double weeklyAmount) {
        this.weeklyAmount = weeklyAmount;
    }

    /**
     * Sets the total monthly amount for the subscription.
     *
     * @param monthlyamount The monthly amount to set.
     */
    public void setMonthlyamount(double monthlyamount) {
        this.monthlyamount = monthlyamount;
    }

    /**
     * Main method for testing Subscription functionality. Creates sample
     * magazines, supplements, customers, and a subscription for testing
     * purposes. Outputs the total supplement cost for the paying customer and
     * sends a monthly notification.
     *
     * @param arg The command-line arguments.
     */
    public static void main(String[] arg) {

        List<Supplement> supplementsAlone = List.of(
                new Supplement(1, "Supplement 1", 1),
                new Supplement(2, "Supplement 2", 1),
                new Supplement(3, "Supplement 3", 1)
        );

        List<Supplement> supplements1 = List.of(
                new Supplement(1, "Supplement 1", 1),
                new Supplement(2, "Supplement 2", 1),
                new Supplement(3, "Supplement 3", 1)
        );

        List<Supplement> supplements2 = List.of(
                new Supplement(1, "Supplement 1", 1),
                new Supplement(2, "Supplement 2", 1),
                new Supplement(3, "Supplement 3", 1)
        );

        List<Magazine> magazines = List.of(
                new Magazine(11, "My Magazine1", 1, supplements1),
                new Magazine(22, "My Magazine2", 1, supplements2),
                new Magazine(33, "My Magazine3", 1, supplements1),
                new Magazine(44, "My Magazine4", 1, supplements2)
        );
        List<Customer> associates = List.of(
                new Customer("john", "john@gmail.com", magazines, supplementsAlone)
        );
        PayingCustomer.PaymentMethod selectedPaymentMethod = PayingCustomer.PaymentMethod.CREDIT_CARD;
        PayingCustomer tim = new PayingCustomer(
                selectedPaymentMethod,
                "sg bank",
                associates,
                "time",
                "tim@gmail.com",
                magazines,
                supplements1
        );

        Subscription test = new Subscription(tim, "2014/02/14");

        System.out.println(test.payingCustomerSupplementCost());
        test.customer.monthlyNotification();

    }

}
