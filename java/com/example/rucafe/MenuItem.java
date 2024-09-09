package com.example.rucafe;

/**
 * Abstract class that sets behavior that other menu items follow
 * @author Connor Powell, Joon Song
 */
public abstract class MenuItem {
    /**
     * getter method for price of the unit
     * @return price
     */
    public abstract double price();

    /**
     * getter method to get the get item cost as a string
     * @return string subtotal
     */
    public String getItemCostString() {
        return String.format("%1$.2f", getItemCost());
    }

    /**
     * getter method for total cost of an item
     * @return double representing the subtotal
     */
    public double getItemCost(){
        return price() *  getQuantity();
    }

    /**
     * gets quantity of an item
     * @return int quantity
     */
    public abstract int getQuantity();


}
