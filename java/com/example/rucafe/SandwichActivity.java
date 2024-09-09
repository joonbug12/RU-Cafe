package com.example.rucafe;

import static com.example.rucafe.MainActivity.addToOrder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class SandwichActivity extends AppCompatActivity {
    private TextView sandwichSubTotal;
    private Button addSandwichToOrder;
    private RadioGroup bread, meat;
    private RadioButton bagel, wheattoast, sourdough, beef, fish, chicken;
    private CheckBox lettuce, tomato, cheese, other;

    /**
     Initializes the elements .
     @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sandwich_activity);
        View.OnClickListener update = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runningTotalSandwich();
            }
        };
        bread = findViewById(R.id.bread);
        bagel = findViewById(R.id.bagel); bagel.setOnClickListener(update);
        wheattoast = findViewById(R.id.wheattoast); wheattoast.setOnClickListener(update);
        sourdough = findViewById(R.id.sourdough); sourdough.setOnClickListener(update);
        sandwichSubTotal = findViewById(R.id.sandwichSubtotal);
        meat = findViewById(R.id.meat);
        beef = findViewById(R.id.beef); beef.setOnClickListener(update);
        chicken = findViewById(R.id.chicken); chicken.setOnClickListener(update);
        fish = findViewById(R.id.fish); fish.setOnClickListener(update);
        lettuce = findViewById(R.id.lettuce); lettuce.setOnClickListener(update);
        tomato = findViewById(R.id.tomato); tomato.setOnClickListener(update);
        cheese = findViewById(R.id.cheese); cheese.setOnClickListener(update);
        other = findViewById(R.id.other); other.setOnClickListener(update);
        addSandwichToOrder = findViewById(R.id.addSandwichToOrderButton);
        addSandwichToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SandwichActivity.this,
                        "Sandwich added to Order!", Toast.LENGTH_SHORT).show();
                addToOrder(createSandwich());
            }
        });
        runningTotalSandwich();
    }
    /**
     Updates the price
     */
    @SuppressLint("SetTextI18n")
    public void runningTotalSandwich(){
        DecimalFormat df = new DecimalFormat("##,###.00");
        String currentPrice = df.format(createSandwich().price());
        sandwichSubTotal.setText("$" + currentPrice);
    }

    /**
     Creates a sandwich object.
     */
    public Sandwich createSandwich(){
        Sandwich newSandwich = new Sandwich();
        RadioButton meatType = findViewById(meat.getCheckedRadioButtonId());
        String meatTypeString = null;
        if (meatType != null) {meatTypeString = meatType.getText().toString();}
        RadioButton breadType = findViewById(bread.getCheckedRadioButtonId());
        String breadTypeString = null;
        if (breadType != null) {breadTypeString = breadType.getText().toString();}
        newSandwich.setBread(breadTypeString);
        if (meatTypeString != null){
            switch (meatTypeString){
                case "Beef": newSandwich.setMeat(Meat.BEEF); break;
                case "Fish": newSandwich.setMeat(Meat.FISH); break;
                case "Chicken": newSandwich.setMeat(Meat.CHICKEN); break;
            }
        }
        boolean lettuceSelected = this.lettuce.isChecked();
        boolean tomatoSelected = this.tomato.isChecked();
        boolean cheeseSelected = this.cheese.isChecked();
        boolean otherSelected = this.other.isChecked();
        if(lettuceSelected) {newSandwich.add("lettuce");}
        if(tomatoSelected) {newSandwich.add("tomato");}
        if(cheeseSelected) {newSandwich.add("cheese");}
        if(otherSelected) {newSandwich.add("other");}
        return newSandwich;
    }
}
