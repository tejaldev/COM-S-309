package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PageAfterLogin extends AppCompatActivity {

    Button returnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_after_login);

        returnBack=findViewById(R.id.buttonToReturnBack);
        returnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnBackHomePage();
            }
        });

    }

    public void returnBackHomePage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}