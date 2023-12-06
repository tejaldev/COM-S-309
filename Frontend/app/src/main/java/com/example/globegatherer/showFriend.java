package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class showFriend extends AppCompatActivity {

    private static final String URL2 = "http://coms-309-013.class.las.iastate.edu:8080/friends/{SignUpName}";

    private ProgressDialog pDialog;
    private static final String TAG = showFriend.class.getSimpleName();

    private LinearLayout friendsContainer;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friend);

        friendsContainer = findViewById(R.id.friendsContainer);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

        JSONArray parameter = new JSONArray();
        getRequest(parameter);
    }

    private void getRequest(JSONArray parameter) {
        progressBar.setVisibility(View.VISIBLE);

        String Iusername = SharedPrefsUtil.getUsername(this);
        String url = URL2.replace("{SignUpName}", Iusername);

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.GET, url, parameter, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                updateFriendList(response);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                textView.setText("Error: " + error.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });

        Volley.newRequestQueue(this).add(jsonObjReq);
    }

    private void updateFriendList(JSONArray friends) {
        // Clear existing views
        friendsContainer.removeAllViews();

        if (friends.length() > 0) {
            textView.setVisibility(View.GONE);

            for (int i = 0; i < friends.length(); i++) {
                try {
                    String friendName = friends.getString(i);

                    // Inflate friend item layout
                    View friendItemView = LayoutInflater.from(this).inflate(R.layout.showfriend_item, null);

                    // Set friend name
                    TextView friendTextView = friendItemView.findViewById(R.id.friendName);
                    friendTextView.setText(friendName);

                    // Add friend item to the container
                    friendsContainer.addView(friendItemView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            textView.setText("No friends available.");
            textView.setVisibility(View.VISIBLE);
        }
    }


}
