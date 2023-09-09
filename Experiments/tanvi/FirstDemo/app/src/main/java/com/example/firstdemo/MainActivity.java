package com.example.firstdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button SignUpText;
    EditText Username;
    EditText Password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        loginButton = findViewById(R.id.loginButton);
        SignUpText = findViewById(R.id.SignUpText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Username.getText().toString().equals("user") && Password.getText().toString().equals("1234"))
                {
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed Log in", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, Activity2_login_next.class);
        startActivity(intent);
    }
}