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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

/**
 * The main application class that extends javafx.application.Application. This
 * class handles the GUI for the magazine service application.
 */
public class App extends Application {

    /**
     * The database instance managing all data for the application.
     */
    static Database database = new Database();
    /**
     * The ListView displaying the list of supplements.
     */
    private ListView<Supplement> supplementsList;
    /**
     * The ListView displaying the list of customers.
     */
    private ListView<Customer> customersList;
    /**
     * The ListView displaying the list of magazines.
     */
    private ListView<Magazine> magazinesList;
    /**
     * The VBox representing the information panel in the GUI.
     */
    private VBox infoPanel;
    /**
     * The file name for data serialization.
     */
    private static final String DATA_FILE = "data.ser";
    /**
     * The primary stage for the JavaFX application.
     */
    private Stage primaryStage;

    /**
     * The start method of the JavaFX application.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        database = loadDataFromFile();
        //database.populate();

        // Top title
        Label titleLabel = new Label("MAGAZINE SERVICE");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #333; -fx-font-weight: bold;");

        // Top button bar
        Button viewButton = new Button("View");
        Button createButton = new Button("Create");
        Button editButton = new Button("Edit");
        HBox buttonBar = new HBox(10, viewButton, createButton, editButton);
        buttonBar.setAlignment(Pos.CENTER);
        VBox topBar = new VBox(5, titleLabel, buttonBar);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(10));

        // Center view with ListViews and Information Panel
        supplementsList = new ListView<>();
        supplementsList.getItems().addAll(database.getSupplements());
        supplementsList.setCellFactory(lv -> new ListCell<Supplement>() {
            @Override
            protected void updateItem(Supplement item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getSupplementName());
            }
        });
        Label supplementsLabel = new Label("List of Supplements");
        VBox supplementsPanel = new VBox(10, supplementsLabel, supplementsList);
        supplementsPanel.setPadding(new Insets(10));

        customersList = new ListView<>();
        customersList.getItems().addAll(database.getPayingCustomers());
        customersList.getItems().addAll(database.getAssociateCustomers());
        customersList.setCellFactory(lv -> new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });
        Label customersLabel = new Label("List of Customers");
        VBox customersPanel = new VBox(10, customersLabel, customersList);
        customersPanel.setPadding(new Insets(10));

        magazinesList = new ListView<>();
        magazinesList.getItems().addAll(database.getMagazines());
        magazinesList.setCellFactory(lv -> new ListCell<Magazine>() {
            @Override
            protected void updateItem(Magazine item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getMagazineName());
            }
        });
        Label magazinesLabel = new Label("List of Magazines");
        VBox magazinePanel = new VBox(10, magazinesLabel, magazinesList);
        magazinePanel.setPadding(new Insets(10));

        // Information Panel with ScrollPane
        infoPanel = new VBox(10, new Label("Information Panel:"));
        infoPanel.setPadding(new Insets(10));
        infoPanel.setMinWidth(200);
        ScrollPane scrollPane = new ScrollPane(infoPanel);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Never show the horizontal scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Only show the vertical scrollbar when needed

        // Maian content layout
        HBox mainContentHBox = new HBox(0, magazinePanel, new Separator(Orientation.VERTICAL), supplementsPanel, customersPanel, new Separator(Orientation.VERTICAL), scrollPane);
        mainContentHBox.setAlignment(Pos.TOP_CENTER);

        // Bottom panels (hidden initially)
        VBox createModePanel = createModePanel(primaryStage);
        VBox editModePanel = editModePanel();
        createModePanel.setVisible(false);
        editModePanel.setVisible(false);

        // GridPane layout setup
        GridPane mainLayout = new GridPane();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.add(topBar, 0, 0);
        mainLayout.add(mainContentHBox, 0, 1);

        mainLayout.add(createModePanel, 0, 1); // Add the createModePanel to the GridPane
        mainLayout.add(editModePanel, 0, 1); // Add the editModePanel to the GridPane

        // Button action handlers
        viewButton.setOnAction(e -> {
            mainContentHBox.setVisible(true);
            createModePanel.setVisible(false);
            editModePanel.setVisible(false);
        });
        createButton.setOnAction(e -> {
            mainContentHBox.setVisible(false);
            createModePanel.setVisible(true);
            editModePanel.setVisible(false);
        });
        editButton.setOnAction(e -> {
            mainContentHBox.setVisible(false);
            createModePanel.setVisible(false);
            editModePanel.setVisible(true);
        });

        // Scene and stage setup
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setTitle("Magazine Service");
        primaryStage.setScene(scene);
        primaryStage.show();

        // ListView selection handling
        supplementsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoPanel.getChildren().clear();

                // Display the selected supplement's information
                Node supplementInfo = newValue.displayGUI();
                infoPanel.getChildren().add(supplementInfo);

                // Add a separator and a label for customer subscriptions
                infoPanel.getChildren().add(new Separator());
                infoPanel.getChildren().add(new Label("Customers subscribed to this supplement:"));

                // Iterate over all customers to find subscriptions to the selected supplement
                boolean found = false;
                for (Customer customer : database.getPayingCustomers()) {
                    if (customer.getSupplements() != null && customer.getSupplements().contains(newValue)) {
                        Label subscribedCustomerLabel = new Label(customer.getName());
                        infoPanel.getChildren().add(subscribedCustomerLabel);
                        found = true;
                    }
                }

                // Do the same for associate customers
                for (Customer customer : database.getAssociateCustomers()) {
                    if (customer.getSupplements() != null && customer.getSupplements().contains(newValue)) {
                        Label subscribedCustomerLabel = new Label(customer.getName());
                        infoPanel.getChildren().add(subscribedCustomerLabel);
                        found = true;
                    }
                }

                // If no customers are found, display a message indicating that
                if (!found) {
                    infoPanel.getChildren().add(new Label("No customers are subscribed to this supplement."));
                }
            }
        });

        customersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedCustomer) -> {
            if (selectedCustomer != null) {
                infoPanel.getChildren().clear();
                Node customerInfo = selectedCustomer.displayGUI();
                infoPanel.getChildren().add(customerInfo);

                if (selectedCustomer instanceof PayingCustomer) { // Check if the customer is a paying customer
                    Button displayBillingHistoryBtn = new Button("Display Billing History");
                    displayBillingHistoryBtn.setOnAction(event -> displayBillingHistory(selectedCustomer));
                    infoPanel.getChildren().add(displayBillingHistoryBtn);
                }
            }
        });

        // ListView selection handling for magazines
        magazinesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoPanel.getChildren().clear();

                // Display the selected magazine's information
                Node magazineInfo = newValue.displayGUI(); // Assuming Magazine class has a displayGUI() method
                infoPanel.getChildren().add(magazineInfo);

                // Add a separator and a label for customers subscribed to this magazine
                infoPanel.getChildren().add(new Separator());
                infoPanel.getChildren().add(new Label("Customers subscribed to this magazine:"));

                // Iterate over all customers to find subscriptions to the selected magazine
                boolean found = false;
                for (Customer customer : database.getPayingCustomers()) {
                    if (customer.getMagazines() != null && customer.getMagazines().contains(newValue)) {
                        Label subscribedCustomerLabel = new Label(customer.getName());
                        infoPanel.getChildren().add(subscribedCustomerLabel);
                        found = true;
                    }
                }

                // Do the same for associate customers
                for (Customer customer : database.getAssociateCustomers()) {
                    if (customer.getMagazines() != null && customer.getMagazines().contains(newValue)) {
                        Label subscribedCustomerLabel = new Label(customer.getName());
                        infoPanel.getChildren().add(subscribedCustomerLabel);
                        found = true;
                    }
                }

                // If no customers are found, display a message indicating that
                if (!found) {
                    infoPanel.getChildren().add(new Label("No customers are subscribed to this magazine."));
                }
            }
        });

        primaryStage.setOnCloseRequest(e -> saveDataToFile(database));

    }

    /**
     * Displays the billing history for a customer. If the customer is not an
     * instance of {@link PayingCustomer}, the method returns without displaying
     * anything.
     *
     * @param customer The customer whose billing history is to be displayed.
     */
    private void displayBillingHistory(Customer customer) {
        if (!(customer instanceof PayingCustomer)) {
            return; // Optionally handle this case
        }
// multithreading 
        Task<BillingInfo> task = new Task<>() {
            @Override
            protected BillingInfo call() {
                return ((PayingCustomer) customer).generateComprehensiveBillingInfo();
            }
        };

        task.setOnSucceeded(e -> {
            BillingInfo billingInfo = task.getValue();
            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);

                // Layout for billing details
                VBox detailsBox = new VBox(10);
                ScrollPane scrollPane = new ScrollPane(detailsBox);
                scrollPane.setFitToWidth(true);
                detailsBox.getChildren().add(new Label("Billing Breakdown:"));

                for (String detail : billingInfo.getDetails()) {
                    detailsBox.getChildren().add(new Label(detail));
                }

                // Layout for cost summary
                VBox costBox = new VBox(10);
                costBox.setPadding(new Insets(10));
                costBox.getChildren().add(new Label("Cost Summary:"));
                float totalCost = billingInfo.getTotalCost();
                costBox.getChildren().add(new Label(String.format("Weekly Cost: $%.2f", totalCost)));
                costBox.getChildren().add(new Label(String.format("Monthly Cost: $%.2f", totalCost * 4))); // Assuming monthly cost is 4 times weekly cost

                // Main layout container
                HBox mainContainer = new HBox(20, scrollPane, costBox);
                mainContainer.setAlignment(Pos.CENTER_LEFT);
                mainContainer.setPadding(new Insets(10));

                Scene scene = new Scene(mainContainer);
                stage.setScene(scene);
                stage.setTitle("Billing History for: " + billingInfo.getCustomerName());
                stage.setWidth(600); // Adjust width and height as needed
                stage.setHeight(400);
                stage.show();
            });
        });

        new Thread(task).start();
    }

    /**
     * Loads data from a file and returns a {@link Database} object. If the data
     * file is not found or loading fails, a new {@link Database} object is
     * returned.
     *
     * @return The loaded or new {@link Database} object.
     */
    private Database loadDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (Database) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new database.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Database(); // Return a new database if loading fails
    }

    /**
     * Saves the provided {@link Database} object to a file.
     *
     * @param database The {@link Database} object to be saved.
     */
    private void saveDataToFile(Database database) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and returns a panel for creating new entities (magazines,
     * supplements, customers).
     *
     * @param primaryStage The primary stage of the application.
     * @return A {@link VBox} containing buttons for creating new entities.
     */
    private VBox createModePanel(Stage primaryStage) {
        // Buttons
        Button createMagazineBtn = new Button("Create Magazine");
        Button createSupplementBtn = new Button("Create Supplement");
        Button createCustomerBtn = new Button("Create Customer");

        // Event Handlers
        createMagazineBtn.setOnAction(e -> showCreateMagazineWindow());
        createSupplementBtn.setOnAction(e -> showCreateSupplementWindow());
        createCustomerBtn.setOnAction(e -> showCreateCustomerWindow(primaryStage));

        // VBox with buttons
        VBox panel = new VBox(10); // Spacing of 10
        panel.getChildren().add(new Label("Create Panel"));
        panel.getChildren().addAll(createMagazineBtn, createSupplementBtn, createCustomerBtn);
        panel.setAlignment(Pos.CENTER); // Center the buttons

        // The VBox will take up all available space
        panel.setFillWidth(true);

        // If you want margins around the VBox itself (optional)
        VBox.setMargin(panel, new Insets(20)); // Margin of 20

        // Set the panel to grow vertically to push the buttons to the center
        VBox.setVgrow(panel, Priority.ALWAYS);

        panel.setVisible(false); // Initially invisible

        return panel;
    }

    /**
     * Creates and returns a panel for editing existing entities (magazines,
     * supplements, customers).
     *
     * @return A {@link VBox} containing buttons for editing existing entities.
     */
    private VBox editModePanel() {
        VBox panel = new VBox(10);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));

        Button editSupplementBtn = new Button("Edit Supplement");
        Button editCustomerBtn = new Button("Edit Customer");
        Button editMagazineBtn = new Button("Edit Magazine"); // New button for editing magazines

        editSupplementBtn.setOnAction(e -> showEditSupplementWindow());
        editCustomerBtn.setOnAction(e -> showEditCustomerWindow());
        editMagazineBtn.setOnAction(e -> showEditMagazineWindow()); // Set the action for the new button

        panel.getChildren().addAll(new Label("Edit Panel"), editSupplementBtn, editCustomerBtn, editMagazineBtn);
        return panel;
    }

    /**
     * Displays a window for editing details of a selected magazine.
     *
     * @param magazine The magazine to be edited.
     */
    private void showEditMagazineWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Magazine");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        // Magazine selection
        ListView<Magazine> magazinesListView = setupMagazinesListView();

        Button editDetailsBtn = new Button("Edit Selected Magazine");
        Button deleteMagazineBtn = new Button("Delete Selected Magazine");

        editDetailsBtn.setOnAction(e -> {
            Magazine selectedMagazine = magazinesListView.getSelectionModel().getSelectedItem();
            if (selectedMagazine != null) {
                showEditSelectedMagazineWindow(selectedMagazine); // This will open a new window for editing the selected magazine
            } else {
                showAlert("Edit Action", "No magazine selected.");
            }
        });

        deleteMagazineBtn.setOnAction(e -> {
            Magazine selectedMagazine = magazinesListView.getSelectionModel().getSelectedItem();
            if (selectedMagazine != null) {
                database.removeMagazine(selectedMagazine); // Assuming you have a method in your Database class for this
                magazinesListView.getItems().remove(selectedMagazine);
                showAlert("Deletion", "Magazine deleted successfully.");
            } else {
                showAlert("Delete Action", "No magazine selected.");
            }
        });

        layout.getChildren().addAll(new Label("Select a Magazine"), magazinesListView, editDetailsBtn, deleteMagazineBtn);

        window.setScene(new Scene(layout, 400, 600));
        window.showAndWait();
    }

    /**
     * Displays a window to edit the details of a selected magazine.
     *
     * @param magazine The magazine object to be edited.
     */
    private void showEditSelectedMagazineWindow(Magazine magazine) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Magazine");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        TextField magazineNameField = new TextField(magazine.getMagazineName());
        TextField magazineCostField = new TextField(String.valueOf(magazine.getMagazineCost()));
        ListView<Supplement> currentSupplementsView = new ListView<>(FXCollections.observableArrayList(magazine.getMagazineSupplement()));
        ListView<Supplement> availableSupplementsView = new ListView<>();
        availableSupplementsView.setItems(FXCollections.observableArrayList(database.getSupplements().stream()
                .filter(supplement -> !magazine.getMagazineSupplement().contains(supplement))
                .collect(Collectors.toList())));

        Button addSupplementButton = new Button("Add Supplement");
        Button removeSupplementButton = new Button("Remove Supplement");
        Button saveButton = new Button("Save");

        addSupplementButton.setOnAction(e -> {
            Supplement selected = availableSupplementsView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                magazine.addSupplement(selected);
                currentSupplementsView.getItems().add(selected);
                availableSupplementsView.getItems().remove(selected);
            }
        });

        removeSupplementButton.setOnAction(e -> {
            Supplement selected = currentSupplementsView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                magazine.removeSupplement(selected);
                currentSupplementsView.getItems().remove(selected);
                availableSupplementsView.getItems().add(selected);
            }
        });

        saveButton.setOnAction(e -> {
            magazine.setMagazineName(magazineNameField.getText());
            magazine.setMagazineCost(Float.parseFloat(magazineCostField.getText()));
            // Assume database.updateMagazine(magazine) updates the magazine in the database
            //database.updateMagazine(magazine);
            window.close();
        });

        layout.add(new Label("Magazine Name:"), 0, 0);
        layout.add(magazineNameField, 1, 0);
        layout.add(new Label("Magazine Cost:"), 0, 1);
        layout.add(magazineCostField, 1, 1);
        layout.add(new Label("Current Supplements:"), 0, 2);
        layout.add(currentSupplementsView, 1, 2);
        layout.add(removeSupplementButton, 2, 2);
        layout.add(new Label("Available Supplements:"), 0, 3);
        layout.add(availableSupplementsView, 1, 3);
        layout.add(addSupplementButton, 2, 3);
        layout.add(saveButton, 1, 4);

        Scene scene = new Scene(layout, 500, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Sets up and returns a ListView containing magazines fetched from the
     * database.
     *
     * @return ListView containing Magazine objects.
     */
    private ListView<Magazine> setupMagazinesListView() {
        ListView<Magazine> magazinesListView = new ListView<>();
        magazinesListView.getItems().addAll(database.getMagazines());
        magazinesListView.setCellFactory(lv -> new ListCell<Magazine>() {
            @Override
            protected void updateItem(Magazine item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getMagazineName()); // Assuming Magazine has a getMagazineName method
            }
        });
        return magazinesListView;
    }

    /**
     * Displays a window to edit the details of a selected supplement.
     *
     * @param supplement The supplement object to be edited.
     */
    private void showEditSelectedSupplementWindow(Supplement supplement) {
        Stage editWindow = new Stage();
        editWindow.initModality(Modality.APPLICATION_MODAL);
        editWindow.setTitle("Edit Supplement");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        TextField supplementNameField = new TextField(supplement.getSupplementName());
        TextField supplementPriceField = new TextField(String.valueOf(supplement.getSupplementCost()));

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String newName = supplementNameField.getText();
            try {
                float newPrice = Float.parseFloat(supplementPriceField.getText());
                supplement.setSupplementName(newName);
                supplement.setSupplementCost(newPrice);
                // Here you might want to update the database or list view accordingly
                supplementsList.refresh(); // If ListView displays Supplement objects directly
                editWindow.close();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price format. Please enter a valid number.");
            }
        });

        layout.add(new Label("Name:"), 0, 0);
        layout.add(supplementNameField, 1, 0);
        layout.add(new Label("Price ($):"), 0, 1);
        layout.add(supplementPriceField, 1, 1);
        layout.add(saveButton, 1, 2);

        editWindow.setScene(new Scene(layout, 300, 200));
        editWindow.showAndWait();
    }

    /**
     * Displays a window to edit the details of a supplement.
     */
    private void showEditSupplementWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Supplement");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        ListView<Supplement> supplementsListView = setupSupplementsListView();

        Button editDetailsBtn = new Button("Edit Selected Supplement");
        Button deleteSupplementBtn = new Button("Delete Selected Supplement");

        // Handling edit action
        editDetailsBtn.setOnAction(e -> handleEditAction(supplementsListView));

        // Handling delete action
        deleteSupplementBtn.setOnAction(e -> handleDeleteAction(supplementsListView));

        layout.getChildren().addAll(new Label("Select a Supplement"), supplementsListView, editDetailsBtn, deleteSupplementBtn);

        window.setScene(new Scene(layout, 300, 400));
        window.showAndWait();
    }

    /**
     * Sets up and returns a ListView containing supplements fetched from the
     * database.
     *
     * @return ListView containing Supplement objects.
     */
    private ListView<Supplement> setupSupplementsListView() {
        ListView<Supplement> supplementsListView = new ListView<>();
        supplementsListView.getItems().addAll(database.getSupplements());
        supplementsListView.setCellFactory(lv -> new ListCell<Supplement>() {
            @Override
            protected void updateItem(Supplement item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getSupplementName());
            }
        });
        return supplementsListView;
    }

    /**
     * Handles the edit action for a selected supplement.
     *
     * @param listView ListView containing supplements.
     */
    private void handleEditAction(ListView<Supplement> listView) {
        Supplement selectedSupplement = listView.getSelectionModel().getSelectedItem();
        if (selectedSupplement != null) {
            showEditSelectedSupplementWindow(selectedSupplement);
        } else {
            showAlert("Edit Action", "No supplement selected.");
        }
    }

    /**
     * Handles the delete action for a selected supplement.
     *
     * @param listView ListView containing supplements.
     */
    private void handleDeleteAction(ListView<Supplement> listView) {
        Supplement selectedSupplement = listView.getSelectionModel().getSelectedItem();
        if (selectedSupplement != null) {
            if (confirmDeletion()) {
                database.removeSupplement(selectedSupplement);
                listView.getItems().remove(selectedSupplement);
                showAlert("Deletion", "Supplement deleted successfully.");
            }
        } else {
            showAlert("Delete Action", "No supplement selected.");
        }
        refreshSupplementsList();
    }

    /**
     * Displays a confirmation dialog for deletion and returns the user's
     * choice.
     *
     * @return True if deletion is confirmed, false otherwise.
     */
    private boolean confirmDeletion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected supplement?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    /**
     * Displays an alert dialog with the given title and message.
     *
     * @param title The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a window to edit customer details.
     */
    private void showEditCustomerWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Customer");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Button editDetailsBtn = new Button("Edit Details");
        Button removeCustomerBtn = new Button("Remove Customer from Magazine");
        Button addAssociateCustomerBtn = new Button("Add Associate Customer to Paying Customer");
        Button removeAssociateBtn = new Button("Remove Associate from Paying Customer");

        // Action handlers - these will call other methods to perform the actual operations
        editDetailsBtn.setOnAction(e -> editCustomerDetails());
        removeCustomerBtn.setOnAction(e -> removeCustomerFromMagazine());
        addAssociateCustomerBtn.setOnAction(e -> addAssociateCustomerToPayingCustomer());
        removeAssociateBtn.setOnAction(e -> showRemoveAssociateWindow());

        layout.getChildren().addAll(new Label("Edit Customer"), editDetailsBtn, removeCustomerBtn, addAssociateCustomerBtn);
        layout.getChildren().add(removeAssociateBtn);
        window.setScene(new Scene(layout, 350, 250));
        window.showAndWait();
        refreshMagazinesList();
    }

    /**
     * Displays a window to remove an associate customer from a paying customer.
     */
    private void showRemoveAssociateWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Remove Associate Customer");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        ComboBox<PayingCustomer> payingCustomerComboBox = new ComboBox<>();
        payingCustomerComboBox.setItems(FXCollections.observableArrayList(database.getPayingCustomers()));
        payingCustomerComboBox.setPromptText("Select Paying Customer");

        ListView<Customer> associateListView = new ListView<>();
        associateListView.setPrefHeight(150);

        payingCustomerComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                associateListView.setItems(FXCollections.observableArrayList(newVal.getAssociateCustomers()));
            }
        });

        Button removeBtn = new Button("Remove Selected Associate");
        removeBtn.setOnAction(e -> {
            Customer selectedAssociate = associateListView.getSelectionModel().getSelectedItem();
            PayingCustomer selectedPayingCustomer = payingCustomerComboBox.getSelectionModel().getSelectedItem();
            if (selectedAssociate != null && selectedPayingCustomer != null) {
                selectedPayingCustomer.getAssociateCustomers().remove(selectedAssociate);
                database.getAssociateCustomers().add(selectedAssociate); // Assuming you have a method to add back to the database
                associateListView.getItems().remove(selectedAssociate);
            }
        });

        layout.getChildren().addAll(new Label("Select Paying Customer and Associate to Remove"), payingCustomerComboBox, associateListView, removeBtn);

        window.setScene(new Scene(layout, 350, 300));
        window.showAndWait();
        refreshCustomersList();
    }

    /**
     * Edits the details of a customer.
     */
    private void editCustomerDetails() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Customer Details");

        BorderPane borderPane = new BorderPane();

        // Left Panel: Customer List
        VBox leftPanel = new VBox(10);
        leftPanel.setPadding(new Insets(10));

        ListView<Customer> customerListView = new ListView<>();
        ObservableList<Customer> customers = FXCollections.observableArrayList(
                Stream.concat(database.getPayingCustomers().stream(), database.getAssociateCustomers().stream())
                        .collect(Collectors.toList()));
        customerListView.setItems(customers);
        customerListView.setPrefWidth(200);
        customerListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });

        leftPanel.getChildren().add(new Label("Select Customer:"));
        leftPanel.getChildren().add(customerListView);
        borderPane.setLeft(leftPanel);

        // Right Panel: Customer Details
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(10));

        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField bankField = new TextField();
        ComboBox<PayingCustomer.PaymentMethod> paymentMethodComboBox = new ComboBox<>();
        paymentMethodComboBox.getItems().addAll(PayingCustomer.PaymentMethod.values());

        rightPanel.getChildren().addAll(new Label("Name:"), nameField, new Label("Email:"), emailField);

        // Bottom Buttons
        Button saveButton = new Button("Save");
        Button editMagazinesAndSupplementsButton = new Button("Edit Magazines and Supplements");
        HBox buttonPanel = new HBox(10, saveButton, editMagazinesAndSupplementsButton);
        buttonPanel.setAlignment(Pos.CENTER);

        Label bankLabel = new Label("Bank:");
        Label paymentMethodLabel = new Label("Payment Method:");

        bankField.setVisible(false);
        paymentMethodComboBox.setVisible(false);
        bankLabel.setVisible(false);
        paymentMethodLabel.setVisible(false);

