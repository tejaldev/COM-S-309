package com.example.globegatherer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globegatherer.Admin_Page;
import com.example.globegatherer.WebSocketManager;
import com.google.android.material.card.MaterialCardView;

import org.java_websocket.handshake.ServerHandshake;
import org.w3c.dom.Text;

public class Admin_Page extends AppCompatActivity implements WebSocketListener{

    private String BASE_URL = "ws://10.0.2.2:8080/chat/";

    private MaterialCardView Announcements, Ban_User, View_Credential;
    private EditText usernameEtx, msgEtx;
    private TextView msgTv;
    private Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        /* initialize UI elements */
        Announcements = (MaterialCardView) findViewById(R.id.Announcement);
        Ban_User = (MaterialCardView) findViewById(R.id.Ban);
        View_Credential = (MaterialCardView) findViewById(R.id.Credential);
//        Suspend_User = (MaterialCardView) findViewById(R.id.Suspend);
//        Emergency_Text = (MaterialCardView) findViewById(R.id.Emergency);
        connect = findViewById(R.id.Connect);

        /* connect button listener */
        connect.setOnClickListener(view -> {
            String serverUrl = BASE_URL + usernameEtx.getText().toString();

            // Establish WebSocket connection and set listener
            WebSocketManager.getInstance().connectWebSocket(serverUrl);
            WebSocketManager.getInstance().setWebSocketListener(Admin_Page.this);
        });

        Ban_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });


        Announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        View_Credential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
        /* send button listener */
//        sendBtn.setOnClickListener(v -> {
//            try {
//
//                // send message
//                WebSocketManager.getInstance().sendMessage(msgEtx.getText().toString());
//            } catch (Exception e) {
//                Log.d("ExceptionSendMessage:", e.getMessage().toString());
//            }
//        });
    }

    public void openActivity(){
        Intent intent = new Intent(this, Announcements.class);
        startActivity(intent);
    }

    public void openActivity1(){
        Intent intent = new Intent(this, ban_user.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, View_credentials.class);
        startActivity(intent);
    }

    @Override
    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "\n"+message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    @Override
    public void onWebSocketError(Exception ex) {}
}