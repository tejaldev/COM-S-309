package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.util.Log;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class Ratings extends AppCompatActivity {

    RatingBar ratings;
    private Button rateUsButton;
    EditText Comments;

    private String URL = "http://coms-309-013.class.las.iastate.edu:8080/rating/add/{SignUpName}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratings);

        rateUsButton = findViewById(R.id.rateUsButton);
        ratings = findViewById(R.id.ratingbar);
        Comments = findViewById(R.id.comments);
        String Iusername = SharedPrefsUtil.getUsername(this);
        String url = URL.replace("{SignUpName}", Iusername);

        rateUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the rating value as an integer
                int ratingValueInt = (int) ratings.getRating();

                // Convert the integer rating value to a float
                float ratingValue = (float) ratingValueInt;
                String comment = Comments.getText().toString();

                try {
                    JSONObject postData = new JSONObject();
                    postData.put("stars", ratingValue); // Send the rating value as a float
                    postData.put("comment", comment);

                    // Assuming you have a NetworkManager class
                    NetworkManager networkManager = NetworkManager.getInstance(getApplicationContext());

                    networkManager.sendPostRequest(postData, url,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Handle the response from the server, if needed
                                    Log.d("Network", "Post request successful: " + response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle the error from the server, if needed
                                    Log.e("Network", "Post request failed: " + error.toString());
                                }
                            }
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}