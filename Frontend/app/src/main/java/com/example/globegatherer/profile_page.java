package com.example.globegatherer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText description;
    private Button logout;
    private TextView Des_Response;
    private NetworkManager networkManager;

    private static final String URL_JSON_OBJECT = "http://coms-309-013.class.las.iastate.edu:8080/description/add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        // Initialize your elements
        Edit = findViewById(R.id.edit);
        description = findViewById(R.id.Description);
        logout = findViewById(R.id.logout);
        Des_Response = findViewById(R.id.Des_Response);
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

    private void openmapPage() {
        Intent intent = new Intent(this, mapPage.class);
        startActivity(intent);
    }

    private void opentravelHistory() {
        Intent intent = new Intent(this, travelHistory.class);
        startActivity(intent);
    }

    private void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void makeJsonObjReq() {
        // Creating a JSON object with the user's description
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("description", description.getText().toString());
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
