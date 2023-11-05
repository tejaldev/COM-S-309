package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class showFriend extends AppCompatActivity {

   // private Button showToDoLists;

    private static final String URL2 = "http://coms-309-013.class.las.iastate.edu:8080/friends/{SignUpName}";

    private ProgressDialog pDialog;
    private static final String TAG = showFriend.class.getSimpleName();

    private TextView showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friend);
        showing = findViewById(R.id.showing);
        //showToDoLists = findViewById(R.id.showToDoList);
        JSONArray parameter= new JSONArray();
        getRequest(parameter);


//        showToDoLists.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                openToDoList();
//            }
//
//
//        });

    }

//    private void openToDoList(){
//        Intent intent = new Intent(this, toDoList.class);
//        startActivity(intent);
//    }

    private void getRequest(JSONArray parameter){

        // Create a ProgressDialog to show loading while fetching data
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        String Iusername = SharedPrefsUtil.getUsername(this);
        String url = URL2.replace("{SignUpName}", Iusername);

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.GET, url, parameter, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                showing.setText(response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                showing.setText("Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {
            //            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                // Your header logic here
//                HashMap<String, String> headers = new HashMap<>();
//                // Add any necessary headers here
//                // Example: headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                // Example: headers.put("Content-Type", "application/json");
//                return headers;
//            }
            {
                setShouldCache(false); // Disable caching for debugging
                VolleyLog.DEBUG = true;
            }
        };



        // Adding the request to the request queue
        Volley.newRequestQueue(this).add(jsonObjReq);


    }

}
