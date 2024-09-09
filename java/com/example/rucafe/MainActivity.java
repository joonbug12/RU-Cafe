package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * main activity class
 * @author Joon Song, Connor Powell
 */
public class MainActivity extends AppCompatActivity {
    private ImageView donuts, coffee, sandwich, order, ordersList;
    public static Order orderCart;
    public static int orderNumber;
    public static ArrayList<String> orderList;

    /**
     Initializes the elements and their effects.
     @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderNumber = 1;
        orderCart = new Order(orderNumber);
        orderList = new ArrayList<>();
        donuts = findViewById(R.id.imageView);
        donuts.setOnClickListener(v -> openCoffeeView());
        coffee = findViewById(R.id.imageView2);
        coffee.setOnClickListener(v -> openDonutView());
        sandwich = findViewById(R.id.imageView3);
        sandwich.setOnClickListener(v -> openSandwichView());
        order = findViewById(R.id.imageView4);
        order.setOnClickListener(v -> openCartView());
        ordersList = findViewById(R.id.imageView5);
        ordersList.setOnClickListener(v -> openOrdersView());
    }
    /**
     open donut activity.
     */
    public void openDonutView(){
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }
    /**
     open coffee activity.
     */
    public void openCoffeeView(){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }
    /**
     * open sandwich activity
     */
    public void openSandwichView(){
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }
    /**
     open order activity.
     */
    public void openCartView(){
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
    /**
     open orders list activity.
     */
    public void openOrdersView(){
        Intent intent = new Intent(this, OrderListActivity.class);
        startActivity(intent);
    }
    /**
     Adds item to the order.
     */
    public static void addToOrder(MenuItem toAdd){
        orderCart.addItem(toAdd);
    }

    /**
     Removes item from the order.
     @param toRemove the item to be removed
     */
    public static void removeFromOrder(MenuItem toRemove){
        orderCart.removeItem(toRemove);
    }

    /**
     Returns the virtual shopping cart.
     @return the list with the current order
     */
    public static Order getCart(){
        return orderCart;
    }

    /**
     Returns the list of orders.
     @return an ArrayList of separate orders
     */
    public static ArrayList<String> getOrders(){
        return orderList;
    }

    /**
     Removes an order from the store.
     @param toRemove order to be removed
     */
    public static void removeFromListofOrders(String toRemove){
        orderList.remove(toRemove);
    }

    /**
     Places an order to the store.
     @param price price
     */
    public static void placeOrder(String price){
        String order = "Order #" + orderNumber + ":\n";
        for(int i = 0; i < orderCart.getItemList().size(); i++){
            order += orderCart.getItemList().get(i).toString() + "\n";
        }
        order += "Total Price: " + price;
        orderList.add(order);
        orderNumber++;
        orderCart = new Order(orderNumber);
    }
}