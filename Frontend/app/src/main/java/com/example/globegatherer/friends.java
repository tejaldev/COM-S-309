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

public class friends extends AppCompatActivity {

    private Button addFriend;
    private static final String TAG = friends.class.getSimpleName();
    private static final String URL = "http://coms-309-013.class.las.iastate.edu:8080/friends/add";


    private ProgressDialog pDialog;

    private TextView responses;

    private Button showFriends;


    private EditText nameEditText;
    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        responses = findViewById(R.id.responses);

        addFriend = findViewById(R.id.addFriendButton);
        showFriends=findViewById(R.id.showFriendButton);

        nameEditText = findViewById(R.id.nameEditText);
        usernameEditText = findViewById(R.id.usernameEditText);


        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String name = nameEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();

                // Check if name and username are not empty
                if (!name.isEmpty() && !username.isEmpty()) {
                    // Create a JSON object with user input
                    JSONObject params = new JSONObject();
                    try {
                        params.put("name", name);
                        params.put("username", username);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    postRequest(params);
                } else {
                    // Handle empty input fields
                    responses.setText("Please enter both name and username.");
                }
            }
        });

        showFriends.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openShowFriends();
            }


        });

    }

    private void openShowFriends(){
        Intent intent = new Intent(this, showFriend.class);
        startActivity(intent);

    }


    private void postRequest(JSONObject params) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                responses.setText(response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                responses.setText("Error: " + error.getMessage());
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
