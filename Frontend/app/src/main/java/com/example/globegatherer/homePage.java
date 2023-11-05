package com.example.globegatherer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homePage extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    private ImageButton ProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ProfileButton=findViewById(R.id.profile_button);

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the profile button
                // You can open the profile page using an Intent
                Intent intent = new Intent(homePage.this, profile_page.class); // Replace ProfileActivity with your actual profile page activity
                startActivity(intent);
            }
        });


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
//                }else if (itemId == R.id.menu_profile) {
//                    openProfile();
//                    return true;
//                }
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

//    private void openProfile() {
//        Intent intent = new Intent(this, profile_page.class);
//        startActivity(intent);
//    }

}