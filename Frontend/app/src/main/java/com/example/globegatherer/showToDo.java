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

public class showToDo extends AppCompatActivity {

    private static final String URL2 = "http://coms-309-013.class.las.iastate.edu:8080/TravelToDo/{SignUpName}";

    private ProgressDialog pDialog;
    private static final String TAG = showToDo.class.getSimpleName();

    private LinearLayout todosContainer;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_to_do);

        todosContainer = findViewById(R.id.todosContainer);
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
                updateTodoList(response);
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

    private void updateTodoList(JSONArray todos) {
        // Clear existing views
        todosContainer.removeAllViews();

        if (todos.length() > 0) {
            textView.setVisibility(View.GONE);

            for (int i = 0; i < todos.length(); i++) {
                try {
                    String todoItem = todos.getString(i);

                    // Inflate todo item layout
                    View todoItemView = LayoutInflater.from(this).inflate(R.layout.todo_item, null);

                    // Set todo item text
                    TextView todoTextView = todoItemView.findViewById(R.id.todoItemText);
                    todoTextView.setText(todoItem);

                    // Add todo item to the container
                    todosContainer.addView(todoItemView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            textView.setText("No ToDo items available.");
            textView.setVisibility(View.VISIBLE);
        }
    }
}
