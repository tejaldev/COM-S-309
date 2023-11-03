package com.example.globegatherer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class Calendar_Page extends AppCompatActivity {

    private CalendarView StartDate;
    private CalendarView EndDate;
    private EditText destinationEditText;
    private Button Save;

    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);

        StartDate = findViewById(R.id.startDate);
        EndDate = findViewById(R.id.endDate);
        destinationEditText = findViewById(R.id.destinationEditText);
        Save = findViewById(R.id.calendarButton);
        networkManager = NetworkManager.getInstance(this);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToBackend();
            }
        });

    }

    private void sendDataToBackend() {
        long startDate = StartDate.getDate(); // Get selected start date in milliseconds
        long endDate = EndDate.getDate(); // Get selected end date in milliseconds
        String destination = destinationEditText.getText().toString();

        try {
            JSONObject postData = new JSONObject();
            postData.put("start_date", startDate);
            postData.put("end_date", endDate);
            postData.put("destination", destination);

            // Assuming NetworkManager class is implemented and available
            NetworkManager networkManager = NetworkManager.getInstance(getApplicationContext());
            String url = "YOUR_BACKEND_ENDPOINT"; // Replace with your actual endpoint

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
}
