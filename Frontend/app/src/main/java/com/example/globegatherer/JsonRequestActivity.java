package com.example.globegatherer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyLog;

import org.json.JSONObject;

public class JsonRequestActivity extends AppCompatActivity {

    private static final String TAG = JsonRequestActivity.class.getSimpleName();
    private static final String URL = "https://3bdd2a4b-2ee5-4602-a7a0-bc11c0a28937.mock.pstmn.io";
    private static final String TAG_JSON_OBJ = "json_obj_req";

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,URL,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }

        );



        // Adding the request to the request queue
        Volley.newRequestQueue(this).add(jsonObjReq);
    }




}
