package com.example.globegatherer;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.globegatherer.NetworkManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.List;

public class Expense_analyzer extends AppCompatActivity {

    private Spinner costItemsSpinner;



    private ArrayAdapter<String> costItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_analyzer);

        // Assuming you have a list of cost items
        List<String> costItems = Arrays.asList("Food", "Travel", "Touring", "Housing", "Others");

        // Create an ArrayAdapter using the costItems list and a default spinner layout
        costItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, costItems);

        // Specify the layout to use when the list of choices appears
        costItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        costItemsSpinner = findViewById(R.id.costItemsSpinner);
        costItemsSpinner.setAdapter(costItemsAdapter);

        // Your existing code...

        // Example onClickListener for the "Save" button
        Button saveButton = findViewById(R.id.saveExpenses);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveList();
            }
        });

        // Example onClickListener for the "Show Expense" button
        Button showExpenseButton = findViewById(R.id.show_expense);
        showExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExpense();
            }
        });
    }

    // Your existing methods...

    private void saveList() {
        // Retrieve data from UI components (Destination, Estimated cost, selected cost item from Spinner)
        EditText destinationEditText = findViewById(R.id.nameDestination);
        EditText estimatedCostEditText = findViewById(R.id.estimatedCost);
        String destination = destinationEditText.getText().toString();
        String estimatedCost = estimatedCostEditText.getText().toString();
        String selectedCostItem = costItemsSpinner.getSelectedItem().toString();

        // Example: Create a JSON object for the data
        JSONObject postData = new JSONObject();
        try {
            postData.put("destination", destination);
            postData.put("estimatedCost", estimatedCost);
            postData.put("selectedCostItem", selectedCostItem);
            // Add other relevant data
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://coms-309-013.class.las.iastate.edu:8080/expense/{SignUpName}"; // Replace with your actual endpoint
        String Iusername = SharedPrefsUtil.getUsername(this);
        String Url = url.replace("{SignUpName}", Iusername);
        // Example: Make a POST request using NetworkManager
        NetworkManager.getInstance(this).sendPostRequest(postData, Url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Handle the response (e.g., update UI)
                TextView responseTextView = findViewById(R.id.responses2);
                responseTextView.setText("The response is: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(com.android.volley.VolleyError error) {
                // Handle error in the POST request (e.g., show an error message)
                TextView responseTextView = findViewById(R.id.responses2);
                responseTextView.setText("Error: " + error.toString());
            }
        });
    }

    private void showExpense() {
        // Example: Make a GET request using NetworkManager
        String url = "http://coms-309-013.class.las.iastate.edu:8080/expense/{SignUpName}"; // Replace with your actual endpoint
        String Iusername = SharedPrefsUtil.getUsername(this);
        String Url = url.replace("{SignUpName}", Iusername);
        NetworkManager.getInstance(this).sendGetRequest(Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Handle the response (e.g., update UI)
                TextView showingListsTextView = findViewById(R.id.showingLists);
                showingListsTextView.setText("Expenses: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(com.android.volley.VolleyError error) {
                // Handle error in the GET request (e.g., show an error message)
                TextView showingListsTextView = findViewById(R.id.showingLists);
                showingListsTextView.setText("Error: " + error.toString());
            }
        });
    }
}
