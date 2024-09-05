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
import java.util.Scanner;

/**
 * Provides logic and functionalities related to managing subscriptions and
 * databases. This class includes methods for selecting default supplements and
 * magazines.
 */
public class LogicAndDatabase {

    /**
     * Constructs a LogicAndDatabase object.
     */
    public LogicAndDatabase() {
        // Default constructor does not require any additional comments.
    }

    //public static List<Subscription> listOfCustomers; 
    private static List<Subscription> listOfCustomers = new ArrayList<>(); // A list of subscriptions.

    /**
     * Prompts the user to select default supplements.
     *
     * @return A list of selected default supplements.
     */
    public static List<Supplement> defaultSelectionSupplememnt() {
        Scanner scanner = new Scanner(System.in);
        List<Supplement> selectedSupplements = new ArrayList<>();

        boolean selecting = true;
        while (selecting) {
            System.out.println("Select a supplement from the list below:");
            System.out.println("1. defaultSelectionSupplememnt_1");
            System.out.println("2. defaultSelectionSupplememnt_2");
            System.out.println("3. defaultSelectionSupplememnt_3");
            System.out.println("4. defaultSelectionSupplememnt_4");
            System.out.println("5. defaultSelectionSupplememnt_5");
            System.out.println("6. defaultSelectionSupplememnt_6");
            System.out.println("7. defaultSelectionSupplememnt_7");
            System.out.println("8. defaultSelectionSupplememnt_8");
            System.out.println("9. defaultSelectionSupplememnt_9");
            System.out.println("10. defaultSelectionSupplememnt_10");
            System.out.print("Enter the number of the supplement you want (or '0' to stop): ");
            int supplementNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (supplementNumber) {
                case 1:
                    selectedSupplements.add(new Supplement(1, "defaultSelectionSupplememnt_1 ", 1));
                    break;
                case 2:
                    selectedSupplements.add(new Supplement(2, "defaultSelectionSupplememnt_2 ", 1));
                    break;
                case 3:
                    selectedSupplements.add(new Supplement(3, "defaultSelectionSupplememnt_3 ", 1));
                    break;
                case 4:
                    selectedSupplements.add(new Supplement(4, "defaultSelectionSupplememnt_4 ", 1));
                    break;
                case 5:
                    selectedSupplements.add(new Supplement(5, "defaultSelectionSupplememnt_5 ", 1));
                    break;
                case 6:
                    selectedSupplements.add(new Supplement(6, "defaultSelectionSupplememnt_6 ", 1));
                    break;
                case 7:
                    selectedSupplements.add(new Supplement(7, "defaultSelectionSupplememnt_7 ", 1));
                    break;
                case 8:
                    selectedSupplements.add(new Supplement(8, "defaultSelectionSupplememnt_8 ", 1));
                    break;
                case 9:
                    selectedSupplements.add(new Supplement(9, "defaultSelectionSupplememnt_9 ", 1));
                    break;
                case 10:
                    selectedSupplements.add(new Supplement(10, "defaultSelectionSupplememnt_10 ", 1));
                    break;
                case 0:
                    selecting = false;
                    break;
                default:
                    System.out.println("Invalid supplement number.");
                    break;
            }

            if (selecting) {
                System.out.print("Do you want to select another supplement? (yes/no): ");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    selecting = false;
                }
            }
        }

