package com.example.demo1_309;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo1_309.Activity2_login_next;
import com.example.demo1_309.MainActivity2;

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
//        SignUp = findViewById(R.id.SignUp);
//        ReturnHome = findViewById(R.id.ReturnHome);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(Username.getText().toString().equals("user") && Password.getText().toString().equals("1234"))
//                {
//                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "Failed Log in", Toast.LENGTH_SHORT).show();
//                }
                openActivitylogin();
            }
        });
        SignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

//        SignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(Username.getText().toString().equals("tanvi") && Password.getText().toString().equals("1234"))
//                {
//                    Toast.makeText(Activity2_login_next.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(Activity2_login_next.this, "Failed in Signing Up", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        ReturnHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openHomePage();
//            }
//        });
    }

    public void openActivitylogin(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent = new Intent(this, Activity2_login_next.class);
        startActivity(intent);
    }

//    public void openHomePage(){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}