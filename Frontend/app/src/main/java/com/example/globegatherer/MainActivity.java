
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String URL_JSON_OBJECT = "http://coms-309-013.class.las.iastate.edu:8080/login";
    static final String URL_JSON_ARRAY = "http://coms-309-013.class.las.iastate.edu:8080/persons/all";

    Button SignUp;
    Button HomePage;
    Button Profile, Ratings;
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


//        message= findViewById(R.id.message);
        username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        SignUp = findViewById(R.id.signupbutton);
        HomePage=findViewById(R.id.homePage);
//        Profile = findViewById(R.id.profile);
//        Ratings = findViewById(R.id.rate);

        networkManager = NetworkManager.getInstance(this);

        loginButton = findViewById(R.id.loginButton);
//        message = findViewById(R.id.message);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputUsername = username.getText().toString().trim();
                String inputPassword = Password.getText().toString().trim();

                if (inputUsername.equals("admin") && inputPassword.equals("admin@123")) {
                    openAdminPage(); // Redirect to the admin page
                } else {
//                    if (signUpUsernames.contains(inputUsername)) {
//                        openActivity3();
//                    } else {
//                    openActivity3();
                    SharedPrefsUtil.saveUsername(MainActivity.this, inputUsername); // Save the username
                        makeJsonObjReq();
//                    }
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

        HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });



//        Profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openActivity5();
//            }
//        });

//        Ratings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openActivity4();
//            }
//        });


    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2_login_next.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, homePage.class);
        startActivity(intent);
    }
    public void openActivity4(){
        Intent intent = new Intent(this, Ratings.class);
        startActivity(intent);
    }

    public void openActivity5(){
        Intent intent = new Intent(this, profile_page.class);
        startActivity(intent);
    }

//    public void openHomepage(){
//        Intent intent = new Intent(this, homePage.class);
//        startActivity(intent);
//    }


    public void openAdminPage() {
        Intent intent = new Intent(this, Admin_Page.class);
        // Pass the username to the profile_page activity
        intent.putExtra("USERNAME", username.getText().toString());
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

//    private void makeJsonArrayReq() {
//        networkManager.sendGetRequest(URL_JSON_ARRAY,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            signUpUsernames.clear(); // Clear the array before adding new data
//
//                            // Iterate through the JSON array and extract signUpUsername
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String signUpUsername = jsonObject.optString("signUpUsername", "");
//
//                                // Append signUpUsername to the ArrayList
//                                signUpUsernames.add(signUpUsername);
//
//
//                                }
//                            // Convert ArrayList to a string for display
//                            String usernamesAsString = TextUtils.join(", ", signUpUsernames);
//
//
//                            // Display the extracted signUpUsernames in your TextView
////                            message.setText("signUpUsernames: " + signUpUsernames.toString());
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

    private void makeJsonObjReq() {
        // Create a JSON object with the user's input data
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("signUpUsername", username.getText().toString());
            requestData.put("signUpPassword", Password.getText().toString());
//            requestData.put("signUpPassword", signUpPasswordEditText.getText().toString());
//            requestData.put("signUpEmail", signUpEmailEditText.getText().toString());
//            requestData.put("signUpPhoneNo", signUpPhoneNoEditText.getText().toString());
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
                            String Message = response.optString("message", "");

                            if (Message.equals("Welcome")) {
                                openActivity3(); // Redirect to the home page on successful login
                            } else {
                                // Handle other messages or errors if needed
                                message.setText(Message); // Display the message to the user
                            }

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