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

public class StoreOrders {

    private final ArrayList<Order> storeOrders;
    private static int orderNumber = 0;

    public StoreOrders(){
        this.storeOrders = new ArrayList<>();
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order firstOrder = new Order(0, pizzaList);
        this.storeOrders.add(firstOrder);
    }

    public int nextAvailableNumber() {
        return orderNumber;
    }
    public int findIndexOfOrder(Order order){
        for(int i =0; i<this.storeOrders.size(); i++){
            if(storeOrders.get(i).getOrderNumber() == order.getOrderNumber()){
                return i;
            }
        }
        return -1;
    }

    public void addOrder(Order order) {
        int index = findIndexOfOrder(order);
        this.storeOrders.set(index, order);
        orderNumber++;
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order setOrder = new Order(orderNumber, pizzaList);
        this.storeOrders.add(setOrder);
    }

    public ArrayList<Integer> getOrderNumbers(){
        ArrayList<Integer> num = new ArrayList<>();
        for (Order storeOrder : this.storeOrders) {
            int temp = storeOrder.getOrderNumber();
            num.add(temp);
        }
        return num;
    }

    public Order find(int orderNumber){
        for (Order storeOrder : this.storeOrders) {
            if (storeOrder.getOrderNumber() == orderNumber) {
                return storeOrder;
            }
        }
        return storeOrders.get(0);
    }

    public int numOrders(){
        return this.storeOrders.size();
    }

    public String orderToString(int index){
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
