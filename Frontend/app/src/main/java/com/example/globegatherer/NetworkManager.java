package com.example.globegatherer;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class NetworkManager {
    private static NetworkManager instance = null;
    private RequestQueue requestQueue;
    private static Context ctx;
    private static final int MY_SOCKET_TIMEOUT_MS = 10000;
    private NetworkManager(Context context) {
        ctx = context.getApplicationContext();
        requestQueue = getRequestQueue();
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }

    public void sendPostRequest(JSONObject postData, String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener)
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData, responseListener, errorListener);
        getRequestQueue().add(jsonObjectRequest);
    }

    public void sendGetRequest(String url, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, responseListener, errorListener);
        getRequestQueue().add(jsonArrayRequest);
    }


    public void sendJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, jsonRequest, listener, errorListener);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    public void sendDeleteRequest(String url, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.DELETE, url, successListener, errorListener);
        requestQueue.add(request);
    }

    public void sendGetRequestWithArgument(String url, String argument, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        String fullUrl = url + argument;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, fullUrl, null, responseListener, errorListener);
        getRequestQueue().add(jsonArrayRequest);
    }


}