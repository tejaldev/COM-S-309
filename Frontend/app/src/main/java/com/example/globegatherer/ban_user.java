//package com.example.globegatherer;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//
//import org.json.JSONArray;
//
//public class ban_user extends AppCompatActivity {
//    private Button Ban, Exit, Show;
//    private TextView Banned;
//
//    private EditText Ban_username;
//
//    private NetworkManager networkManager;
//    private static String URL = "http://coms-309-013.class.las.iastate.edu:8080/admins/delete/{SignUpName}";
//
//    private static String URL_GET = "http://coms-309-013.class.las.iastate.edu:8080/persons/all";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ban_user);
//
//        Ban = findViewById(R.id.Ban_user);
//        Ban_username = findViewById(R.id.announce_text);
//        Banned = findViewById(R.id.user_ban);
//        Exit = findViewById(R.id.back);
//        Show = findViewById(R.id.get);
//
//        networkManager = NetworkManager.getInstance(this);
//
//        Show.setOnClickListener(view -> {
//            getUsers();
//        });
//
//        Ban.setOnClickListener(view -> {
//            // Get the username from the EditText
//            String username = Ban_username.getText().toString();
//
//            // Call the method to send a delete request
//            banUser(username);
//        });
//
//        Exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                openActivity();
//            }
//        });
//    }
////    public ban_user(Context context) {
////        networkManager = NetworkManager.getInstance(context);
////    }
//
//
//
//    public void openActivity(){
//        Intent intent = new Intent(this, Admin_Page.class);
//        startActivity(intent);
//    }
//
//    public void getUsers() {
////        String Iusername = SharedPrefsUtil.getUsername(this);
////        String url = URL.replace("{SignUpName}", Iusername);
//
//        networkManager.sendGetRequest(
//                URL_GET,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        // Handle the response (all the users' data)
//                        String usersData = response.toString();
//                        Banned.setText("All users: " + usersData);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle error
//                        Banned.setText("Error: " + error.toString());
//                    }
//                }
//        );
//    }
//    public void banUser(String userId) {
//
//
////        String Iusername = SharedPrefsUtil.getUsername(this);
//        String url = URL.replace("{SignUpName}", userId);
//
//        networkManager.sendDeleteRequest(
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Handle successful response (if applicable)
//                        Banned.setText("User with ID " + userId + " deleted successfully.");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle error
//                        Banned.setText("Error: " + error.toString());
//                    }
//                }
//        );
//    }
//}


package com.example.globegatherer;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.globegatherer.Admin_Page;
import com.example.globegatherer.NetworkManager;

import org.json.JSONArray;

public class ban_user extends AppCompatActivity {

    private static String URL_BAN_USER = "http://coms-309-013.class.las.iastate.edu:8080/admins/delete/{SignUpName}";
    private static String URL_GET_USERS = "http://coms-309-013.class.las.iastate.edu:8080/persons/all";

    private ProgressBar progressBar;
    private TextView textView;
    private LinearLayout usersContainer;

    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ban_user);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.user_ban);
        usersContainer = findViewById(R.id.Container);

        networkManager = NetworkManager.getInstance(this);

        Button showButton = findViewById(R.id.get);
        Button banButton = findViewById(R.id.Ban_user);
        Button exitButton = findViewById(R.id.back);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsers();
            }
        });

        banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the username from the EditText
                EditText usernameEditText = findViewById(R.id.announce_text);
                String username = usernameEditText.getText().toString();

                // Call the method to send a delete request
                banUser(username);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
    }

    private void getUsers() {
        progressBar.setVisibility(View.VISIBLE);

        networkManager.sendGetRequest(
                URL_GET_USERS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the response (all the users' data)
                        updateUsersList(response);
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        textView.setText("Error: " + error.toString());
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }

    private void updateUsersList(JSONArray users) {
        // Clear existing views
        usersContainer.removeAllViews();

        if (users.length() > 0) {
            textView.setVisibility(View.GONE);

            for (int i = 0; i < users.length(); i++) {
                try {
                    String username = users.getString(i);

                    // Inflate user item layout
                    View userItemView = LayoutInflater.from(this).inflate(R.layout.ban_item, null);

                    // Set username
                    TextView userTextView = userItemView.findViewById(R.id.userName);
                    userTextView.setText(username);

                    // Set click listener for the Ban button
                    Button banButton = userItemView.findViewById(R.id.banButton);
                    banButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle Ban button click for each user
                            // You can perform the DELETE request here
                            // Use username to identify the user for whom the button was clicked
                            try {
                                // Call the existing banUser method with the username
                                banUser(username);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    // Add user item to the container
                    usersContainer.addView(userItemView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            textView.setText("No users available.");
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void banUser(String username) {
        String url = URL_BAN_USER.replace("{SignUpName}", username);

        networkManager.sendDeleteRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle successful response (if applicable)
                        textView.setText("User with username " + username + " banned successfully.");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        textView.setText("Error: " + error.toString());
                    }
                }
        );
    }

    private void openActivity() {
        Intent intent = new Intent(this, Admin_Page.class);
        startActivity(intent);
    }
}

