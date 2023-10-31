package com.example.globegatherer;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ban_user extends AppCompatActivity {
    private Button Ban;
    private TextView Banned;

    private EditText Ban_username;

    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ban_user);

        Ban = findViewById(R.id.Ban_user);
        Ban_username = findViewById(R.id.announce_text);
        Banned = findViewById(R.id.user_ban);

        networkManager = NetworkManager.getInstance(this);

        Ban.setOnClickListener(view -> {
            // Get the username from the EditText
            String username = Ban_username.getText().toString();

            // Call the method to send a delete request
            banUser(username);
        });
    }
//    public ban_user(Context context) {
//        networkManager = NetworkManager.getInstance(context);
//    }

    public void banUser(String userId) {
        String url = "/admins/delete/{SignUpName}" + userId; // Replace with your API endpoint for banning users

        networkManager.sendDeleteRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle successful response (if applicable)
                        Banned.setText("User with ID " + userId + " deleted successfully.");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Banned.setText("Error: " + error.toString());
                    }
                }
        );
    }
}
