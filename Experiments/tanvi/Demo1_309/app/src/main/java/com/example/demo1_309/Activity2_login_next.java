package com.example.demo1_309;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2_login_next extends AppCompatActivity {

//    EditText SignUpUsername;
//    EditText SignUpPassword;
//    Button SignUp;
    Button ReturnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_login_next);

        ReturnHome = findViewById(R.id.ReturnHome);

//        SignUpUsername = findViewById(R.id.SignUpUsername);
//        SignUpPassword = findViewById(R.id.SignUpPassword);
//
//        SignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(SignUpUsername.getText().toString().equals("tanvi") && SignUpPassword.getText().toString().equals("1234"))
//                {
//                    Toast.makeText(Activity2_login_next.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(Activity2_login_next.this, "Failed in Signing Up", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        ReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });
    }

    public void openHomePage(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
}