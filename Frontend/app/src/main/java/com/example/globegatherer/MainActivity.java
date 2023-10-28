
package com.example.globegatherer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.globegatherer.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String URL_JSON_ARRAY = "http://coms-309-013.class.las.iastate.edu:8080/persons/all";
    Button SignUp;
    Button Profile;
    EditText username;
    EditText Password;
    Button loginButton;
    private TextView message;
    private NetworkManager networkManager;

    private JSONArray jsonResponse;

    private ArrayList<String> signUpUsernames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        SignUp = findViewById(R.id.signupbutton);
        Profile = findViewById(R.id.profile);

        networkManager = NetworkManager.getInstance(this);

        loginButton = findViewById(R.id.loginButton);
        message = findViewById(R.id.message);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputUsername = username.getText().toString().trim();
                String inputPassword = Password.getText().toString().trim();

                if (inputUsername.equals("admin") && inputPassword.equals("admin@123")) {
                    openAdminPage(); // Redirect to the admin page
                } else {
                    if (signUpUsernames.contains(inputUsername)) {
                        openActivity3();
                    } else {
                        makeJsonArrayReq();
                    }
                }
                //makeJsonArrayReq();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });


    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2_login_next.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, profile_page.class);
        startActivity(intent);
    }

    public void openAdminPage() {
        Intent intent = new Intent(this, Admin_Page.class);
        startActivity(intent);
    }

//    private void makeJsonArrayReq() {
//        networkManager.sendGetRequest(URL_JSON_ARRAY,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            // Display the response
//                            message.setText(response.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Volley Error", error.toString());
//                    }
//                }
//        );
//    }
//

    private void makeJsonArrayReq() {
        networkManager.sendGetRequest(URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            signUpUsernames.clear(); // Clear the array before adding new data

                            // Iterate through the JSON array and extract signUpUsername
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String signUpUsername = jsonObject.optString("signUpUsername", "");

                                // Append signUpUsername to the ArrayList
                                signUpUsernames.add(signUpUsername);


                                }
                            // Convert ArrayList to a string for display
                            String usernamesAsString = TextUtils.join(", ", signUpUsernames);


                            // Display the extracted signUpUsernames in your TextView
                            message.setText("signUpUsernames: " + signUpUsernames.toString());
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