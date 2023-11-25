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
        // create first order
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order firstOrder = new Order(0, pizzaList);
        this.storeOrders.add(firstOrder);
    }

    public int getAvailable_OrderNumber() {
        return orderNumber;
    }

    public int findIndexOfOrder(Order order){
        for(int i =0; i<this.storeOrders.size(); i++){
            if(storeOrders.get(i).getOrderNumber() == order.getOrderNumber()){
                return i;
            }
        }
        return -1; //NOT FOUND
    }

    public void addOrder(Order order) {
        int index = findIndexOfOrder(order);
        this.storeOrders.set(index, order);
        //  this.storeOrders.set(getAvailable_OrderNumber(), order);


        orderNumber++;

        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order setOrder = new Order(orderNumber, pizzaList);
        this.storeOrders.add(setOrder);
    }

    public ArrayList<Integer> getOrderNumbers(){
        ArrayList<Integer> orderNumb = new ArrayList<>();
        for (Order storeOrder : this.storeOrders) {
            int tempNum = storeOrder.getOrderNumber();
            orderNumb.add(tempNum);
        }
        return orderNumb;
    }

    public Order find(int orderNumber){
        for (Order storeOrder : this.storeOrders) {
            if (storeOrder.getOrderNumber() == orderNumber) {
                return storeOrder;
            }
        }
        return storeOrders.get(0);
    }

    public boolean removeOrder(int orderNumber){
        Order removeMe = find(orderNumber);
        this.storeOrders.remove(removeMe);
        return true;
    }

    public int numberOfOrders(){
        return this.storeOrders.size();
    }

    public String orderToString(int index){
        Order order = storeOrders.get(index);
        int orderNumber = order.getOrderNumber();
        double total = order.total();
        double tax = 0.06625;
        total = (total*tax) + total;

        StringBuilder returnString = new StringBuilder("Order Number " + orderNumber);
        ArrayList<String> pizzaStrings = order.getPizzas();
        if(pizzaStrings.isEmpty()){
            return "";
        }
        for (String pizzaString : pizzaStrings) {
            returnString.append("\n").append(pizzaString);
        }
        String totalString = new DecimalFormat("#,##0.00").format(total);
        returnString.append("\n" + "Total Price: $").append(totalString);
        return returnString.toString();
    }

    public boolean export(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        ExtensionFilter ex1 = new ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(ex1);
        File selectedFile = fileChooser.showSaveDialog(stage);

        if(selectedFile == null){
            return false;
        }
        StringBuilder finalProduct = new StringBuilder();
        for(int i =0; i<numberOfOrders(); i++){
            finalProduct.append(orderToString(i));
            finalProduct.append("\n\n");
        }

        String absPath = selectedFile.getAbsolutePath();
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(absPath));
            writer.write(finalProduct.toString());
            writer.close();
            return true;
        } catch(IOException e){
            return false;
        }

    }


}
