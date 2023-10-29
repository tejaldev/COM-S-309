package com.example.globegatherer;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ban_user extends AppCompatActivity {

    private NetworkManager networkManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ban_user);
    }
    public ban_user(Context context) {
        networkManager = NetworkManager.getInstance(context);
    }

    public void banUser(String userId) {
        String url = "http://your_api_url/users/" + userId; // Replace with your API endpoint for banning users

        networkManager.sendDeleteRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle successful response (if applicable)
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }
        );
    }
}
