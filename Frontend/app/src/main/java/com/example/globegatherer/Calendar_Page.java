package com.example.globegatherer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Calendar_Page extends AppCompatActivity {

    private CalendarView StartDate;
    private Button startDateButton;
    private Button endDateButton;
    private CalendarView EndDate;
    private EditText destinationEditText;
    private Button Save;

    private NetworkManager networkManager;
    private long selectedStartDate;
    private long selectedEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);

        StartDate = findViewById(R.id.startDate);
        EndDate = findViewById(R.id.endDate);
        destinationEditText = findViewById(R.id.destinationEditText);
        Save = findViewById(R.id.calendarButton);
        startDateButton = findViewById(R.id.startDateButton);
        endDateButton = findViewById(R.id.endDateButton);
        networkManager = NetworkManager.getInstance(this);

        StartDate.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedStartDate = calendar.getTimeInMillis();
        });

        EndDate.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedEndDate = calendar.getTimeInMillis();
        });

        startDateButton.setOnClickListener(v -> {
            StartDate.setVisibility(View.VISIBLE);
            EndDate.setVisibility(View.GONE);
        });

        endDateButton.setOnClickListener(v -> {
            StartDate.setVisibility(View.GONE);
            EndDate.setVisibility(View.VISIBLE);
        });

        Save.setOnClickListener(v -> sendDataToBackend());
    }

    private void sendDataToBackend() {

        String destination = destinationEditText.getText().toString();

        if (selectedStartDate != 0 && selectedEndDate != 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                String startDateString = sdf.format(new Date(selectedStartDate));
                String endDateString = sdf.format(new Date(selectedEndDate));

                JSONObject postData = new JSONObject();
                postData.put("startDate", startDateString);
                postData.put("endDate", endDateString);
                postData.put("destination", destination);

                // NetworkManager class is used to send the JSON data to the backend
                String url = "http://coms-309-013.class.las.iastate.edu:8080/cal/{SignUpName}"; // Replace with your actual endpoint
                String Iusername = SharedPrefsUtil.getUsername(this);
                String Url = url.replace("{SignUpName}", Iusername);

                networkManager.sendPostRequest(postData, Url,
                        response -> Log.d("Network", "Post request successful: " + response.toString()),
                        error -> Log.e("Network", "Post request failed: " + error.toString())
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("Network", "Selected dates are not set properly");
        }
    }
}
