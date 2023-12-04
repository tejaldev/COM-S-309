package com.example.globegatherer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.globegatherer.R;

import org.json.JSONObject;

public class Activity2_login_next extends AppCompatActivity {

    private Button signUpButton;
    private EditText signUpNameEditText, signUpUsernameEditText, signUpPasswordEditText, signUpEmailEditText, signUpPhoneNoEditText;
    private TextView responseTextView;
    private NetworkManager networkManager;

    private static final String URL_JSON_OBJECT = "http://coms-309-013.class.las.iastate.edu:8080/persons/add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_login_next);

        signUpButton = findViewById(R.id.SignUpButton);
        signUpNameEditText = findViewById(R.id.SignUpName);
        signUpUsernameEditText = findViewById(R.id.SignUpUsername);
        signUpPasswordEditText = findViewById(R.id.SignUpPassword);
        signUpEmailEditText = findViewById(R.id.SignUpEmail);
        signUpPhoneNoEditText = findViewById(R.id.SignUpPhoneNo);
        responseTextView = findViewById(R.id.Response);

        networkManager = NetworkManager.getInstance(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReq();
            }
        });
    }

    private void makeJsonObjReq() {
        // Create a JSON object with the user's input data
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("signUpName", signUpNameEditText.getText().toString());
            requestData.put("signUpUsername", signUpUsernameEditText.getText().toString());
            requestData.put("signUpPassword", signUpPasswordEditText.getText().toString());
            requestData.put("signUpEmail", signUpEmailEditText.getText().toString());
            requestData.put("signUpPhoneNo", signUpPhoneNoEditText.getText().toString());
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
                            //responseTextView.setText(response.toString());
                            openActivity();
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

    public void openActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
