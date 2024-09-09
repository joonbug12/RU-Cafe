package com.example.rucafe;

import static com.example.rucafe.Donut.CAKE_PRICE;
import static com.example.rucafe.Donut.YEAST_PRICE;
import static com.example.rucafe.MainActivity.addToOrder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Lily Chang
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{

    private Context context;
    private ArrayList<Item> items;

    public ItemsAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rowlayout, parent, false);
        return new ItemsAdapter.ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        holder.tv_name.setText(items.get(position).getItemName());
        holder.tv_price.setText("Unit Price: $" + items.get(position).getUnitPrice());
        holder.im_item.setImageResource(items.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {return items.size();}

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        /**
         * Constructor to initialize the instance variables.
         * @param itemView
         */
        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            setAddButtonOnClick(itemView);
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add To Order?");
                    alert.setMessage(tv_price.getText().toString());
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), "Donut Added to Order", Toast.LENGTH_LONG).show();
                            addToOrder(createDonut());
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }

        /**
         * create donut
         * @return donut instance
         */
        public Donut createDonut(){
            String flavor = tv_name.getText().toString();
            if(Double.parseDouble(tv_price.getText().toString().substring(13))
                    == YEAST_PRICE){
                return new Donut(1, "Yeast Donut", flavor);
            }else if(Double.parseDouble(tv_price.getText().toString().substring(13))
                    == CAKE_PRICE){
                return new Donut(1, "Cake Donut", flavor);
            }else{
                return new Donut(1, "Donut Hole", flavor);
            }
        }
    }
}
