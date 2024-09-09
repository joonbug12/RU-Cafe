package com.example.rucafe;

import static com.example.rucafe.MainActivity.getOrders;
import static com.example.rucafe.MainActivity.removeFromListofOrders;
import static com.example.rucafe.MainActivity.removeFromOrder;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity{
    private ListView listOfOrders;
    private ArrayList<String> orderList;
    private ArrayAdapter<String> adapter;

    /**
     Initializes the elements and their effects.
     @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderlist_activity);
        listOfOrders = findViewById(R.id.listOfOrders);
        orderList = getOrders();
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.
                support_simple_spinner_dropdown_item, orderList);
        listOfOrders.setOnItemClickListener(this::onItemClick);
        listOfOrders.setAdapter(adapter);
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
                removeFromListofOrders((String) adapterView.getAdapter().getItem(i));
                adapter = new ArrayAdapter<String>(OrderListActivity.this, androidx.appcompat.R.layout.
                        support_simple_spinner_dropdown_item, orderList);
                listOfOrders.setAdapter(adapter);
                Toast.makeText(OrderListActivity.this,
                        getString(R.string.orderDeleted), Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
