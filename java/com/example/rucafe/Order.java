package com.example.rucafe;
import java.util.ArrayList;

/**
 * manages the orders for rucafe
 * @author Joon Song, Connor Powell
 */
public class Order{
    private int orderNumber;
    private ArrayList<MenuItem> itemList;

    /**
     Constructor to initialize the instance variables.
     @param orderNumber the number of the order
     */
    public Order(int orderNumber){
        this.orderNumber = orderNumber;
        this.itemList = new ArrayList<>();
    }

    /**
     Adds an item to the order.
     @param toAdd menuItem to be added
     */
    public void addItem(MenuItem toAdd){
        itemList.add(toAdd);
    }

    /**
     Removes an item from the order.
     @param toRemove menuItem to be added
     */
    public void removeItem(MenuItem toRemove){
        itemList.remove(toRemove);
    }

    /**
     Returns the order number.
     @return orderList instance variable
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     Returns the list of menu items.
     @return itemList instance variable
     */
    public ArrayList<MenuItem> getItemList(){
        return itemList;
    }
}
