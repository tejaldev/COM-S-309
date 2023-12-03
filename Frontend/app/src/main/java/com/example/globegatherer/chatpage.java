package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.java_websocket.handshake.ServerHandshake;

public class chatpage extends AppCompatActivity implements WebSocketListener {

    private String BASE_URL = "ws://coms-309-013.class.las.iastate.edu:8080/chat/{SignUpName}";

    private Button connectBtn, sendBtn;
    private EditText usernameEtx, msgEtx;
    private LinearLayout chatContainer;  // Change from TextView to LinearLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);

        /* initialize UI elements */
        connectBtn = findViewById(R.id.bt1);
        sendBtn = findViewById(R.id.sendButton);
        usernameEtx = findViewById(R.id.et1);
        msgEtx = findViewById(R.id.messageInput);
        chatContainer = findViewById(R.id.chatContainer);  // Change to chatContainer

        /* connect button listener */
        connectBtn.setOnClickListener(view -> {
            String Iusername = SharedPrefsUtil.getUsername(this);
            String url = BASE_URL.replace("{SignUpName}", Iusername);

            String serverUrl = url + usernameEtx.getText().toString();

            // Establish WebSocket connection and set listener
            WebSocketManager.getInstance().connectWebSocket(serverUrl);
            WebSocketManager.getInstance().setWebSocketListener(chatpage.this);
        });

        /* send button listener */
        sendBtn.setOnClickListener(v -> {
            try {
                // send message
                WebSocketManager.getInstance().sendMessage(msgEtx.getText().toString());
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage());
            }
        });
    }

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            // Create a new TextView for the received message
            TextView receivedMessage = new TextView(this);
            receivedMessage.setText(message);
            receivedMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
           // receivedMessage.setBackgroundResource(R.drawable.bubble_left); // You may need to create a custom drawable for the left bubble
            receivedMessage.setTextColor(Color.BLACK);

            // Add the TextView to the chat container
            chatContainer.addView(receivedMessage);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        runOnUiThread(() -> {
            // Update the UI accordingly or remove this part if not needed
            String s = ""; // Initialize with an empty string or retrieve the appropriate text from your layout
            s += "---\nconnection closed by " + (remote ? "server" : "local") + "\nreason: " + reason;
            TextView msgTv = findViewById(R.id.statusTextView);  // You might want to use a different TextView for displaying status messages
            msgTv.setText(s);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    @Override
    public void onWebSocketError(Exception ex) {}
}
