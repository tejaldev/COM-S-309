package com.example.globegatherer;

import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyLog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mapPage extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText locationInput;
    private List<Marker> pins = new ArrayList<>();
    private RequestQueue requestQueue;
    private ProgressDialog pDialog;

    private static final String TAG = toDoList.class.getSimpleName();
    private static final String URL = "http://coms-309-013.class.las.iastate.edu:8080/GoogleMaps/{SignUpName}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationInput = findViewById(R.id.locationInput);
        Button searchButton = findViewById(R.id.searchButton);

        requestQueue = Volley.newRequestQueue(this); // Initialize the RequestQueue

        searchButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String locationText = locationInput.getText().toString();
                Geocoder geocoder = new Geocoder(mapPage.this);
                List<Address> addressList;
                String name = locationInput.getText().toString().trim();
                try {
                    addressList = geocoder.getFromLocationName(locationText, 1);
                    if (!addressList.isEmpty()) {
                        double latitude = addressList.get(0).getLatitude();
                        double longitude = addressList.get(0).getLongitude();
                        addPin(latitude, longitude);
                        sendLocationToServer(latitude, longitude); // Send location data to the server
                    } else {
                        // Handle the case where the address couldn't be geocoded
                    }
                } catch (IOException e) {
                    // Handle any geocoding errors or exceptions
                    e.printStackTrace();
                }
                JSONObject params = new JSONObject();
                try {
                    params.put("destinationName", name);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                postRequest(params);

            }
        });
    }


    private void postRequest(JSONObject params) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        String Iusername = SharedPrefsUtil.getUsername(this);
        String url = URL.replace("{SignUpName}", Iusername);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                //responses2.setText(response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                //responses2.setText("Error: " + error.getMessage());
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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng defaultLocation = new LatLng(37.7749, -122.4194);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15));
    }

    private void addPin(double latitude, double longitude) {
        if (mMap != null) {
            LatLng pinLocation = new LatLng(latitude, longitude);
            Marker pin = mMap.addMarker(new MarkerOptions()
                    .position(pinLocation)
                    .title("Pin " + (pins.size() + 1)));
            pins.add(pin);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pinLocation, 15));
        }
    }

    private void sendLocationToServer(double latitude, double longitude) {
        String url = "{{url}}"; // Replace with your server's endpoint

        JSONObject locationData = new JSONObject();
        try {
            locationData.put("latitude", latitude);
            locationData.put("longitude", longitude);
            // Add other data if needed
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, locationData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle a successful response from the server
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                    }
                });

        requestQueue.add(request); // Add the request to the RequestQueue
    }
}
