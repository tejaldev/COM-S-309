package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    private static final String URL = "http://coms-309-013.class.las.iastate.edu:8080/friends/add/check/{SignUpName}";
    private static final String URL2 = "http://coms-309-013.class.las.iastate.edu:8080/persons/all";


    private ProgressDialog pDialog;

    private TextView responses;

    private Button showFriends;
    private Button myFriends;

    private EditText nameEditText;
    private EditText usernameEditText;
    private LinearLayout friendsContainer;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        responses = findViewById(R.id.responses);
//
//        addFriend = findViewById(R.id.addButton);
        showFriends=findViewById(R.id.showFriendButton);
        friendsContainer = findViewById(R.id.friendsContainer);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        myFriends= findViewById(R.id.friendSpecific);

//        nameEditText = findViewById(R.id.nameEditText);
//        usernameEditText = findViewById(R.id.usernameEditText);


//        addFriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user input
//                String name = nameEditText.getText().toString().trim();
//                String username = usernameEditText.getText().toString().trim();
//
//
//                // Check if name and username are not empty
//                if (!name.isEmpty() && !username.isEmpty()) {
//                    // Create a JSON object with user input
//                    JSONObject params = new JSONObject();
//                    try {
//                        params.put("name", name);
//                        params.put("username", username);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    postRequest(params);
//                } else {
//                    // Handle empty input fields
//                    responses.setText("Please enter both name and username.");
//                }
//            }
//        });

        showFriends.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                JSONArray parameter = new JSONArray();
                getRequest(parameter);
            }


        });

        myFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

    }

//    private void openShowFriends(){
//        Intent intent = new Intent(this, showFriend.class);
//        startActivity(intent);
//
//    }

    private void openActivity() {
        Intent intent = new Intent(this, showFriend.class);
        startActivity(intent);
    }
    private void getRequest(JSONArray parameter) {
        progressBar.setVisibility(View.VISIBLE);

//        String Iusername = SharedPrefsUtil.getUsername(this);
//        String url = URL2.replace("{SignUpName}", Iusername);

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.GET, URL2, parameter, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                updateFriendList(response);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                textView.setText("Error: " + error.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });

        Volley.newRequestQueue(this).add(jsonObjReq);
    }

    private void updateFriendList(JSONArray friends) {
        // Clear existing views
        friendsContainer.removeAllViews();

        if (friends.length() > 0) {
            textView.setVisibility(View.GONE);

            for (int i = 0; i < friends.length(); i++) {
                try {
                    String name = friends.getString(i);

                    // Inflate friend item layout
                    View friendItemView = LayoutInflater.from(this).inflate(R.layout.friend_item, null);

                    // Set friend name
                    TextView friendTextView = friendItemView.findViewById(R.id.friendName);
                    friendTextView.setText(name);

                    // Set click listener for the Add button
                    Button addButton = friendItemView.findViewById(R.id.addButton);
                    addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle Add button click for each friend
                            // You can perform the POST request here
                            // Use friendName to identify the friend for whom the button was clicked
                            try {
                                // Create a JSONObject with the necessary parameters for the POST request
                                JSONObject params = new JSONObject();
                                params.put("name", name);

                                // Call the existing postRequest method with the JSONObject
                                postRequest(params);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                    // Add friend item to the container
                    friendsContainer.addView(friendItemView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            textView.setText("No friends available.");
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void postRequest(JSONObject params) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        String Iusername = SharedPrefsUtil.getUsername(this);
        String url = URL.replace("{SignUpName}", Iusername);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
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
