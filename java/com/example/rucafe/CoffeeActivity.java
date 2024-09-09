package com.example.rucafe;

import static com.example.rucafe.MainActivity.addToOrder;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

/**
 * this controlls what goes on in the coffee order screen
 * @author Joon Song, Connor Powell
 */
public class CoffeeActivity extends AppCompatActivity{
    private TextView subtotal;
    private Button addtoOrder;
    private RadioGroup sizes;
    private RadioButton tall, shorty,  grande, venti;
    private Spinner quantity;
    private CheckBox sweetCream, frenchVanilla, irishCream, caramel, mocha;
    private String[] quantities= {"1","2","3","4","5"};
    private ArrayAdapter<String> adapter;

    /**
     Initializes the elements
     @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_activity);
        View.OnClickListener update = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runningTotal();
            }
        };
        sizes = findViewById(R.id.radioGroup);
        shorty = findViewById(R.id.shorty);
        shorty.setOnClickListener(update);
        tall = findViewById(R.id.tall);
        tall.setOnClickListener(update);
        grande = findViewById(R.id.grande);
        grande.setOnClickListener(update);
        venti = findViewById(R.id.venti);
        venti.setOnClickListener(update);
        subtotal = findViewById(R.id.subtotalField);
        sweetCream = findViewById(R.id.sweetCream);
        sweetCream.setOnClickListener(update);
        frenchVanilla = findViewById(R.id.frenchVanilla);
        frenchVanilla.setOnClickListener(update);
        irishCream = findViewById(R.id.irishCream);
        irishCream.setOnClickListener(update);
        caramel = findViewById(R.id.caramel);
        caramel.setOnClickListener(update);
        mocha = findViewById(R.id.mocha);
        mocha.setOnClickListener(update);
        quantity = findViewById(R.id.sizes);
        adapter = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantities);
        quantity.setAdapter(adapter);
        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                runningTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                runningTotal();
            }
        });
        addtoOrder = findViewById(R.id.addToOrderButton);
        addtoOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoffeeActivity.this,
                        getString(R.string.addCoffee), Toast.LENGTH_SHORT).show();
                addToOrder(createCoffee());
            }
        });
        runningTotal();
    }
    /**
     Updates the price
     */
    public void runningTotal(){
        DecimalFormat df = new DecimalFormat("##,###.00");
        String currentPrice = df.format(createCoffee().price());
        subtotal.setText("Subtotal: $" + currentPrice);
    }

    /**
     Creates a coffee object.
     */
    public Coffee createCoffee(){
        RadioButton cup = findViewById(sizes.getCheckedRadioButtonId());
        String coffeeSize = (String)cup.getText();
        Spinner num = findViewById(R.id.sizes);
        int coffeeQuantity = Integer.parseInt((String)num.getSelectedItem());
        boolean sweetCream = this.sweetCream.isChecked();
        boolean frenchVanilla = this.frenchVanilla.isChecked();
        boolean irishCream = this.irishCream.isChecked();
        boolean caramel = this.caramel.isChecked();
        boolean mocha = this.mocha.isChecked();
        return new Coffee(coffeeSize, coffeeQuantity, sweetCream,
                frenchVanilla, irishCream, caramel, mocha);
    }

}
