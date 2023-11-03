
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratings);

        rateUsButton = findViewById(R.id.rateUsButton);
        ratings = findViewById(R.id.ratingbar);
        Comments = findViewById(R.id.comments);

        rateUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float ratingValue = ratings.getRating();
                String comment = Comments.getText().toString();

                try {
                    JSONObject postData = new JSONObject();
                    postData.put("rating", ratingValue);
                    postData.put("comment", comment);

                    // Assuming you have a NetworkManager class
                    NetworkManager networkManager = NetworkManager.getInstance(getApplicationContext());
                    String url = "/rating/add";

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
