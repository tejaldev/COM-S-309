package com.example.globegatherer;

//import static com.example.globegatherer.MainActivity.URL_JSON_ARRAY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class View_credentials extends AppCompatActivity {


    private NetworkManager networkManager;
    private TextView Credentials;
    private EditText Username_Edit;
    private Button View;
    private static String URL="http://coms-309-013.class.las.iastate.edu:8080/persons/cred";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_credentials);

        networkManager = NetworkManager.getInstance(this);

        Credentials = findViewById(R.id.credentials_user);
        View = findViewById(R.id.view_credentials);
        Username_Edit = findViewById(R.id.usernameEdit);


        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = Username_Edit.getText().toString();
//                if (!inputUsername.isEmpty()) {
//                    makeJsonArrayReq();
//                } else {
//                    // Handle empty input case
//                    // For example, show a message to the user to enter a username
//                }
                makeJsonArrayReq();
            }
            });
    }

    // Inside the makeJsonArrayReq method in View_credentials activity
        private void makeJsonArrayReq() {
//            String Iusername = SharedPrefsUtil.getUsername(this);
//            String url = URL.replace("{SignUpName}", Iusername);
        networkManager.sendGetRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Display the response
                            Credentials.setText(response.toString());
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