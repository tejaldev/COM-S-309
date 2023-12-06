package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class toDoList extends AppCompatActivity {

    private Button save;
    private static final String TAG = toDoList.class.getSimpleName();
    private static final String URL3 = "http://coms-309-013.class.las.iastate.edu:8080/TravelToDo/{SignUpName}";


    private ProgressDialog pDialog;

    private TextView responses2;
    private TextView showingList;

    private Button showingListButtons;
//    private Button Expenses;

    private EditText nameEditTextDestinations;
    private EditText countryEditTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        responses2 = findViewById(R.id.responses2);
//        showingList = findViewById(R.id.showingLists);
        showingListButtons  = findViewById(R.id.showListButton);
        save = findViewById(R.id.saveList);
//        Expenses = findViewById(R.id.Expense);

        nameEditTextDestinations = findViewById(R.id.nameEditTextDestination);
        countryEditTexts = findViewById(R.id.countryEditText);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String name = nameEditTextDestinations.getText().toString().trim();
                String username = countryEditTexts.getText().toString().trim();

                // Check if name and username are not empty
                if (!name.isEmpty() && !username.isEmpty()) {
                    // Create a JSON object with user input
                    JSONObject params = new JSONObject();
                    try {
                        params.put("destinationName", name);
                        params.put("country", username);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    postRequest(params);
                } else {
                    // Handle empty input fields
                    responses2.setText("Please enter both name and username.");
                }
            }
        });

//        Expenses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openActivity();
//            }
//        });

        showingListButtons.setOnClickListener(new View.OnClickListener() {
//            JSONArray parameter= new JSONArray();
//
//            @Override
//            public void onClick(View view) {
//                getRequest(parameter);
//            }


                @Override
                public void onClick(View view) {
                    openShowToDo();
                }




        });



    }
    public void openActivity(){
        Intent intent = new Intent(this, Expense_analyzer.class);
        startActivity(intent);
    }

    private void openShowToDo(){
        Intent intent = new Intent(this, showToDo.class);
        startActivity(intent);

    }

    private void postRequest(JSONObject params) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        String Iusername = SharedPrefsUtil.getUsername(this);
        String url = URL3.replace("{SignUpName}", Iusername);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                responses2.setText(response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                responses2.setText("Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {
            //            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                // Your header logic here
//                HashMap<String, String> headers = new HashMap<>();
//                // Add any necessary headers here
//                // Example: headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                // Example: headers.put("Content-Type", "application/json");
//                return headers;
//            }
            {
                setShouldCache(false); // Disable caching for debugging
                VolleyLog.DEBUG = true;
            }
        };



        // Adding the request to the request queue
        Volley.newRequestQueue(this).add(jsonObjReq);
    }




}
