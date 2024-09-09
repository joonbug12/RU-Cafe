package com.example.rucafe;

import static com.example.rucafe.MainActivity.getCart;
import static com.example.rucafe.MainActivity.removeFromOrder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private static final double SALES_TAX = .06625;
    private Button placeOrder;
    private TextView subtotal, salesTax, totalPrice;
    private ListView cartList;
    private ArrayList<MenuItem> itemList;
    private ArrayAdapter<MenuItem> adapter;
    private String orderPrice;

    /**
     Initializes the elements and their effects.
     @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        placeOrder = findViewById(R.id.placeOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOrder();
            }
        });
        subtotal = findViewById(R.id.cartSubtotal);
        salesTax = findViewById(R.id.cartTax);
        totalPrice = findViewById(R.id.cartTotal);
        cartList = findViewById(R.id.cartList);
        itemList = getCart().getItemList();
        adapter = new ArrayAdapter<MenuItem>(this, androidx.appcompat.R.layout.
                support_simple_spinner_dropdown_item, itemList);
        cartList.setOnItemClickListener(this::onItemClick);
        cartList.setAdapter(adapter);
        displayAmounts();
    }

    /**
     Creates an Alert Dialog upon clicking an item.
     @param adapterView
     @param view
     @param i
     @param l
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Item?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                removeFromOrder((MenuItem) adapterView.getAdapter().getItem(i));
                adapter = new ArrayAdapter<MenuItem>(OrderActivity.this, androidx.appcompat.R.layout.
                        support_simple_spinner_dropdown_item, itemList);
                cartList.setAdapter(adapter);
                displayAmounts();
                Toast.makeText(OrderActivity.this,
                        getString(R.string.itemDeleted), Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    /**
     Places the order.
     */
    public void clickOrder(){
        if(itemList.size() > 0){
            MainActivity.placeOrder(orderPrice);
            for(int i = itemList.size()-1; i >= 0; i--){
                itemList.remove(i);
            }
            adapter = new ArrayAdapter<MenuItem>(this, androidx.appcompat.R.layout.
                    support_simple_spinner_dropdown_item, itemList);
            cartList.setAdapter(adapter);
            displayAmounts();
            Toast.makeText(OrderActivity.this,
                    getString(R.string.placeOrder), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(OrderActivity.this,
                    getString(R.string.emptyCart), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     Displays the running subtotal, tax, and total.
     */
    protected void displayAmounts(){
        DecimalFormat df = new DecimalFormat("##,###.00");
        double price = 0;
        for(int i = 0; i < itemList.size(); i++){
            price += itemList.get(i).price();
        }
        subtotal.setText("Subtotal: $" + df.format(price));
        salesTax.setText("Sales Tax: $" + df.format(price*SALES_TAX));
        orderPrice = "$" + df.format(price+(price*SALES_TAX));
        totalPrice.setText("Total: " + orderPrice);
    }
}
