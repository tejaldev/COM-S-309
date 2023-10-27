package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.handshake.ServerHandshake;

public class chatpage extends AppCompatActivity implements WebSocketListener{

    //new code
    private String BASE_URL = "ws://10.0.2.2:8080/chat/";

    private Button  sendBtn;
    private EditText msgEtx;

    private TextView msgTv;
//new code ends here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);

        sendBtn = findViewById(R.id.sendButton);
        msgEtx = findViewById(R.id.messageInput);
        sendBtn.setOnClickListener(v -> {
            try {
                WebSocketManager.getInstance().sendMessage(msgEtx.getText().toString());
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage());
            }
        });
    }

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "\n" + message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "---\nConnection closed by " + closedBy + "\nReason: " + reason);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {
        // WebSocket connection is opened
    }

    @Override
    public void onWebSocketError(Exception ex) {
        Log.e("WebSocketError", ex.getMessage(), ex);
    }
}


