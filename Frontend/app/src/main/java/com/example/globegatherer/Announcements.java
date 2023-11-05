package com.example.globegatherer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.java_websocket.handshake.ServerHandshake;
import android.util.Log;
import android.widget.Toast;
import com.example.globegatherer.Announcements;
import com.example.globegatherer.WebSocketManager;
import org.w3c.dom.Text;

public class Announcements extends AppCompatActivity implements WebSocketListener {

//        private String BASE_URL = "ws://10.0.2.2:8080/chat/";
        private String BASE_URL = "ws://coms-309-013.class.las.iastate.edu:8080/chat/{SignUpName}";

        private EditText Announcement, msgEtx;
        private TextView Announce;
        private Button Exit, Connect, Send;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.announcement);

            /* initialize UI elements */
            Announcement =  findViewById(R.id.announce_text);
            Connect = findViewById(R.id.Connect);
            Exit = findViewById(R.id.back);
            Send = findViewById(R.id.send_button);

            Announce = findViewById(R.id.announce);

            Send.setOnClickListener(view -> {

                String message_announce = Announcement.getText().toString();

                if (WebSocketManager.getInstance().isSocketOpen()) {
                    // Send the announcement message to all connected users
                    WebSocketManager.getInstance().sendMessage(message_announce);
                } else {
                    // Show a message if the WebSocket connection is not established
                    Toast.makeText(Announcements.this, "WebSocket connection is not established", Toast.LENGTH_SHORT).show();
                }
            });
            /* connect button listener */
            Connect.setOnClickListener(view -> {
                String Iusername = SharedPrefsUtil.getUsername(this);
                String url = BASE_URL.replace("{SignUpName}", Iusername);

                // Establish WebSocket connection and set listener
                WebSocketManager.getInstance().connectWebSocket(url);
                WebSocketManager.getInstance().setWebSocketListener(Announcements.this);
            });


            Exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openActivity();
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

        public void openActivity() {
            Intent intent = new Intent(this, Admin_Page.class);
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
                String s = Announce.getText().toString();
//                Announce.setText(s + "\n" + message);
            });
        }

        @Override
        public void onWebSocketClose(int code, String reason, boolean remote) {
            String closedBy = remote ? "server" : "local";
            runOnUiThread(() -> {
                String s = Announce.getText().toString();
//                Announce.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
            });
        }

        @Override
        public void onWebSocketOpen(ServerHandshake handshakedata) {
        }

        @Override
        public void onWebSocketError(Exception ex) {
        }
    }
