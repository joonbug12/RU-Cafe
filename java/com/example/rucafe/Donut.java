package com.example.rucafe;

import java.util.Objects;

/**
 * Extension of the menu item class that stores information about donuts
 * @author Connor Powell, Joon Song
 */
public class Donut extends MenuItem{
    /*
     * cake donut 1.89
     * yeast donut 1.79
     * donut hole 0.39
     * price is same regardless of the flavor
     *
     */
    private int quantity;
    private String donutType;
    private String flavor;

    static final double CAKE_PRICE = 1.89;
    static final double YEAST_PRICE = 1.79;
    private static final double HOLES_PRICE = 0.39;

    public static final String CAKE_TYPE = "Cake Donut";
    public static final String YEAST_TYPE ="Yeast Donut";
    public static final String HOLES_TYPE = "Donut Hole";

    public static final String[] DONUT_TYPES = new String[]{CAKE_TYPE,YEAST_TYPE,HOLES_TYPE};

    /**
     * Constructor that creates a Donut object based off the given strings and ints.
     * It assigns the proper values to the instance variables quantity, donutType, and flavor
     * @param donutType type of donut
     * @param flavor of the donut
     * @param quantity the number of donuts
     */
    public Donut(int quantity, String donutType, String flavor){
        this.donutType = donutType;
        this.flavor=flavor;
        this.quantity= quantity;
    }

    /**
     * Gives the donut type
     * @return the type of donut
     */
    public String getDonutType() {
        return donutType;
    }
    /**
     * Gives the number of donuts
     * Overrides method from MenuItem class
     * @return - int representing the number of donuts
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gives the price of the donut depending on the type.
     * Overrides method from MenuItem class
     * @return the unit price
     */
    @Override
    public double price(){
        return switch (donutType) {
            case CAKE_TYPE -> CAKE_PRICE;
            case YEAST_TYPE -> YEAST_PRICE;
            case HOLES_TYPE -> HOLES_PRICE;
            default -> throw new RuntimeException("unknown type " + donutType);
        };
    }

    /**
     * Gives the flavor of the donut
     * @return String representation of the flavor of donut
     */
    public String getDonutFlavor()
    {
        return flavor;
    }

    /**
     * Gives the flavor of donut with the quantity
     * @return String representation of the donut
     */
    @Override
    public String toString() {
        return flavor + " (" + this.quantity + ")";
    }

    /**
     * Checks if two donuts are equal
     * @param obj object to compare
     * @return true if instance var of passed in object is equal to the instance var of this
     * Donut otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Donut donut = (Donut) obj;
        return quantity == donut.quantity && Objects.equals(donutType, donut.donutType) &&
                Objects.equals(flavor, donut.flavor);
    }

    /**
     * Gives the hashcode value of donut object
     * Helper method to equals method
     * @return int representing hash value
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(quantity, donutType, flavor);
    }
}