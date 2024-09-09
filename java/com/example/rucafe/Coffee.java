package com.example.rucafe;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Extension of the menu item class that contains information about coffee items
 * @author Connor Powell, Joon Song
 */
public class Coffee extends MenuItem {
    /*
     * 4 different sizes (short, tall, grande, venti)
     * black coffee is the default coffee.
     * Addons to a black coffee are sweet cream, french vanilla, irish cream, caramel, and mocha. Each cost 30 cents
     * base price for short black coffee is 1.99, and it goes up 50 cents per size
     */

    private Set<String> addIns = new HashSet<>();
    private String size = TALL;
    private int quantity = 1;
    private boolean sweetCream, frenchVanilla, irishCream, caramel, mocha;
    private static final double TALL_PRICE = 2.49;
    private static final double SHORT_PRICE = 1.99;
    private static final double GRANDE_PRICE = 2.99;
    private static final double VENTI_PRICE = 3.49;
    private static final double ADDIN_PRICE = 0.30;

    public static final String TALL = "Tall";
    public static final String SHORT = "Short";
    public static final String GRANDE = "Grande";
    public static final String VENTI = "Venti";

    public static final String[] SIZES = new String[]{TALL, SHORT, GRANDE, VENTI};

    /**
     Constructor to initialize the instance variables.
     @param size the size
     @param quantity the number of coffees ordered
     @param sweetCream sweet cream
     @param frenchVanilla french vanilla
     @param irishCream irish cream
     @param caramel caramel
     @param mocha mocha
     */
    public Coffee(String size, int quantity, boolean sweetCream, boolean frenchVanilla,
                  boolean irishCream, boolean caramel, boolean mocha){
        this.size = size;
        this.quantity = quantity;
        this.sweetCream = sweetCream;
        this.frenchVanilla = frenchVanilla;
        this.irishCream = irishCream;
        this.caramel = caramel;
        this.mocha = mocha;
    }

    /**
     * Constructor that creates a Coffee object based off the given Coffee object
     * It assigns the values to the instance variables quantity, size, and addIns
     * @param copy of a Coffee object
     */
    public Coffee(Coffee copy){
        this.quantity = copy.quantity;
        this.size = copy.size;
        addIns.addAll(copy.addIns);
    }

    /**
     * Empty constructor
     */
    public Coffee(){

    }

    /**
     * Sets the size type for a coffee object based on passed in string
     * @param size of the copy
     */
    public void setSize(String size){this.size = size;}

    /**
     * Sets the size type for a coffee object based on passed in string
     * @param quantity of coffee in the order
     */
    public void setQuantity(int quantity){this.quantity = quantity;}

    /**
     * Checks if the passed in object is an instance of String or not and adds to addIns set
     * @param obj to add
     * @return true if addIn has been added, false otherwise.
     */
    public void add(Object obj) {
        if(obj instanceof String) {
            String addin = (String) obj;
            addIns.add(addin);
        }
    }

    /**
     * Checks if the passed in object is an instance of String or not and removes from addIns set
     * @param obj object to remove
     */
    public void remove(Object obj){
        if(obj instanceof String) {
            String addin = (String) obj;
            addIns.remove(addin);
        }
    }

    /**
     * Gives the price of the coffee depending on size and addins.
     * Overrides method from MenuItem class
     * @return the price
     */
    @Override
    public double price(){return (getSizePrice() + addInPrice()) * quantity;}

    /**
     * Gives the price corresponding to the size.
     * Helper for price method
     * @return double representing the size price
     */
    private double getSizePrice(){
        return switch (size) {
            case TALL -> TALL_PRICE;
            case SHORT -> SHORT_PRICE;
            case GRANDE -> GRANDE_PRICE;
            case VENTI -> VENTI_PRICE;
            default -> throw new RuntimeException("unknown size " + size);
        };
    }

    /**
     * add in price
     */
    private double addInPrice(){
        double sum = 0.0;
        if(this.sweetCream) {sum+=ADDIN_PRICE;}
        if(this.frenchVanilla) {sum+=ADDIN_PRICE;}
        if(this.mocha) {sum+=ADDIN_PRICE;}
        if(this.irishCream) {sum+=ADDIN_PRICE;}
        if(this.caramel) {sum+=ADDIN_PRICE;}
        return sum;
    }

    /**
     * Gives the number of coffees
     * Overrides method from MenuItem class
     * @return the quantity of coffee items
     */
    @Override
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Gives the size type of coffee with the quantity and addins if there are any
     * @return String representation of the coffee
     */
    @Override
    public String toString(){
        if(addIns.isEmpty()) return "Coffee " + size + " (" + this.quantity + ")";
        return "Coffee " + size + " (" + this.quantity + ")" + " " + addIns;
    }

    /**
     * Checks if two Coffee objects are equal
     * @param obj object to be compared
     * @return true if instance var of passed in object is equal to the instance var of this
     * Coffee otherwise false.
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Coffee coffee = (Coffee) obj;
        return quantity == coffee.quantity && Objects.equals(addIns, coffee.addIns) &&
                Objects.equals(size, coffee.size);
    }

    /**
     * Gives the hashcode value of the coffee object
     * Helper method for equals method
     * @return int representing hash value of the coffee
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(addIns, size, quantity);
    }
}