// Add them to the right panel initially but set them as invisible
        rightPanel.getChildren().addAll(bankLabel, bankField, paymentMethodLabel, paymentMethodComboBox);

        // Selection listener to populate and adjust fields based on customer type
        customerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedCustomer) -> {
            if (selectedCustomer != null) {
                nameField.setText(selectedCustomer.getName());
                emailField.setText(selectedCustomer.getEmail());
                if (selectedCustomer instanceof PayingCustomer) {
                    PayingCustomer payingCustomer = (PayingCustomer) selectedCustomer;
                    bankField.setText(payingCustomer.getBank());
                    paymentMethodComboBox.setValue(payingCustomer.getSelectedPaymentMethod());

                    // Make bank details and payment method fields visible
                    bankField.setVisible(true);
                    paymentMethodComboBox.setVisible(true);
                    bankLabel.setVisible(true);
                    paymentMethodLabel.setVisible(true);
                } else {
                    // Hide bank details and payment method fields for non-paying customers
                    bankField.setVisible(false);
                    paymentMethodComboBox.setVisible(false);
                    bankLabel.setVisible(false);
                    paymentMethodLabel.setVisible(false);

                    // Clear any previously entered/selected data
                    bankField.clear();
                    paymentMethodComboBox.getSelectionModel().clearSelection();
                }
            } else {
                // Clear all fields if no customer is selected and hide bank/payment method fields
                nameField.clear();
                emailField.clear();
                bankField.clear();
                paymentMethodComboBox.getSelectionModel().clearSelection();
                bankField.setVisible(false);
                paymentMethodComboBox.setVisible(false);
                bankLabel.setVisible(false);
                paymentMethodLabel.setVisible(false);
            }
        });

        editMagazinesAndSupplementsButton.setOnAction(e -> {
            Customer selectedCustomer = customerListView.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                openMagazineAndSupplementEditor(selectedCustomer);
            }
        });

        saveButton.setOnAction(e -> {
            Customer selectedCustomer = customerListView.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                // Update the selected customer's properties
                selectedCustomer.setName(nameField.getText());
                selectedCustomer.setEmail(emailField.getText());

                // If the customer is a PayingCustomer, update bank and payment method as well
                if (selectedCustomer instanceof PayingCustomer) {
                    PayingCustomer payingCustomer = (PayingCustomer) selectedCustomer;
                    payingCustomer.setBank(bankField.getText());
                    payingCustomer.setSelectedPaymentMethod(paymentMethodComboBox.getValue());
                }

                // Here you should add logic to persist these changes to your database or data model.
                // For example, if you have a method in your database class to update a customer, call it here.
                // database.updateCustomer(selectedCustomer);
                // Refresh the customer list view to reflect changes
                customerListView.refresh();

                // Optionally, close the edit window or show a confirmation message
                // window.close();
                // Show a confirmation dialog or message to the user
            }
        });

        rightPanel.getChildren().add(buttonPanel);
        borderPane.setCenter(rightPanel);

        Scene scene = new Scene(borderPane, 600, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Opens a window to edit magazines and supplements for a specific customer.
     *
     * @param customer The customer object for whom magazines and supplements
     * are to be edited.
     */
    private void openMagazineAndSupplementEditor(Customer customer) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Magazines and Supplements for " + customer.getName());

        BorderPane borderPane = new BorderPane();
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        // Displaying customer name
        Label customerNameLabel = new Label("Editing for: " + customer.getName());
        customerNameLabel.setFont(new Font("Arial", 16));

        // Customer's current Magazines and Supplements
        ListView<Magazine> customerMagazinesView = new ListView<>(FXCollections.observableArrayList(customer.getMagazines()));
        ListView<Supplement> customerSupplementsView = new ListView<>(FXCollections.observableArrayList(customer.getSupplements()));

        // Database Magazines and Supplements excluding customer's
        List<Magazine> availableMagazines = database.getMagazines().stream()
                .filter(mag -> !customer.getMagazines().contains(mag))
                .collect(Collectors.toList());
        ListView<Magazine> availableMagazinesView = new ListView<>(FXCollections.observableArrayList(availableMagazines));

        List<Supplement> availableSupplements = database.getSupplements().stream()
                .filter(sup -> !customer.getSupplements().contains(sup))
                .collect(Collectors.toList());
        ListView<Supplement> availableSupplementsView = new ListView<>(FXCollections.observableArrayList(availableSupplements));

        // Buttons for adding and removing magazines/supplements
        Button addMagazineButton = new Button("Add Magazine");
        Button removeMagazineButton = new Button("Remove Magazine");
        Button addSupplementButton = new Button("Add Supplement");
        Button removeSupplementButton = new Button("Remove Supplement");

        // Action Handlers for Buttons
        addMagazineButton.setOnAction(event -> {
            Magazine selected = availableMagazinesView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customer.addMagazine(selected);
                customerMagazinesView.getItems().add(selected);
                availableMagazinesView.getItems().remove(selected);
            }
        });

        removeMagazineButton.setOnAction(event -> {
            Magazine selected = customerMagazinesView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customer.removeMagazine(selected);
                customerMagazinesView.getItems().remove(selected);
                availableMagazinesView.getItems().add(selected);
            }
        });

        addSupplementButton.setOnAction(event -> {
            Supplement selected = availableSupplementsView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customer.addSupplement(selected);
                customerSupplementsView.getItems().add(selected);
                availableSupplementsView.getItems().remove(selected);
            }
        });

        removeSupplementButton.setOnAction(event -> {
            Supplement selected = customerSupplementsView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customer.removeSupplement(selected);
                customerSupplementsView.getItems().remove(selected);
                availableSupplementsView.getItems().add(selected);
            }
        });

        // Layout setup
        HBox buttonLayoutMagazine = new HBox(10, addMagazineButton, removeMagazineButton);
        VBox magazineLayout = new VBox(5, new Label("Magazines"), customerMagazinesView, availableMagazinesView, buttonLayoutMagazine);

        HBox buttonLayoutSupplement = new HBox(10, addSupplementButton, removeSupplementButton);
        VBox supplementLayout = new VBox(5, new Label("Supplements"), customerSupplementsView, availableSupplementsView, buttonLayoutSupplement);

        HBox contentLayout = new HBox(10, magazineLayout, supplementLayout);
        mainLayout.getChildren().addAll(customerNameLabel, contentLayout);
        borderPane.setCenter(mainLayout);

        Scene scene = new Scene(borderPane, 800, 600);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Removes a customer from a magazine.
     */
    private void removeCustomerFromMagazine() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Remove Customer from Magazine");

        // Use a BorderPane for overall layout
        BorderPane borderPane = new BorderPane();

        // Customer List Panel
        VBox customerListPanel = new VBox(10);
        ListView<Customer> customerListView = new ListView<>();
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers.addAll(database.getPayingCustomers());
        customers.addAll(database.getAssociateCustomers());
        customerListView.setItems(customers);
        customerListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        customerListPanel.getChildren().addAll(new Label("Select Customer:"), customerListView);

        // Magazine List Panel (empty initially)
        VBox magazineListPanel = new VBox(10);
        ListView<Magazine> magazineListView = new ListView<>();
        magazineListView.setCellFactory(param -> new ListCell<Magazine>() {
            @Override
            protected void updateItem(Magazine magazine, boolean empty) {
                super.updateItem(magazine, empty);
                if (empty || magazine == null) {
                    setText(null);
                } else {
                    setText(magazine.getMagazineName()); // Assuming Magazine has a getMagazineName() method
                }
            }
        });

        magazineListPanel.getChildren().addAll(new Label("Customer's Magazines:"), magazineListView);

        Button removeMagazineButton = new Button("Remove Selected Magazine");
        magazineListPanel.getChildren().add(removeMagazineButton);

        // When a customer is selected, update the magazine list
        customerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                magazineListView.setItems(FXCollections.observableArrayList(newValue.getMagazines()));
            }
        });

        // Handle magazine removal
        removeMagazineButton.setOnAction(e -> {
            Magazine selectedMagazine = magazineListView.getSelectionModel().getSelectedItem();
            if (selectedMagazine != null) {
                Customer selectedCustomer = customerListView.getSelectionModel().getSelectedItem();
                selectedCustomer.removeMagazine(selectedMagazine); // Assumes removeMagazine method exists
                magazineListView.getItems().remove(selectedMagazine);
            }
        });

        Button saveChangesButton = new Button("Save Changes");
        saveChangesButton.setOnAction(e -> {
            // Implement logic to save changes, such as updating the database or list views
            window.close();
        });

        HBox saveButtonBox = new HBox(10, saveChangesButton);
        saveButtonBox.setAlignment(Pos.CENTER);

        borderPane.setLeft(customerListPanel);
        borderPane.setCenter(magazineListPanel);
        borderPane.setBottom(saveButtonBox);

        Scene scene = new Scene(borderPane, 600, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Adds associate customers to a paying customer.
     */
    private void addAssociateCustomerToPayingCustomer() {
        // Fetch paying customers for the user to choose from
        List<PayingCustomer> payers = database.getPayingCustomers();
        ChoiceDialog<PayingCustomer> payerDialog = new ChoiceDialog<>(null, payers);
        payerDialog.setTitle("Select Paying Customer");
        payerDialog.setHeaderText("Select a paying customer to add associates");
        payerDialog.setContentText("Choose paying customer:");

        Optional<PayingCustomer> payerResult = payerDialog.showAndWait();
        if (!payerResult.isPresent()) {
            return; // Exit if no paying customer is selected
        }

        PayingCustomer selectedPayingCustomer = payerResult.get();

        // Create a custom dialog for selecting associate customers
        Dialog<List<Customer>> dialog = new Dialog<>();
        dialog.setTitle("Add Associate Customers");
        dialog.setHeaderText("Select associate customers to add to " + selectedPayingCustomer.getName());

        // Set up the buttons
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create a container for checkboxes
        VBox checkBoxContainer = new VBox();
        checkBoxContainer.setSpacing(10);

        // Populate the container with checkboxes, one for each associate customer
        List<CheckBox> checkboxes = new ArrayList<>();
        for (Customer associate : database.getAssociateCustomers()) {
            CheckBox checkBox = new CheckBox(associate.getName());
            checkBox.setUserData(associate); // Associate the actual Customer object with the checkbox
            checkboxes.add(checkBox);
            checkBoxContainer.getChildren().add(checkBox);
        }

        dialog.getDialogPane().setContent(checkBoxContainer);

        // Convert the result to a list of selected customers when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return checkboxes.stream()
                        .filter(CheckBox::isSelected)
                        .map(cb -> (Customer) cb.getUserData())
                        .collect(Collectors.toList());
            }
            return null;
        });

        Optional<List<Customer>> result = dialog.showAndWait();
        result.ifPresent(selectedAssociates -> {
            // Add selected associates to the paying customer and remove them from the database
            for (Customer associate : selectedAssociates) {
                selectedPayingCustomer.addAssociateCustomer(associate);
                database.removeAssociateCustomer(associate); // Assuming this method correctly removes the associate from the database
            }

            // Optionally refresh your UI here to reflect changes
        });
        refreshCustomersList();
    }

    /**
     * Shows a window to create a new magazine.
     */
    private void showCreateMagazineWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Magazine");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        TextField magazineNameField = new TextField();
        magazineNameField.setPromptText("Magazine Name");
        TextField magazineCostField = new TextField();
        magazineCostField.setPromptText("Magazine Cost");

        // Creating a VBox to hold checkboxes for each supplement
        VBox checkBoxContainer = new VBox();
        checkBoxContainer.setSpacing(5); // Set spacing between checkboxes

        // Dynamically creating a CheckBox for each supplement
        List<CheckBox> supplementCheckBoxes = database.getSupplements().stream()
                .map(supplement -> new CheckBox(supplement.getSupplementName()))
                .collect(Collectors.toList());

        // Adding checkboxes to the VBox container
        checkBoxContainer.getChildren().addAll(supplementCheckBoxes);

        Button createMagazineButton = new Button("Create Magazine");
        createMagazineButton.setOnAction(e -> {
            String magazineName = magazineNameField.getText();
            float magazineCost;
            try {
                magazineCost = Float.parseFloat(magazineCostField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid magazine cost. Please enter a valid number.");
                return; // Exit the method if the cost is not a valid number
            }

            // Collecting selected supplements based on CheckBox selections
            List<Supplement> selectedSupplements = supplementCheckBoxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(checkBox -> database.getSupplements().stream()
                    .filter(supplement -> supplement.getSupplementName().equals(checkBox.getText()))
                    .findFirst().orElse(null))
                    .collect(Collectors.toList());

            Magazine newMagazine = new Magazine(magazineName, magazineCost, selectedSupplements);
            database.addMagazine(newMagazine);
            window.close();
        });

        layout.add(new Label("Magazine Name:"), 0, 0);
        layout.add(magazineNameField, 1, 0);
        layout.add(new Label("Magazine Cost:"), 0, 1);
        layout.add(magazineCostField, 1, 1);
        layout.add(new Label("Select Supplements:"), 0, 2);
        // Setting constraints for the VBox to ensure it aligns properly
        GridPane.setConstraints(checkBoxContainer, 1, 2);
        layout.getChildren().addAll(checkBoxContainer);
        layout.add(createMagazineButton, 1, 3);

        window.setScene(new Scene(layout, 400, 400)); // Adjust window size as needed
        window.showAndWait();
        refreshMagazinesList();
    }

    /**
     * Shows a window to create a new supplement.
     */
    private void showCreateSupplementWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Supplement");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        // Supplement name input
        TextField supplementNameField = new TextField();
        supplementNameField.setPromptText("Supplement Name");

        // Supplement cost input
        TextField supplementCostField = new TextField();
        supplementCostField.setPromptText("Supplement Cost");

        // Create button
        Button createSupplementButton = new Button("Create Supplement");
        createSupplementButton.setOnAction(e -> {
            try {
                String supplementName = supplementNameField.getText();
                float supplementCost = Float.parseFloat(supplementCostField.getText());
                Supplement newSupplement = new Supplement(supplementName, supplementCost);
                database.addSupplement(newSupplement);
                supplementsList.getItems().add(newSupplement); // Optionally refresh the list
                window.close();
            } catch (NumberFormatException ex) {
                // Handle invalid cost input (e.g., show an error message)
                System.out.println("Invalid supplement cost. Please enter a valid number.");
            }
        });

        // Adding components to the layout
        layout.add(new Label("Supplement Name:"), 0, 0);
        layout.add(supplementNameField, 1, 0);
        layout.add(new Label("Supplement Cost:"), 0, 1);
        layout.add(supplementCostField, 1, 1);
        layout.add(createSupplementButton, 1, 2);

        window.setScene(new Scene(layout, 300, 200));
        window.showAndWait();
    }

    /**
     * Shows a window to create a new customer, with options to create either an
     * associate or paying customer.
     *
     * @param primaryStage The primary stage of the application.
     */
    private void showCreateCustomerWindow(Stage primaryStage) {
        Stage window = new Stage();
        window.initOwner(primaryStage);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Customer");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Button createAssociateCustomerBtn = new Button("Create Associate Customer");
        Button createPayingCustomerBtn = new Button("Create Paying Customer");

        createAssociateCustomerBtn.setOnAction(e -> showCreateAssociateCustomerWindow());
        createPayingCustomerBtn.setOnAction(e -> showCreatePayingCustomerWindow());

        layout.getChildren().addAll(createAssociateCustomerBtn, createPayingCustomerBtn);

        window.setScene(new Scene(layout, 300, 200));
        window.showAndWait();
    }

    /**
     * Shows a window to create a new associate customer.
     */
    private void showCreateAssociateCustomerWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Associate Customer");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        // Supplement selection checkboxes
        VBox supplementCheckBoxContainer = new VBox();
        List<CheckBox> supplementCheckBoxes = database.getSupplements().stream()
                .map(supplement -> new CheckBox(supplement.getSupplementName()))
                .collect(Collectors.toList());
        supplementCheckBoxContainer.getChildren().addAll(new Label("Select Supplements:"), new Separator());
        supplementCheckBoxContainer.getChildren().addAll(supplementCheckBoxes);

        // Magazine selection checkboxes
        VBox magazineCheckBoxContainer = new VBox();
        List<CheckBox> magazineCheckBoxes = database.getMagazines().stream()
                .map(magazine -> new CheckBox(magazine.getMagazineName()))
                .collect(Collectors.toList());
        magazineCheckBoxContainer.getChildren().addAll(new Label("Select Magazines:"), new Separator());
        magazineCheckBoxContainer.getChildren().addAll(magazineCheckBoxes);

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> {
            List<Supplement> selectedSupplements = supplementCheckBoxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> database.getSupplements().stream()
                    .filter(supplement -> supplement.getSupplementName().equals(cb.getText()))
                    .findFirst().orElse(null))
                    .collect(Collectors.toList());

            List<Magazine> selectedMagazines = magazineCheckBoxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> database.getMagazines().stream()
                    .filter(magazine -> magazine.getMagazineName().equals(cb.getText()))
                    .findFirst().orElse(null))
                    .collect(Collectors.toList());

            Customer newCustomer = new Customer(nameField.getText(), emailField.getText(), selectedMagazines, selectedSupplements);
            database.addAssociateCustomer(newCustomer);
            customersList.getItems().add(newCustomer); // Refresh the customer list if necessary
            window.close();
        });

        layout.add(new Label("Name:"), 0, 0);
        layout.add(nameField, 1, 0);
        layout.add(new Label("Email:"), 0, 1);
        layout.add(emailField, 1, 1);
        layout.add(supplementCheckBoxContainer, 1, 2);
        layout.add(magazineCheckBoxContainer, 1, 3);
        layout.add(createButton, 1, 4);

        window.setScene(new Scene(layout, 350, 400));
        window.showAndWait();
    }

    /**
     * Shows a window to create a new paying customer.
     */
    private void showCreatePayingCustomerWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Paying Customer");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        // Payment Method ComboBox
        ComboBox<PayingCustomer.PaymentMethod> paymentMethodComboBox = new ComboBox<>();
        paymentMethodComboBox.getItems().addAll(PayingCustomer.PaymentMethod.values());
        paymentMethodComboBox.setValue(PayingCustomer.PaymentMethod.CREDIT_CARD);

        TextField bankField = new TextField();
        bankField.setPromptText("Bank");

        // Supplement selection checkboxes
        VBox supplementCheckBoxContainer = new VBox();
        List<CheckBox> supplementCheckBoxes = database.getSupplements().stream()
                .map(supplement -> new CheckBox(supplement.getSupplementName()))
                .collect(Collectors.toList());
        supplementCheckBoxContainer.getChildren().addAll(new Label("Select Supplements:"), new Separator());
        supplementCheckBoxContainer.getChildren().addAll(supplementCheckBoxes);

        // Magazine selection checkboxes
        VBox magazineCheckBoxContainer = new VBox();
        List<CheckBox> magazineCheckBoxes = database.getMagazines().stream()
                .map(magazine -> new CheckBox(magazine.getMagazineName()))
                .collect(Collectors.toList());
        magazineCheckBoxContainer.getChildren().addAll(new Label("Select Magazines:"), new Separator());
        magazineCheckBoxContainer.getChildren().addAll(magazineCheckBoxes);

        // Associate Customer selection checkboxes
        VBox associateCustomerCheckBoxContainer = new VBox();
        List<CheckBox> associateCustomerCheckBoxes = database.getAssociateCustomers().stream()
                .map(customer -> new CheckBox(customer.getName()))
                .collect(Collectors.toList());
        associateCustomerCheckBoxContainer.getChildren().addAll(new Label("Select Associate Customers:"), new Separator());
        associateCustomerCheckBoxContainer.getChildren().addAll(associateCustomerCheckBoxes);

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> {
            List<Supplement> selectedSupplements = supplementCheckBoxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> database.getSupplements().stream()
                    .filter(supplement -> supplement.getSupplementName().equals(cb.getText()))
                    .findFirst().orElse(null))
                    .collect(Collectors.toList());

            List<Magazine> selectedMagazines = magazineCheckBoxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> database.getMagazines().stream()
                    .filter(magazine -> magazine.getMagazineName().equals(cb.getText()))
                    .findFirst().orElse(null))
                    .collect(Collectors.toList());

            List<Customer> selectedAssociateCustomers = associateCustomerCheckBoxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> database.getAssociateCustomers().stream()
                    .filter(customer -> customer.getName().equals(cb.getText()))
                    .findFirst().orElse(null))
                    .collect(Collectors.toList());

            PayingCustomer newPayingCustomer = new PayingCustomer(
                    paymentMethodComboBox.getValue(),
                    bankField.getText(),
                    selectedAssociateCustomers,
                    nameField.getText(),
                    emailField.getText(),
                    selectedMagazines,
                    selectedSupplements
            );
            database.addPayingCustomer(newPayingCustomer);// Remove selected associate customers from the database
            selectedAssociateCustomers.forEach(associateCustomer -> {
                database.removeAssociateCustomer(associateCustomer); // Assuming this method exists in your Database class
                customersList.getItems().remove(associateCustomer); // Optionally update the UI list if needed
            });

            customersList.getItems().add(newPayingCustomer); // Refresh the customer list if necessary
            window.close();
        });

        layout.add(new Label("Name:"), 0, 0);
        layout.add(nameField, 1, 0);
        layout.add(new Label("Email:"), 0, 1);
        layout.add(emailField, 1, 1);
        layout.add(new Label("Payment Method:"), 0, 2);
        layout.add(paymentMethodComboBox, 1, 2);
        layout.add(new Label("Bank:"), 0, 3);
        layout.add(bankField, 1, 3);
        layout.add(supplementCheckBoxContainer, 0, 4, 2, 1);
        layout.add(magazineCheckBoxContainer, 0, 5, 2, 1);
        layout.add(associateCustomerCheckBoxContainer, 0, 6, 2, 1);
        layout.add(createButton, 1, 7);

        window.setScene(new Scene(layout, 350, 600)); // Adjust the size as necessary to fit all components
        window.showAndWait();
    }

    /**
     * Refreshes the supplements list view.
     */
    private void refreshSupplementsList() {
        ObservableList<Supplement> updatedList = FXCollections.observableArrayList(database.getSupplements());
        supplementsList.setItems(updatedList);
    }

    /**
     * Refreshes the magazines list view.
     */
    private void refreshMagazinesList() {
        ObservableList<Magazine> updatedList = FXCollections.observableArrayList(database.getMagazines());
        // Assuming magazinesList is your ListView for displaying magazines
        magazinesList.setItems(updatedList);
    }

    /**
     * Refreshes the customers list view.
     */
    private void refreshCustomersList() {
        ObservableList<Customer> updatedCustomers = FXCollections.observableArrayList();
        updatedCustomers.addAll(database.getPayingCustomers());
        updatedCustomers.addAll(database.getAssociateCustomers());
        customersList.setItems(updatedCustomers);
    }

    /**
     * Main method to launch the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        database.populate();
        launch(args);
    }

}
