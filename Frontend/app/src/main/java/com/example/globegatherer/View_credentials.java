package com.example.globegatherer;

import static com.example.globegatherer.MainActivity.URL_JSON_ARRAY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;

public class View_credentials extends AppCompatActivity {

    private NetworkManager networkManager;
    private TextView Credentials;
    private Button View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_credentials);

        networkManager = NetworkManager.getInstance(this);

        Credentials = findViewById(R.id.credentials_user);
        View = findViewById(R.id.view_credentials);

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        makeJsonArrayReq();
                }
                //makeJsonArrayReq();
            });
    }

    private void makeJsonArrayReq() {
        networkManager.sendGetRequest(URL_JSON_ARRAY,
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