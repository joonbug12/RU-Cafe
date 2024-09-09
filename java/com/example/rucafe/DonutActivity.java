package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

/**
 * class for the donut activity
 * @author Joon Song, Connor Powell
 */
public class DonutActivity extends AppCompatActivity{
    private RecyclerView flavors;
    private ArrayList<Item> items;

    /**
     Initializes the elements and their effects.
     @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_activity);
        flavors = findViewById(R.id.flavors);
        items = new ArrayList<>();
        createFlavorList();
        ItemsAdapter adapter = new ItemsAdapter(this, items);
        flavors.setAdapter(adapter);
        flavors.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     Populates the ArrayList with items.
     */
    public void createFlavorList(){
        String[] flavors = getResources().getStringArray(R.array.flavorList);
        int[] itemImages = {R.drawable.bostoncream, R.drawable.chocolateglaze, R.drawable.vanillaglaze,
                R.drawable.strawberryjelly, R.drawable.plain, R.drawable.oreo, R.drawable.redvelvet,
                R.drawable.vanilla, R.drawable.blueberry, R.drawable.chocolatehole, R.drawable.glazehole,
                R.drawable.powderedsugarhole};
        String[] itemPrices = getResources().getStringArray(R.array.donutPrices);
        for(int i = 0; i < flavors.length; i++) {
            items.add(new Item(flavors[i], itemImages[i], itemPrices[i]));
        }
    }
}
