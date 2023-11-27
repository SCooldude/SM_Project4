package com.example.sm_project4;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Manages a collection of store orders.
 * Handles order creation, retrieval, and export functionality.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class StoreOrders {

    private final ArrayList<Order> storeOrders;
    private static int orderNumber = 0;

    /**
     * Constructs a new StoreOrders instance with an initial order.
     */
    public StoreOrders() {
        this.storeOrders = new ArrayList<>();
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order firstOrder = new Order(0, pizzaList);
        this.storeOrders.add(firstOrder);
    }

    /**
     * Gets the next available order number.
     * @return The next available order number.
     */
    public int nextAvailableNumber() {
        return orderNumber;
    }

    /**
     * Finds the index of the given order in the list.
     * @param order The order to find.
     * @return The index of the order, or -1 if not found.
     */
    public int findIndexOfOrder(Order order) {
        for (int i = 0; i < this.storeOrders.size(); i++) {
            if (storeOrders.get(i).getOrderNumber() == order.getOrderNumber()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a new order to the list and increments the order number.
     * @param order The order to add.
     */
    public void addOrder(Order order) {
        int index = findIndexOfOrder(order);
        this.storeOrders.set(index, order);
        orderNumber++;
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order setOrder = new Order(orderNumber, pizzaList);
        this.storeOrders.add(setOrder);
    }

    /**
     * Gets a list of order numbers.
     * @return The list of order numbers.
     */
    public ArrayList<Integer> getOrderNumbers() {
        ArrayList<Integer> num = new ArrayList<>();
        for (Order storeOrder : this.storeOrders) {
            int temp = storeOrder.getOrderNumber();
            num.add(temp);
        }
        return num;
    }

    /**
     * Finds and returns the order with the specified order number.
     * @param orderNumber The order number to find.
     * @return The order with the specified order number.
     */
    public Order find(int orderNumber) {
        for (Order storeOrder : this.storeOrders) {
            if (storeOrder.getOrderNumber() == orderNumber) {
                return storeOrder;
            }
        }
        return storeOrders.get(0);
    }

    /**
     * Gets the total number of orders.
     * @return The total number of orders.
     */
    public int numOrders() {
        return this.storeOrders.size();
    }

    /**
     * Converts an order to a formatted string.
     * @param index The index of the order.
     * @return The formatted string representation of the order.
     */
    public String orderToString(int index) {
        Order order = storeOrders.get(index);
        int orderNumber = order.getOrderNumber();
        double total = order.totalCost();
        double taxRate = 0.06625;
        total = (total * taxRate) + total;

        StringBuilder resultString = new StringBuilder("Order Number " + orderNumber);
        ArrayList<String> strings = order.getPizzaStrings();

        if (strings.isEmpty()) {
            return "";
        }

        for (String pizzaD : strings) {
            resultString.append("\n").append(pizzaD);
        }

        String formattedTotal = new DecimalFormat("#,##0.00").format(total);
        resultString.append("\nTotal Price: $").append(formattedTotal);

        return resultString.toString();
    }

    /**
     * Exports orders to a file.
     * @param stage The stage used for displaying the file chooser dialog.
     * @return True if the export is successful, false otherwise.
     */
    public boolean export(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        ExtensionFilter filter = new ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile == null) {
            return false;
        }
        StringBuilder fileContent = new StringBuilder();
        for (Order currentOrder : storeOrders) {
            fileContent.append(orderToString(currentOrder.getOrderNumber()));
            fileContent.append("\n\n");
        }
        String path = selectedFile.getAbsolutePath();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(fileContent.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
