package com.example.globegatherer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.java_websocket.handshake.ServerHandshake;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;


public class Announcements_admin extends AppCompatActivity implements WebSocketListener {

    private TextView Message;
    private Button Back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcements_admin);

        Message = findViewById(R.id.Announce);
        Back = findViewById(R.id.back);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

    }

    private boolean isMessageFromAdmin(String message) {
        return message.trim().startsWith("/announcement"); // Update with your admin message identification logic
    }


    public void openActivity() {
        Intent intent = new Intent(this, profile_page.class);
        startActivity(intent);
    }
    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {

    }

    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        runOnUiThread(() -> {
            if (Message != null) {
                if (isMessageFromAdmin(message)) {
                    // The message is from the admin - Display it in this view
                    String s = Message.getText().toString();
                    Message.setText(s + "\n" + message);
                }
            }
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {

        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = Message.getText().toString();
            Message.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
    }

    @Override
    public void onWebSocketError(Exception ex) {

    }

}
