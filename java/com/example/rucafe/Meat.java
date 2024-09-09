package com.example.rucafe;
/**
 * Enum class for the meat that contains the meat type and price
 * @author Connor Powell, Joon Song
 */
public enum Meat{
    BEEF("Beef",10.99),
    CHICKEN("Chicken",8.99),
    FISH("Fish",9.99);

    private final String type;
    private final double price;

    /**
     * Constructor for the meat class
     * @param type of meat
     * @param price of the meat
     */
    Meat(String type, double price) {
        this.type = type;
        this.price = price;
    }

    /**
     * Getter method for the meat
     * @return String for the type of meat
     */
    public String getType() {
        return type;
    }

    /**
     * Getter method
     * @return double for the price
     */
    public double getPrice() {
        return price;
    }
}