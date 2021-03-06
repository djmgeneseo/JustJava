/**
 * Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=1;

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
    } // increment()

        /**
         * This method is called when the decrement button is clicked.
         */
    public void decrement(View view) {
        if(quantity>0){quantity--; displayQuantity(quantity);}
        else {displayQuantity(quantity);}
    } // decrement()


    /**
     * This method is called when the order button is clicked.
     */
    public void checkoutCart(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.nameField);
        final String name = nameEditText.getText().toString();

        final String priceMessage = (createOrderSummary(calculatePrice(quantity, 5, hasWhippedCream, hasChocolate),
                        hasWhippedCream, hasChocolate, name));

        displayMessage(priceMessage);

        // Show 'ORDER' button
        View order = findViewById(R.id.orderButton);
        order.setVisibility(View.VISIBLE);

        /** Clicking the 'ORDER' button opens email app and sends the order summary
         *  into the textbox, and adds a subject that includes the user's name
         * */
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                // intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
                intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    } // checkoutCart()


    /**
     * This method calculates the price of a coffee purchase
     *
     * @param numberOfCups = number of cups of coffee
     * @param pricePerCup = the price of a cup of coffee
     */
    private int calculatePrice(int numberOfCups, int pricePerCup, boolean hasWhippedCream, boolean hasChocolate) {
        int price = numberOfCups*pricePerCup;

        if (hasWhippedCream) {price++;}
        if (hasChocolate) {price +=2;}
        return price;
    } // calculatePrice()


    /**
     * This method takes in a price and prints a report of the individual's
     * name, number of cups of coffee, the total price, and a thank you message
     *
     * @param price = number of cups of coffee
     */
    private String createOrderSummary(int price,  boolean hasWhippedCream, boolean hasChocolate, String name) {
        String orderSummary = "Name: " + name;
        orderSummary = orderSummary + "\nAdd whipped cream? ";
        if (hasWhippedCream) {orderSummary +="Yes!";} else {orderSummary += "No";}
        orderSummary = orderSummary + "\nAdd chocolate? ";
        if (hasChocolate) {orderSummary +="Yes!";} else {orderSummary += "No";}
        orderSummary = orderSummary + "\nQuantity: " + quantity;
        orderSummary = orderSummary + "\nTotal: $" + price;

        return orderSummary;
    } // createOrderSummary()


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    } // displayQuantity()


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        // Show 'Order Summary' View
        View orderSummary = findViewById(R.id.orderSummary);
        orderSummary.setVisibility(View.VISIBLE);

        // Show 'Order Summary Text' View
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
        priceTextView.setVisibility(View.VISIBLE);
    } // displayMessage()
}