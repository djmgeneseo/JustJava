/**
 * Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

        /**
         * This method is called when the decrement button is clicked.
         */
    public void decrement(View view) {
        if(quantity>0){quantity--; displayQuantity(quantity);}
        else {displayQuantity(quantity);}
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary(calculatePrice(quantity, 5)));
    }

    /**
     * This method calculates the price of a coffee purchase
     *
     * @param numberOfCups = number of cups of coffee
     * @param pricePerCup = the price of a cup of coffee
     */
    private int calculatePrice(int numberOfCups, int pricePerCup) {
        return numberOfCups*pricePerCup;
    }

    /**
     * This method takes in a price and prints a report of the individual's
     * name, number of cups of coffee, the total price, and a thank you message
     *
     * @param price = number of cups of coffee
     */
    private String createOrderSummary(int price) {
        String orderSummary = "Name: Rodger Federer";
        orderSummary = orderSummary + "\nQuantity: " + quantity;
        orderSummary = orderSummary + "\nTotal: $" + price;
        orderSummary = orderSummary + "\nThank you!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}