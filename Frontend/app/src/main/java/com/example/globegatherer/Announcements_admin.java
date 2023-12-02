package com.example.globegatherer;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.java_websocket.handshake.ServerHandshake;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;


public class Announcements_admin extends AppCompatActivity implements WebSocketListener {

    private TextView Message;
    private Button Back, connection;
    private WebSocketManager webSocketManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcements_admin);

        Message = findViewById(R.id.Announce);
        Back = findViewById(R.id.back);
        connection = findViewById(R.id.Connect);
        webSocketManager = WebSocketManager.getInstance();


        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectToWebSocket();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        webSocketManager.setWebSocketListener(this);


    }

    private boolean isMessageFromAdmin(String message) {
        return message.trim().startsWith("/announcement");
    }


    public void openActivity() {
        Intent intent = new Intent(this, profile_page.class);
        startActivity(intent);
    }

    private void connectToWebSocket() {
        String URL = "ws://coms-309-013.class.las.iastate.edu:8080/chat/{SignUpName}";

        String Iusername = SharedPrefsUtil.getUsername(this);
        String webSocketURL = URL.replace("{SignUpName}", Iusername);

        webSocketManager.connectWebSocket(webSocketURL);
    }
    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {
        Log.d("WebSocket", "Connected to the server");
    }

    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        Log.d("WebSocket", "Received message: " + message);
        if (message.trim().contains("/announcement")) {
            runOnUiThread(() -> {
                // Extract the line containing /announcement
                String announcementLine = getAnnouncementLine(message);
                String s = Message.getText().toString();
                Message.setText(s + "\n" + announcementLine);
            });
        }
    }

    private String getAnnouncementLine(String message) {
        String[] lines = message.split("\n");
        for (String line : lines) {
            if (line.contains("/announcement")) {
                return line.trim();
            }
        }
        return ""; // If no line contains /announcement
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

    protected void onDestroy() {
        super.onDestroy();
        webSocketManager.removeWebSocketListener();
    }
}
