package com.example.globegatherer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homePage extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        // Set up a listener to handle item selection
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_friends) {
                    openFriendsActivity();
                    return true;
                } else if (itemId == R.id.menu_chat) {
                    openChatActivity();
                    return true;
                } else if (itemId == R.id.menu_todo) {
                    openTodoListActivity();
                    return true;
                }else if (itemId == R.id.menu_map) {
                    openmapPage();
                    return true;
                }else if (itemId == R.id.menu_history) {
                    opentravelHistory();
                    return true;
                }
                return false;
            }
        });
    }

    private void openFriendsActivity() {
        Intent intent = new Intent(this, friends.class);
        startActivity(intent);
    }

    private void openChatActivity() {
        Intent intent = new Intent(this, chatpage.class);
        startActivity(intent);
    }

    private void openTodoListActivity() {
        Intent intent = new Intent(this, toDoList.class);
        startActivity(intent);
    }

    private void openmapPage() {
        Intent intent = new Intent(this, mapPage.class);
        startActivity(intent);
    }

    private void opentravelHistory() {
        Intent intent = new Intent(this, travelHistory.class);
        startActivity(intent);
    }
}