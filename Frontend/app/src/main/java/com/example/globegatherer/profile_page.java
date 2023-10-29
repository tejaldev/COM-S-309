

package com.example.globegatherer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.globegatherer.R;

import org.json.JSONObject;

public class profile_page extends AppCompatActivity{

    private Button Edit;
    private Button ToDo;
    private Button Friend;

    //new code
    private Button Chat;

    private Button Map;

    // new code ends here
    private EditText description;
    private Button logout;
    private TextView Des_Response;
    private NetworkManager networkManager;

    private static final String URL_JSON_OBJECT = "http://coms-309-013.class.las.iastate.edu:8080/description/add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        Edit = findViewById(R.id.edit);
        description = findViewById(R.id.Description);
        logout = findViewById(R.id.logout);
        ToDo = findViewById(R.id.To_do_button);
        Friend = findViewById(R.id.Friends);
        //new code
        Chat = findViewById(R.id.chatButton);
        // new code ends here
        Des_Response = findViewById(R.id.Des_Response);


        networkManager = NetworkManager.getInstance(this);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReq();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        ToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity5();
            }
        });

        Friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
        //new code
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChatPage();
            }
        });

        // new code ends
    }

    public void openActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openActivity4(){
        Intent intent = new Intent(this, friends.class);
        startActivity(intent);
    }

    public void openActivity5(){
        Intent intent = new Intent(this, toDoList.class);
        startActivity(intent);
    }

    // new code
    public void openChatPage(){
        Intent intent = new Intent(this, chatpage.class);
        startActivity(intent);
    }
    // new code ends here
    private void makeJsonObjReq() {
        // Creating a JSON object with the user's description
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("description", description.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        networkManager.sendPostRequest(
                requestData,
                URL_JSON_OBJECT,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Volley Response", response.toString());
                            //Toast.makeText(profile_page.this, "Description saved successfully", Toast.LENGTH_SHORT).show();
                            Des_Response.setText(response.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        );
    }
}

