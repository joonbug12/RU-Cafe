package com.example.rucafe;
import java.util.ArrayList;

/**
 * Extension of the menu item class that stores information about sandwiches
 * @author Connor Powell, Joon Song
 */
public class Sandwich extends MenuItem{
    /*
     * 3 different bread (bagel, sourdough, wheat bread) doesnt change price
     * beef sandwich is 10.99
     * chicken sandwich is 8.99
     * fish sandwich is 9.99
     * customers can use addons(cheese, lettuce, tomatoes, and onions) 30 cents each
     * cheese add on is 1.00
     *
     */
    private String bread;
    private Meat meat;
    private ArrayList<String> addons;
    private int amount;

    private final double CHEESE_PRICE = 1;
    private final double ADDON_PRICE = .3;

    /**
     * Constructor that sets the values for the instance variables bread, meat, and addons
     * @param bread bread
     * @param meat meat
     * @param addons addons
     */
    public Sandwich(String bread, Meat meat, ArrayList<String> addons) {
        this.bread = bread;
        this.meat = meat;
        this.addons = addons;
    }

    /**
     * Default constructor for the sandwich
     */
    public Sandwich(){
        this.addons = new ArrayList<String>();
        this.bread="Bagel";
    }

    /**
     * price calculator
     * @return price 2 decimal places
     */
    @Override
    public double price() {
        if(meat!=null){
            return meat.getPrice() + addonPrice();
        }else{
            return addonPrice();
        }
    }

    /**
     * Gets the quantity of sandwiches
     * @return 1
     */
    @Override
    public int getQuantity() {
        return 1;
    }

    /**
     * Calculates the price of all the addons
     * @return double representing the price of the addons
     */
    private double addonPrice() {
        double tr = 0.0;
        for(String item : addons) {
            if(item.equals("cheese"))
                tr += CHEESE_PRICE;
            else
                tr += ADDON_PRICE;
        }
        return tr;
    }

    /**
     * Gets the bread type
     * @return String of the bread type
     */
    public String getBread() {
        return bread;
    }

    /**
     * Sets the bread type
     * @param bread type to set it to
     */
    public void setBread(String bread) {
        this.bread = bread;
    }

    /**
     * Gets the meat type
     * @return Meat type
     */
    public Meat getMeat() {
        return meat;
    }

    /**
     * Sets the meat type
     * @param meat type to set it to
     */
    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    /**
     * Gets the addons of the sandwich
     * @return list of addons
     */
    public ArrayList<String> getAddons() {
        return addons;
    }

    /**
     * Adds the specified addon to the sandwich
     * @param addon to add
     * @return true if addon was added successfully, false otherwise
     */
    public boolean add(String addon) {
        if(!addons.contains(addon)) {
            addons.add(addon);
            return true;
        }
        return false;
    }

    /**
     * Removes specified addon from the sandwich
     * @param addon to remove
     * @return true if addon was removed successfully, false otherwise
     */
    public boolean remove(String addon) {
        if(addons.contains(addon)) {
            addons.remove(addon);
            return true;
        }
        return false;
    }

    /**
     * Checks if 2 sandwiches are equal to each other
     * @param obj object to compare
     * @return true if the 2 objects are equal, false othewise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Sandwich) {
            Sandwich temp = (Sandwich) obj;
            return this.addons.equals(temp.addons) && this.amount == temp.amount && this.bread.equals(temp.bread) && this.meat == temp.meat;
        }
        return false;
    }

    /**
     * Generates a string for the sandwich with the meat type, bread type, amount, and addons
     * @return String representation of the sandwich
     */
    @Override
    public String toString() {
        if(!addons.isEmpty())
            return "Sandwich " + meat.getType() + " " + bread + " (" + getQuantity() + ") " + addons;
        return "Sandwich " + meat.getType() + " " + bread + " (" + getQuantity() + ")";
    }
}