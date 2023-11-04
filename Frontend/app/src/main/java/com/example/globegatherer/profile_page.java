package com.example.globegatherer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class profile_page extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private Button Edit;
    private Button ToDo, Calendar;
    private Button Friend;
    private EditText description;
    private Button logout;
    private TextView Des_Response;
    private NetworkManager networkManager;
    private String username;
    private ImageView Announcements;

    private static final String URL_JSON_OBJECT = "http://coms-309-013.class.las.iastate.edu:8080/description/add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        // Initialize your elements
        Edit = findViewById(R.id.edit);
        description = findViewById(R.id.Description);
        logout = findViewById(R.id.logout);
        ToDo = findViewById(R.id.To_do_button);
        Friend = findViewById(R.id.Friends);
        Calendar = findViewById(R.id.calendar_open);

        Des_Response = findViewById(R.id.Des_Response);
        Announcements = findViewById(R.id.announcements);


        networkManager = NetworkManager.getInstance(this);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Set up a listener to handle item selection
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int itemId = item.getItemId();
                if (itemId == R.id.menu_friends) {
                    openFriendsActivity();
                    return true;
                } else if (itemId == R.id.menu_chat) {
                    openChatActivity();
                    return true;
                } else if (itemId == R.id.menu_todo) {
                    openTodoListActivity();
                    return true;
                }else if (itemId == R.id.menu_map) {
                    openmapPage();
                    return true;
                }else if (itemId == R.id.menu_history) {
                    opentravelHistory();
                    return true;
                }
                return false;
            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReq();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        ToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity5();
            }
        });

        Friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });

        Announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity6();
            }
        });

        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity7();
            }
        });

        username = getIntent().getStringExtra("USERNAME");
        if (username != null) {
            Log.d("Username", username); // Verify if the username is received correctly

            // Pass the username along with the description to the server
            Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeJsonObjReq();
                }
            });
        }
    }

    private void openFriendsActivity() {
        Intent intent = new Intent(this, homePage.class);
        startActivity(intent);
    }

    private void openChatActivity() {
        Intent intent = new Intent(this, chatpage.class);
        startActivity(intent);
    }

    private void openTodoListActivity() {
        Intent intent = new Intent(this, toDoList.class);
        startActivity(intent);
    }

    public void openActivity6(){
        Intent intent = new Intent(this, Announcements_admin.class);
        startActivity(intent);
    }
    public void openActivity7(){
        Intent intent = new Intent(this, Calendar_Page.class);
        startActivity(intent);
    }


    private void makeJsonObjReq() {
        // Creating a JSON object with the user's description
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("description", description.getText().toString());
            requestData.put("username", username); // Add the username to the request

        } catch (Exception e) {
            e.printStackTrace();
        }

        networkManager.sendPostRequest(
                requestData,
                URL_JSON_OBJECT,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Volley Response", response.toString());
                            Des_Response.setText(response.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        );
    }
}