        return selectedSupplements;
    }

    /**
     * Prompts the user to select default magazines along with their associated
     * supplements.
     *
     * @return A list of selected default magazines.
     */
    public static List<Magazine> defaultSelectionMagazine() {
        Scanner scanner = new Scanner(System.in);
        List<Magazine> selectedMagazines = new ArrayList<>();

        boolean selecting = true;
        while (selecting) {
            System.out.println("Select a magazine from the list below:");
            System.out.println("1. defaultSelectionMagazine_1");
            System.out.println("2. defaultSelectionMagazine_2");
            System.out.println("3. defaultSelectionMagazine_3");
            System.out.println("4. defaultSelectionMagazine_4");
            System.out.println("5. defaultSelectionMagazine_5");
            System.out.println("6. defaultSelectionMagazine_6");
            System.out.println("7. defaultSelectionMagazine_7");
            System.out.println("8. defaultSelectionMagazine_8");
            System.out.println("9. defaultSelectionMagazine_9");
            System.out.println("10. defaultSelectionMagazine_10");
            System.out.print("Enter the number of the magazine you want (or '0' to stop): ");
            int magazineNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (magazineNumber) {
                case 1:
                    selectedMagazines.add(new Magazine(1, "defaultSelectionMagazine_1", 1, defaultSelectionSupplememnt()));
                    break;
                case 2:
                    selectedMagazines.add(new Magazine(2, "defaultSelectionMagazine_2", 2, defaultSelectionSupplememnt()));
                    break;
                case 3:
                    selectedMagazines.add(new Magazine(3, "defaultSelectionMagazine_3", 3, defaultSelectionSupplememnt()));
                    break;
                case 4:
                    selectedMagazines.add(new Magazine(4, "defaultSelectionMagazine_4", 4, defaultSelectionSupplememnt()));
                    break;
                case 5:
                    selectedMagazines.add(new Magazine(5, "defaultSelectionMagazine_5", 5, defaultSelectionSupplememnt()));
                    break;
                case 6:
                    selectedMagazines.add(new Magazine(6, "defaultSelectionMagazine_6", 6, defaultSelectionSupplememnt()));
                    break;
                case 7:
                    selectedMagazines.add(new Magazine(7, "defaultSelectionMagazine_7", 7, defaultSelectionSupplememnt()));
                    break;
                case 8:
                    selectedMagazines.add(new Magazine(8, "defaultSelectionMagazine_8", 8, defaultSelectionSupplememnt()));
                    break;
                case 9:
                    selectedMagazines.add(new Magazine(9, "defaultSelectionMagazine_9", 9, defaultSelectionSupplememnt()));
                    break;
                case 10:
                    selectedMagazines.add(new Magazine(10, "defaultSelectionMagazine_10", 10, defaultSelectionSupplememnt()));
                    break;
                case 0:
                    selecting = false;
                    break;
                default:
                    System.out.println("Invalid magazine number.");
                    break;
            }

            if (selecting) {
                System.out.print("Do you want to select another magazine? (yes/no): ");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    selecting = false;
                }
            }
        }

        return selectedMagazines;
    }

    //data base
    /**
     * Manages the database by creating subscriptions and populating them with
     * customers, magazines, and supplements.
     */
    public static void database() {

        // stand alone supplemenr list 
        Supplement standAlomeSupplement_1 = new Supplement(1, "stand alone supplement_1 ", 1);
        Supplement standAlomeSupplement_2 = new Supplement(2, "stand alone supplement_2 ", 1);
        Supplement standAlomeSupplement_3 = new Supplement(3, "stand alone supplement_3 ", 1);
        Supplement standAlomeSupplement_4 = new Supplement(4, "stand alone supplement_4 ", 1);

        List<Supplement> standAlomeSupplementList = new ArrayList<>();
        standAlomeSupplementList.add(standAlomeSupplement_1);
        standAlomeSupplementList.add(standAlomeSupplement_2);
        standAlomeSupplementList.add(standAlomeSupplement_3);
        standAlomeSupplementList.add(standAlomeSupplement_4);

        // 10 suppliments 
        Supplement supplement_1 = new Supplement(1, "supplement_1 ", 1);
        Supplement supplement_2 = new Supplement(2, "supplement_2 ", 1);
        Supplement supplement_3 = new Supplement(3, "supplement_3 ", 1);
        Supplement supplement_4 = new Supplement(4, "supplement_4 ", 1);
        Supplement supplement_5 = new Supplement(5, "supplement_5 ", 1);
        Supplement supplement_6 = new Supplement(6, "supplement_6 ", 1);
        Supplement supplement_7 = new Supplement(7, "supplement_7 ", 1);
        Supplement supplement_8 = new Supplement(8, "supplement_8 ", 1);
        Supplement supplement_9 = new Supplement(9, "supplement_9 ", 1);
        Supplement supplement_10 = new Supplement(10, "supplement_10 ", 1);

        // 3 suppliment list
        List<Supplement> supplementsList1 = new ArrayList<>();
        supplementsList1.add(supplement_1);
        supplementsList1.add(supplement_2);
        supplementsList1.add(supplement_3);

        List<Supplement> supplementsList2 = new ArrayList<>();
        supplementsList2.add(supplement_4);
        supplementsList2.add(supplement_5);
        supplementsList2.add(supplement_6);

        List<Supplement> supplementsList3 = new ArrayList<>();
        supplementsList3.add(supplement_7);
        supplementsList3.add(supplement_8);
        supplementsList3.add(supplement_9);
        supplementsList3.add(supplement_10);

        //System.out.println("Supplement List 1: " + supplementsList1);
        //System.out.println("Supplement List 2: " + supplementsList2);
        //System.out.println("Supplement List 3: " + supplementsList3);
        // 10 magazine
        Magazine magazine_1 = new Magazine(1, "Magazine_1", 1, supplementsList1);
        Magazine magazine_2 = new Magazine(2, "Magazine_2", 1, supplementsList2);
        Magazine magazine_3 = new Magazine(3, "Magazine_3", 1, supplementsList3);
        Magazine magazine_4 = new Magazine(4, "Magazine_4", 1, supplementsList1);
        Magazine magazine_5 = new Magazine(5, "Magazine_5", 1, supplementsList2);
        Magazine magazine_6 = new Magazine(6, "Magazine_6", 1, supplementsList3);
        Magazine magazine_7 = new Magazine(7, "Magazine_7", 1, supplementsList1);
        Magazine magazine_8 = new Magazine(8, "Magazine_8", 1, supplementsList2);
        Magazine magazine_9 = new Magazine(9, "Magazine_9", 1, supplementsList3);
        Magazine magazine_10 = new Magazine(10, "Magazine_10", 1, null);

        // 3 magazine list
        List<Magazine> magazinesList1 = new ArrayList<>();
        List<Magazine> magazinesList2 = new ArrayList<>();
        List<Magazine> magazinesList3 = new ArrayList<>();

        magazinesList1.add(magazine_1);
        magazinesList1.add(magazine_2);
        magazinesList1.add(magazine_3);

        magazinesList2.add(magazine_4);
        magazinesList2.add(magazine_5);
        magazinesList2.add(magazine_6);

        magazinesList3.add(magazine_7);
        magazinesList3.add(magazine_8);
        magazinesList3.add(magazine_9);
        magazinesList3.add(magazine_10);

        // 10 associate customer
        Customer john = new Customer("John", "john.smith@example.com", magazinesList1, standAlomeSupplementList);
        Customer alice = new Customer("Alice", "alice.jones@example.com", magazinesList1, standAlomeSupplementList);
        Customer robert = new Customer("Robert", "robert.williams@example.com", magazinesList1, standAlomeSupplementList);
        Customer emily = new Customer("Emily", "emily.brown@example.com", magazinesList1, standAlomeSupplementList);
        Customer michael = new Customer("Michael", "michael.johnson@example.com", magazinesList1, standAlomeSupplementList);
        Customer sophia = new Customer("Sophia", "sophia.miller@example.com", magazinesList1, standAlomeSupplementList);
        Customer james = new Customer("James", "james.davis@example.com", magazinesList1, standAlomeSupplementList);
        Customer olivia = new Customer("Olivia", "olivia.garcia@example.com", magazinesList1, standAlomeSupplementList);
        Customer william = new Customer("William", "william.martinez@example.com", magazinesList1, standAlomeSupplementList);
        Customer isabella = new Customer("Isabella", "isabella.anderson@example.com", magazinesList1, standAlomeSupplementList);

        // create 3 list of assocute customers 
        List<Customer> associateCustomerList1 = new ArrayList<>();
        List<Customer> associateCustomerList2 = new ArrayList<>();
        List<Customer> associateCustomerList3 = new ArrayList<>();

        associateCustomerList1.add(john);
        associateCustomerList1.add(alice);
        associateCustomerList1.add(robert);

        associateCustomerList2.add(emily);
        associateCustomerList2.add(michael);
        associateCustomerList2.add(sophia);

        associateCustomerList3.add(james);
        associateCustomerList3.add(olivia);
        associateCustomerList3.add(william);
        associateCustomerList3.add(isabella);

        // 10 paying customer
        PayingCustomer.PaymentMethod selectedPaymentMethod = PayingCustomer.PaymentMethod.CREDIT_CARD;
        PayingCustomer tim = new PayingCustomer(selectedPaymentMethod, "SG Bank", associateCustomerList2, "Tim", "tim@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer sarah = new PayingCustomer(selectedPaymentMethod, "UK Bank", associateCustomerList1, "Sarah", "sarah@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer alex = new PayingCustomer(selectedPaymentMethod, "US Bank", associateCustomerList1, "Alex", "alex@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer emma = new PayingCustomer(selectedPaymentMethod, "AU Bank", associateCustomerList3, "Emma", "emma@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer jacob = new PayingCustomer(selectedPaymentMethod, "CA Bank", associateCustomerList1, "Jacob", "jacob@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer mia = new PayingCustomer(selectedPaymentMethod, "NZ Bank", associateCustomerList2, "Mia", "mia@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer ethan = new PayingCustomer(selectedPaymentMethod, "FR Bank", associateCustomerList3, "Ethan", "ethan@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer ava = new PayingCustomer(selectedPaymentMethod, "DE Bank", associateCustomerList1, "Ava", "ava@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer williem = new PayingCustomer(selectedPaymentMethod, "IT Bank", associateCustomerList1, "Williem", "williem@gmail.com", magazinesList2, standAlomeSupplementList);
        PayingCustomer mira = new PayingCustomer(selectedPaymentMethod, "ES Bank", associateCustomerList3, "Mira", "mira@gmail.com", magazinesList2, standAlomeSupplementList);

        Subscription Tim = new Subscription(tim, "2014/02/14");
        Subscription Sarah = new Subscription(sarah, "2014/02/14");
        Subscription Alex = new Subscription(alex, "2014/02/14");
        Subscription Emma = new Subscription(emma, "2014/02/14");
        Subscription Jacob = new Subscription(jacob, "2014/02/14");
        Subscription Mia = new Subscription(mia, "2014/02/14");
        Subscription Ethan = new Subscription(ethan, "2014/02/14");
        Subscription Ava = new Subscription(ava, "2014/02/14");
        Subscription Williem = new Subscription(williem, "2014/02/14");
        Subscription Mira = new Subscription(mira, "2014/02/14");

        listOfCustomers.add(Sarah);
        listOfCustomers.add(Alex);
        listOfCustomers.add(Emma);
        listOfCustomers.add(Jacob);
        listOfCustomers.add(Mia);
        listOfCustomers.add(Ethan);
        listOfCustomers.add(Ava);
        listOfCustomers.add(Williem);
        listOfCustomers.add(Mira);
        listOfCustomers.add(Tim);

    }

    /**
     *
     * This method redirects the user to various options based on their choice.
     * The user is prompted to enter a choice between 1 and 11. Depending on the
     * choice, different actions are executed such as calling specific functions
     * or exiting the loop.
     */
    public static void redirect() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter your choice (1-11): ");
            Scanner scanner = new Scanner(System.in);
            int choice = Menu.displayMenu();

            switch (choice) {
                case 1:

                    System.out.println("Option 1 selected");
                    option1();
                    break;
                case 2:
                    System.out.println("Option 2 selected");
                    option2();
                    break;
                case 3:
                    System.out.println("Option 3 selected");
                    option3();
                    break;
                case 4:
                    System.out.println("Option 4 selected");
                    // Call function for Option 4
                    option4();
                    break;
                case 5:
                    System.out.println("Option 5 selected");
                    // Call function for Option 5
                    option5();
                    break;
                case 6:
                    System.out.println("Option 6 selected");
                    // Call function for Option 6
                    option6();
                    break;
                case 7:
                    System.out.println("Option 7 selected");
                    // Call function for Option 7
                    option7();
                    break;
                case 8:
                    System.out.println("Option 8 selected");
                    // Call function for Option 8
                    option8();
                    break;
                case 9:
                    System.out.println("Option 9 selected");
                    // Call function for Option 9
                    option9();
                    break;
                case 10:
                    System.out.println("Option 10 selected");
                    // Call function for Option 10
                    option10();
                    break;
                case 11:
                    System.out.println("Option 11 selected");

                    option11();
                    break;
                case 12:
                    System.out.println("Option 12 selected");
                    option12();
                    break;
                case 13:
                    System.out.println("Option 13 selected");
                    option13();
                    break;
                case 14:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     *
     * Displays all the paying customers along with their associated customers'
     * names and emails.
     */
    public static void option1() {
        for (Subscription subscription : listOfCustomers) {
            PayingCustomer customer = subscription.customer;
            System.out.println(subscription.getMonthlyamount());
            if (customer != null) {
                System.out.println("Paying Customer Name: " + customer.getName());
                System.out.println("Paying Customer Email: " + customer.getEmail());
                System.out.println();

                List<Customer> associatedCustomers = customer.getAssociateCustomer();
                if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                    System.out.println("Associated Customers:");
                    for (Customer associateCustomer : associatedCustomers) {
                        System.out.println("   - Name: " + associateCustomer.getName());
                        System.out.println("     Email: " + associateCustomer.getEmail());
                    }
                } else {
                    System.out.println("No associated customers.");
                }
                System.out.println();
            }
        }
    }

    /**
     *
     * Allows the user to add a new paying customer. The user is prompted to
     * enter details such as name, email, payment method, bank, magazines,
     * supplements, and associated customers.
     */
    public static void option2() {

        Scanner scanner = new Scanner(System.in);
        Subscription newEntry = new Subscription(createPayingCustomer(scanner), "2014/02/14");
        listOfCustomers.add(newEntry);

    }

    /**
     * Creates a new paying customer with the provided details.
     *
     * @param scanner The scanner object to read user input
     * @return The newly created paying customer
     */
    private static PayingCustomer createPayingCustomer(Scanner scanner) {
        System.out.println("Enter details for the paying customer:");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Get the payment method
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Bank Card");
        int paymentMethodChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        PayingCustomer.PaymentMethod paymentMethod = null;
        switch (paymentMethodChoice) {
            case 1:
                paymentMethod = PayingCustomer.PaymentMethod.CREDIT_CARD;
                break;
            case 2:
                paymentMethod = PayingCustomer.PaymentMethod.BANK_CARD;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Credit Card.");
                paymentMethod = PayingCustomer.PaymentMethod.CREDIT_CARD;
                break;
        }

        System.out.print("Bank: ");
        String bank = scanner.nextLine();

        // Get the list of magazines
        List<Magazine> magazinesList = getMagazinesList(scanner);

        // Get the list of supplements
        List<Supplement> supplementsList = getSupplementsList(scanner);

        // Get the list of associate customers
        List<Customer> associateCustomersList = getAssociateCustomersList(scanner);

        // Create and return the paying customer object
        return new PayingCustomer(paymentMethod, bank, associateCustomersList, name, email, magazinesList, supplementsList);
    }

    /**
     * Gets the list of associate customers along with their details.
     *
     * @param scanner The scanner object to read user input
     * @return The list of associate customers
     */
    private static List<Customer> getAssociateCustomersList(Scanner scanner) {
        List<Customer> associateCustomersList = new ArrayList<>();

        boolean addAnotherCustomer = true;
        while (addAnotherCustomer) {
            System.out.println("Enter details for an associate customer:");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Get the list of magazines for this associate customer
            List<Magazine> magazinesList = getMagazinesList(scanner);

            // Get the list of supplements for this associate customer
            List<Supplement> supplementsList = getSupplementsList(scanner);

            // Create a new Customer object with the provided details
            Customer associateCustomer = new Customer(name, email, magazinesList, supplementsList);

            // Add the new customer to the associate customers list
            associateCustomersList.add(associateCustomer);

            // Ask if the user wants to add another associate customer
            System.out.print("Do you want to add another associate customer? (yes/no): ");
            String input = scanner.nextLine();
            addAnotherCustomer = input.equalsIgnoreCase("yes");
        }

        return associateCustomersList;
    }

    /**
     * Gets the list of magazines along with their details.
     *
     * @param scanner The scanner object to read user input
     * @return The list of magazines
     */
    private static List<Magazine> getMagazinesList(Scanner scanner) {
        System.out.print("Do you want to add a magazine for this customer? (yes/no): ");
        String addMagazineChoice = scanner.nextLine();

        if (addMagazineChoice.equalsIgnoreCase("no")) {
            return null; // Return null if the user chooses not to add a magazine
        }

        List<Magazine> magazinesList = new ArrayList<>();

        System.out.print("Do you want to use default magazine selections? (yes/no): ");
        String useDefault = scanner.nextLine();

        if (useDefault.equalsIgnoreCase("yes")) {
            return defaultSelectionMagazine();
        } else {
            boolean addAnotherMagazine = true;
            while (addAnotherMagazine) {
                Magazine magazine = getMagazineDetails(scanner);
                magazinesList.add(magazine);

                // Ask if the user wants to add another magazine
                System.out.print("Do you want to add another magazine? (yes/no): ");
                String input = scanner.nextLine();
                addAnotherMagazine = input.equalsIgnoreCase("yes");
            }
        }

        return magazinesList;
    }

    /**
     * Gets details for a magazine.
     *
     * @param scanner The scanner object to read user input
     * @return The magazine object with provided details
     */
    private static Magazine getMagazineDetails(Scanner scanner) {
        System.out.println("Enter details for the magazine:");

        System.out.print("Magazine ID: ");
        int magazineId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        System.out.print("Magazine Name: ");
        String magazineName = scanner.nextLine();

        System.out.print("Magazine Cost: ");
        float magazineCost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline left-over

        // Now get the list of supplements for this magazine
        List<Supplement> supplementsList = getSupplementsList(scanner);

        // Create and return the magazine object
        return new Magazine(magazineId, magazineName, magazineCost, supplementsList);
    }

    /**
     * Gets the list of supplements along with their details.
     *
     * @param scanner The scanner object to read user input
     * @return The list of supplements
     */
    private static List<Supplement> getSupplementsList(Scanner scanner) {
        System.out.print("Do you want to add a supplement for this customer? (yes/no): ");
        String addSupplementChoice = scanner.nextLine();

        if (addSupplementChoice.equalsIgnoreCase("no")) {
            return null; // Return null if the user chooses not to add a supplement
        }

        List<Supplement> supplementsList = new ArrayList<>();

        System.out.print("Do you want to use default supplement selections? (yes/no): ");
        String useDefault = scanner.nextLine();

        if (useDefault.equalsIgnoreCase("yes")) {
            return defaultSelectionSupplememnt();
        } else {
            System.out.println("Enter details for each supplement (press Enter to stop):");

            boolean flag = true;
            while (flag) {
                Supplement supplement = getSupplementDetails(scanner);
                supplementsList.add(supplement);

                System.out.print("Add another supplement? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("yes")) {
                    flag = false;
                }
            }
        }

        return supplementsList;
    }

    /**
     * Gets details for a supplement.
     *
     * @param scanner The scanner object to read user input
     * @return The supplement object with provided details
     */
    private static Supplement getSupplementDetails(Scanner scanner) {
        System.out.println("Enter supplement details:");

        System.out.print("ID: ");
        int supplementId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Name: ");
        String supplementName = scanner.nextLine();

        System.out.print("Cost: ");
        float supplementCost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        return new Supplement(supplementId, supplementName, supplementCost);
    }

    /**
     * Removes a paying customer or an associated customer of a paying customer.
     *
     */
    public static void option3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to remove:");
        System.out.println("1. Paying Customer");
        System.out.println("2. Associate Customer of Paying Customer");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                removePayingCustomer(scanner);
                break;
            case 2:
                removeAssociateCustomerOfPayingCustomer(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Removes a paying customer.
     *
     * @param scanner The scanner object to read user input
     */
    private static void removePayingCustomer(Scanner scanner) {
        System.out.println("List of Paying Customers:");
        int index = 1;
        for (Subscription subscription : listOfCustomers) {
            if (subscription.getCustomer() != null) {
                System.out.println(index + ". " + subscription.getCustomer().getName());
                index++;
            }
        }

        System.out.print("Select the Paying Customer to remove: ");
        int customerIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        if (customerIndex >= 1 && customerIndex <= listOfCustomers.size()) {
            listOfCustomers.remove(customerIndex - 1);
            System.out.println("Paying Customer removed successfully.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * Removes an associated customer of a paying customer.
     *
     * @param scanner The scanner object to read user input
     */
    private static void removeAssociateCustomerOfPayingCustomer(Scanner scanner) {
        System.out.println("Select the Paying Customer:");
        int payingCustomerIndex = selectPayingCustomer(scanner);

        if (payingCustomerIndex >= 0 && payingCustomerIndex < listOfCustomers.size()) {
            PayingCustomer payingCustomer = listOfCustomers.get(payingCustomerIndex).getCustomer();
            List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();

            if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                System.out.println("List of Associated Customers for " + payingCustomer.getName() + ":");
                int index = 1;
                for (Customer associateCustomer : associatedCustomers) {
                    System.out.println(index + ". " + associateCustomer.getName());
                    index++;
                }

                System.out.print("Select the Associated Customer to remove: ");
                int associateCustomerIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                if (associateCustomerIndex >= 1 && associateCustomerIndex <= associatedCustomers.size()) {
                    associatedCustomers.remove(associateCustomerIndex - 1);
                    System.out.println("Associated Customer removed successfully.");
                } else {
                    System.out.println("Invalid selection.");
                }
            } else {
                System.out.println("No associated customers found for " + payingCustomer.getName() + ".");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * Selects a paying customer based on user input.
     *
     * @param scanner The scanner object to read user input
     * @return The index of the selected paying customer
     */
    private static int selectPayingCustomer(Scanner scanner) {
        System.out.println("List of Paying Customers:");
        int index = 1;
        for (Subscription subscription : listOfCustomers) {
            if (subscription.getCustomer() != null) {
                System.out.println(index + ". " + subscription.getCustomer().getName());
                index++;
            }
        }

        System.out.print("Select the Paying Customer: ");
        return scanner.nextInt() - 1;
    }

    /**
     * This method allows a paying customer to add an associate customer to
     * their subscription. It prompts the user to select a paying customer from
     * the list of paying customers, then prompts for details of the associate
     * customer to be added. Once the details are provided, the associate
     * customer is added to the selected paying customer's subscription.
     */
    public static void option4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of Paying Customers:");
        int index = 1;
        for (Subscription subscription : listOfCustomers) {
            if (subscription.getCustomer() != null) {
                System.out.println(index + ". " + subscription.getCustomer().getName());
                index++;
            }
        }

        System.out.print("Select the Paying Customer: ");
        int payingCustomerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline left-over

        if (payingCustomerIndex >= 0 && payingCustomerIndex < listOfCustomers.size()) {
            PayingCustomer payingCustomer = listOfCustomers.get(payingCustomerIndex).getCustomer();

            // Create a new associate customer
            Customer associateCustomer = createAssociateCustomer(scanner);

            // Add the associate customer to the selected paying customer
            payingCustomer.addAssociateCustomer(associateCustomer);

            System.out.println("Associate customer added successfully to " + payingCustomer.getName());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static Customer createAssociateCustomer(Scanner scanner) {
        System.out.println("Enter details for the associate customer:");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Get the list of magazines for the associate customer
        List<Magazine> magazinesList = getMagazinesList(scanner);

        // Get the list of supplements for the associate customer
        List<Supplement> supplementsList = getSupplementsList(scanner);

        // Create and return the associate customer object
        return new Customer(name, email, magazinesList, supplementsList);
    }

    /**
     * This method allows a paying customer to remove an associate customer from
     * their subscription. It prompts the user to select a paying customer from
     * the list of paying customers, then lists out the associated customers for
     * the selected paying customer. The user can then select an associated
     * customer to remove from the subscription.
     */
    public static void option5() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the Paying Customer:");
        int payingCustomerIndex = selectPayingCustomer(scanner);

        if (payingCustomerIndex >= 0 && payingCustomerIndex < listOfCustomers.size()) {
            PayingCustomer payingCustomer = listOfCustomers.get(payingCustomerIndex).getCustomer();
            List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();

            if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                System.out.println("List of Associated Customers for " + payingCustomer.getName() + ":");
                int index = 1;
                for (Customer associateCustomer : associatedCustomers) {
                    System.out.println(index + ". " + associateCustomer.getName());
                    index++;
                }

                System.out.print("Select the Associated Customer to remove: ");
                int associateCustomerIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                if (associateCustomerIndex >= 1 && associateCustomerIndex <= associatedCustomers.size()) {
                    associatedCustomers.remove(associateCustomerIndex - 1);
                    System.out.println("Associated Customer removed successfully.");
                } else {
                    System.out.println("Invalid selection.");
                }
            } else {
                System.out.println("No associated customers found for " + payingCustomer.getName() + ".");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * This method allows a user to add a magazine to a customer's subscription.
     * It prompts the user to select a customer, then choose whether to add the
     * magazine to the paying customer's subscription or one of their associate
     * customers' subscriptions. Depending on the selection, the user is
     * prompted to select the specific customer and then either choose a
     * magazine from their list of magazines or input details for a new
     * magazine.
     */
    public static void option6() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the Customer:");
        int customerIndex = selectCustomer(scanner);

        if (customerIndex >= 0 && customerIndex < listOfCustomers.size()) {
            Subscription subscription = listOfCustomers.get(customerIndex);
            PayingCustomer payingCustomer = subscription.getCustomer();
            List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();

            System.out.println("Do you want to add a magazine for:");
            System.out.println("1. Paying Customer");
            System.out.println("2. Associate Customer");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    addMagazine(payingCustomer, scanner);
                    break;
                case 2:
                    if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                        System.out.println("Select the Associate Customer:");
                        int associateCustomerIndex = selectAssociateCustomer(scanner, associatedCustomers);

                        if (associateCustomerIndex >= 0 && associateCustomerIndex < associatedCustomers.size()) {
                            Customer associateCustomer = associatedCustomers.get(associateCustomerIndex);
                            addMagazine(associateCustomer, scanner);
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    } else {
                        System.out.println("No associated customers found.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static int selectCustomer(Scanner scanner) {
        System.out.println("List of Customers:");
        int index = 1;
        for (Subscription subscription : listOfCustomers) {
            PayingCustomer payingCustomer = subscription.getCustomer();
            if (payingCustomer != null) {
                System.out.println(index + ". Paying Customer: " + payingCustomer.getName());
                List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();
                if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                    for (Customer associateCustomer : associatedCustomers) {
                        System.out.println("   - Associate Customer: " + associateCustomer.getName());
                    }
                }
                index++;
            }
        }
        System.out.print("Select the Customer: ");
        return scanner.nextInt() - 1;
    }

    private static int selectAssociateCustomer(Scanner scanner, List<Customer> associatedCustomers) {
        int index = 1;
        for (Customer associateCustomer : associatedCustomers) {
            System.out.println(index + ". " + associateCustomer.getName());
            index++;
        }
        System.out.print("Select the Associate Customer: ");
        return scanner.nextInt() - 1;
    }

    private static void addMagazine(Customer customer, Scanner scanner) {
        System.out.println("Do you want to use the default selection of magazines? (yes/no): ");
        String choice = scanner.nextLine().trim();
        if (choice.equalsIgnoreCase("yes")) {
            List<Magazine> selectedMagazines = defaultSelectionMagazine();
            customer.getMagazines().addAll(selectedMagazines);
            System.out.println("Magazines added successfully to " + customer.getName() + ".");
        } else if (choice.equalsIgnoreCase("no")) {
            // Input magazine details manually
            Magazine magazine = getMagazineDetails(scanner);
            customer.getMagazines().add(magazine);
            System.out.println("Magazine added successfully to " + customer.getName() + ".");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * This method allows a user to remove a magazine from a customer's
     * subscription. It prompts the user to select a customer, then choose
     * whether to remove the magazine from the paying customer's subscription or
     * one of their associate customers' subscriptions. Depending on the
     * selection, the user is prompted to select the specific customer and then
     * choose a magazine from their list of magazines to remove.
     */
    public static void option7() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the Customer:");
        int customerIndex = selectCustomer(scanner);

        if (customerIndex >= 0 && customerIndex < listOfCustomers.size()) {
            Subscription subscription = listOfCustomers.get(customerIndex);
            PayingCustomer payingCustomer = subscription.getCustomer();
            List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();

            System.out.println("Do you want to remove a magazine for:");
            System.out.println("1. Paying Customer");
            System.out.println("2. Associate Customer");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    removeMagazine(payingCustomer, scanner);
                    break;
                case 2:
                    if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                        System.out.println("Select the Associate Customer:");
                        int associateCustomerIndex = selectAssociateCustomer(scanner, associatedCustomers);

                        if (associateCustomerIndex >= 0 && associateCustomerIndex < associatedCustomers.size()) {
                            Customer associateCustomer = associatedCustomers.get(associateCustomerIndex);
                            removeMagazine(associateCustomer, scanner);
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    } else {
                        System.out.println("No associated customers found.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void removeMagazine(Customer customer, Scanner scanner) {
        List<Magazine> magazinesList = customer.getMagazines();

        if (magazinesList != null && !magazinesList.isEmpty()) {
            System.out.println("Select the Magazine to remove:");
            for (int i = 0; i < magazinesList.size(); i++) {
                System.out.println((i + 1) + ". " + magazinesList.get(i).getMagazineName());
            }
            int magazineIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (magazineIndex >= 1 && magazineIndex <= magazinesList.size()) {
                Magazine removedMagazine = magazinesList.remove(magazineIndex - 1);
                System.out.println("Magazine '" + removedMagazine.getMagazineName() + "' removed successfully.");
            } else {
                System.out.println("Invalid selection.");
            }
        } else {
            System.out.println("No magazines found for this customer.");
        }
    }

    /**
     * This method allows a user to add a supplement to a customer's
     * subscription. It prompts the user to select a customer, then choose
     * whether to add the supplement to the paying customer's subscription or
     * one of their associate customers' subscriptions. Depending on the
     * selection, the user is prompted to select the specific customer and then
     * choose whether to add the supplement inside a magazine or as a standalone
     * supplement.
     */
    public static void option8() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the Customer:");
        int customerIndex = selectCustomer(scanner);

        if (customerIndex >= 0 && customerIndex < listOfCustomers.size()) {
            Subscription subscription = listOfCustomers.get(customerIndex);
            PayingCustomer payingCustomer = subscription.getCustomer();
            List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();

            System.out.println("Do you want to add a supplement for:");
            System.out.println("1. Paying Customer");
            System.out.println("2. Associate Customer");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    addSupplementToCustomer(payingCustomer, scanner);
                    break;
                case 2:
                    if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                        System.out.println("Select the Associate Customer:");
                        int associateCustomerIndex = selectAssociateCustomer(scanner, associatedCustomers);

                        if (associateCustomerIndex >= 0 && associateCustomerIndex < associatedCustomers.size()) {
                            Customer associateCustomer = associatedCustomers.get(associateCustomerIndex);
                            addSupplementToCustomer(associateCustomer, scanner);
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    } else {
                        System.out.println("No associated customers found.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void addSupplementToCustomer(Customer customer, Scanner scanner) {
        System.out.println("Do you want to add the supplement:");
        System.out.println("1. Inside a Magazine");
        System.out.println("2. Standalone");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                addSupplementToMagazine(customer, scanner);
                break;
            case 2:
                addStandaloneSupplement(customer, scanner);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void addSupplementToMagazine(Customer customer, Scanner scanner) {
        // Display the list of magazines for the customer to choose from
        List<Magazine> magazinesList = customer.getMagazines();
        if (magazinesList != null && !magazinesList.isEmpty()) {
            System.out.println("Select the Magazine:");
            for (int i = 0; i < magazinesList.size(); i++) {
                System.out.println((i + 1) + ". " + magazinesList.get(i).getMagazineName());
            }
            int magazineIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (magazineIndex >= 1 && magazineIndex <= magazinesList.size()) {
                Magazine selectedMagazine = magazinesList.get(magazineIndex - 1);

                // Ask the user if they want to add the default prepopulated supplements
                System.out.print("Do you want to add default prepopulated supplements? (yes/no): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    List<Supplement> defaultSupplements = defaultSelectionSupplememnt();
                    selectedMagazine.getMagazineSupplement().addAll(defaultSupplements);
                    System.out.println("The magazine after adding the supplement is: " + selectedMagazine.getMagazineName());///testt

                    System.out.println("Default supplements added successfully to " + selectedMagazine.getMagazineName() + ".");
                } else {
                    // Get supplement details
                    Supplement supplement = getSupplementDetails(scanner);

                    // Add supplement to the selected magazine
                    selectedMagazine.addSupplement(supplement);

                    System.out.println("Supplement added successfully to " + selectedMagazine.getMagazineName() + ".");
                }
            } else {
                System.out.println("Invalid magazine selection.");
            }
        } else {
            System.out.println("No magazines found for this customer.");
        }
    }

    private static void addStandaloneSupplement(Customer customer, Scanner scanner) {
        // Ask the user if they want to add the default prepopulated supplements
        System.out.print("Do you want to add default prepopulated supplements? (yes/no): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            List<Supplement> defaultSupplements = defaultSelectionSupplememnt();
            customer.getSupplement().addAll(defaultSupplements);
            System.out.println("Default supplements added successfully to " + customer.getName() + ".");
        } else {
            // Get supplement details
            Supplement supplement = getSupplementDetails(scanner);

            // Add supplement to customer
            customer.getSupplement().add(supplement);

            System.out.println("Supplement added successfully to " + customer.getName() + ".");
        }
    }

    /**
     * This method allows a user to remove a supplement from a customer's
     * subscription. It prompts the user to select a customer, then choose
     * whether to remove the supplement from the paying customer's subscription
     * or one of their associate customers' subscriptions. Depending on the
     * selection, the user is prompted to select the specific customer and then
     * choose whether to remove a standalone supplement or a supplement from
     * within a magazine.
     */
    public static void option9() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the Customer:");
        int customerIndex = selectCustomer(scanner);

        if (customerIndex >= 0 && customerIndex < listOfCustomers.size()) {
            Subscription subscription = listOfCustomers.get(customerIndex);
            PayingCustomer payingCustomer = subscription.getCustomer();
            List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();

            System.out.println("Do you want to remove a supplement for:");
            System.out.println("1. Paying Customer");
            System.out.println("2. Associate Customer");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    removeSupplementOption(payingCustomer, scanner);
                    break;
                case 2:
                    if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                        System.out.println("Select the Associate Customer:");
                        int associateCustomerIndex = selectAssociateCustomer(scanner, associatedCustomers);

                        if (associateCustomerIndex >= 0 && associateCustomerIndex < associatedCustomers.size()) {
                            Customer associateCustomer = associatedCustomers.get(associateCustomerIndex);
                            removeSupplementOption(associateCustomer, scanner);
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    } else {
                        System.out.println("No associated customers found.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void removeSupplementOption(Customer customer, Scanner scanner) {
        System.out.println("Do you want to remove a supplement for:");
        System.out.println("1. Standalone Supplements");
        System.out.println("2. Supplements Inside Magazines");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                removeStandaloneSupplement(customer, scanner);
                break;
            case 2:
                removeSupplementFromMagazine(customer, scanner);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void removeStandaloneSupplement(Customer customer, Scanner scanner) {
        List<Supplement> supplementsList = customer.getSupplement();

        if (supplementsList != null && !supplementsList.isEmpty()) {
            System.out.println("Standalone Supplements:");
            for (int i = 0; i < supplementsList.size(); i++) {
                System.out.println((i + 1) + ". " + supplementsList.get(i).getSupplementName());
            }

            System.out.print("Select the Standalone Supplement to remove: ");
            int supplementIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (supplementIndex >= 1 && supplementIndex <= supplementsList.size()) {
                Supplement removedSupplement = supplementsList.remove(supplementIndex - 1);
                System.out.println("Standalone Supplement '" + removedSupplement.getSupplementName() + "' removed successfully.");
            } else {
                System.out.println("Invalid supplement selection.");
            }
        } else {
            System.out.println("No standalone supplements found for this customer.");
        }
    }

    private static void removeSupplementFromMagazine(Customer customer, Scanner scanner) {
        List<Magazine> magazinesList = customer.getMagazines();
        int cumulativeIndex = 1;

        if (magazinesList != null && !magazinesList.isEmpty()) {
            System.out.println("Magazines and Supplements:");
            for (Magazine magazine : magazinesList) {
                List<Supplement> magazineSupplements = magazine.getMagazineSupplement();
                if (magazineSupplements != null && !magazineSupplements.isEmpty()) {
                    System.out.println("Magazine: " + magazine.getMagazineName());
                    for (Supplement supplement : magazineSupplements) {
                        System.out.println((cumulativeIndex++) + ". " + supplement.getSupplementName());
                    }
                } else {
                    System.out.println("  No supplements found for this magazine.");
                }
            }

            System.out.print("Select the Supplement to remove from magazines: ");
            int supplementIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (supplementIndex >= 1 && supplementIndex <= cumulativeIndex) {
                // Find the magazine containing the selected supplement
                cumulativeIndex = 1;
                for (Magazine magazine : magazinesList) {
                    List<Supplement> magazineSupplements = magazine.getMagazineSupplement();
                    if (magazineSupplements != null && supplementIndex >= cumulativeIndex && supplementIndex < cumulativeIndex + magazineSupplements.size()) {
                        // Remove supplement from the magazine
                        Supplement removedSupplement = magazineSupplements.remove(supplementIndex - cumulativeIndex);
                        System.out.println("Supplement '" + removedSupplement.getSupplementName() + "' removed successfully from magazine '" + magazine.getMagazineName() + "'.");
                        return;
                    }
                    cumulativeIndex += magazineSupplements.size();
                }
            }
        } else {
            System.out.println("No magazines found for this customer.");
        }

        System.out.println("Invalid supplement selection.");
    }

    /**
     * This method displays the weekly notifications for a selected customer. It
     * prompts the user to select a customer (either a paying customer or one of
     * their associates), then displays the weekly notifications for that
     * customer for four weeks.
     */
    public static void option10() {
        Scanner scanner = new Scanner(System.in);

        // List out paying customers and their associate customers
        System.out.println("List of Paying Customers:");
        int index = 1;
        for (Subscription subscription : listOfCustomers) {
            PayingCustomer payingCustomer = subscription.getCustomer();
            if (payingCustomer != null) {
                System.out.println(index + ". Paying Customer: " + payingCustomer.getName());
                List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();
                if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                    for (int i = 0; i < associatedCustomers.size(); i++) {
                        System.out.println("   " + (index + i + 1) + ". Associate Customer: " + associatedCustomers.get(i).getName());
                    }
                }
                index += associatedCustomers.size() + 1;
            }
        }

        // Select a paying customer
        System.out.print("Select the Customer: ");
        int customerIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        int currentIndex = 1;
        boolean found = false;
        for (Subscription subscription : listOfCustomers) {
            PayingCustomer payingCustomer = subscription.getCustomer();
            if (payingCustomer != null) {
                if (currentIndex == customerIndex) {
                    // Display weekly notifications for the selected paying customer

                    payingCustomer.weeklyNotification();
                    found = true;
                    break;
                }
                List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();
                if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                    if (currentIndex + associatedCustomers.size() >= customerIndex) {
                        // Display weekly notifications for the selected associate customer
                        associatedCustomers.get(customerIndex - currentIndex - 1).weeklyNotification();
                        found = true;
                        break;
                    }
                    currentIndex += associatedCustomers.size() + 1;
                } else {
                    currentIndex++;
                }
            }
        }

        if (!found) {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * This method displays the monthly notifications for a selected paying
     * customer. It prompts the user to select a paying customer, then displays
     * the monthly notifications for that customer.
     */
    public static void option11() {
        Scanner scanner = new Scanner(System.in);

        // List out paying customers
        System.out.println("List of Paying Customers:");
        int index = 1;
        for (Subscription subscription : listOfCustomers) {
            PayingCustomer payingCustomer = subscription.getCustomer();
            if (payingCustomer != null) {
                System.out.println(index + ". Paying Customer: " + payingCustomer.getName());
                index++;
            }
        }

        // Select a paying customer
        System.out.print("Select the Paying Customer: ");
        int customerIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        int currentIndex = 1;
        boolean found = false;
        for (Subscription subscription : listOfCustomers) {
            PayingCustomer payingCustomer = subscription.getCustomer();
            if (payingCustomer != null) {
                if (currentIndex == customerIndex) {
                    // Display monthly notification for the selected paying customer
                    payingCustomer.monthlyNotification();
                    found = true;
                    break;
                }
                currentIndex++;
            }
        }

        if (!found) {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * This method prints out the text of all the emails for all customers for
     * four weeks of magazines. It iterates through all paying customers and
     * their associate customers, displaying the weekly notifications for each
     * of them for four weeks.
     */
    public static void option12() {
        // List out paying customers and their associate customers
        System.out.println("Weekly Notifications for All Customers:");

        for (Subscription subscription : listOfCustomers) {
            PayingCustomer payingCustomer = subscription.getCustomer();
            if (payingCustomer != null) {
                // Display weekly notification for the paying customer
                System.out.println("Paying Customer: " + payingCustomer.getName());
                for (int week = 1; week <= 4; week++) {
                    System.out.println("Week " + week + ":");
                    payingCustomer.weeklyNotification();
                }

                // Display weekly notifications for associated customers
                List<Customer> associatedCustomers = payingCustomer.getAssociateCustomer();
                if (associatedCustomers != null && !associatedCustomers.isEmpty()) {
                    for (Customer associatedCustomer : associatedCustomers) {
                        System.out.println("  Associate Customer: " + associatedCustomer.getName());
                        for (int week = 1; week <= 4; week++) {
                            System.out.println("Week " + week + ":");
                            associatedCustomer.weeklyNotification();
                        }
                    }
                }
            }
        }
    }

    /**
     * This method displays the monthly notifications for all paying customers.
     * It iterates through all paying customers and displays the monthly
     * notifications for each of them.
     */
    public static void option13() {
        // List out paying customers
        System.out.println("Monthly Notifications for All Paying Customers:");

        boolean found = false;
        for (Subscription subscription : listOfCustomers) {
            PayingCustomer payingCustomer = subscription.getCustomer();
            if (payingCustomer != null) {
                // Display monthly notification for the paying customer
                payingCustomer.monthlyNotification();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No paying customers found.");
        }
    }

    /**
     * Main method to start the program.
     *
     * @param arg The command-line arguments passed to the program.
     */
    public static void main(String[] arg) {
        database();

        redirect();

    }
}
