package com.example.globegatherer;

import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

    private static final String CHANNEL_ID = "admin_channel";
        private EditText Announcement, msgEtx;
        private TextView Announce;
        private Button Exit, Connect, Send;
    private Button sendAnnouncementButton;


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

            sendAnnouncementButton = findViewById(R.id.announcement_button);

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
                String Iusername = "admin";
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

            sendAnnouncementButton.setOnClickListener(view -> {
                // Handle button click to send /announcement
                sendAnnouncement();
            });

            createNotificationChannel();

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


    private void sendAnnouncement() {
        String announcementMessage = Announcement.getText().toString();
        String fullMessage = "/announcement " + announcementMessage;

        if (WebSocketManager.getInstance().isSocketOpen()) {
            // Send the announcement message to all connected users
            WebSocketManager.getInstance().sendMessage(fullMessage);

            // Display local notification
            showNotification(announcementMessage);
        } else {
            // Show a message if the WebSocket connection is not established
            Toast.makeText(Announcements.this, "WebSocket connection is not established", Toast.LENGTH_SHORT).show();
        }
        showNotification(announcementMessage);
    }


    private void showNotification(String announcementMessage) {
        // Create an explicit intent for the Announcements activity
        Intent intent = new Intent(this, Announcements.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Create the notification
        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("New Announcement")
                .setContentText(announcementMessage)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentIntent(contentIntent)
                .build();


        // Get the notification manager
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Create the notification channel (if not already created)
        createNotificationChannel();

        // Show the notification
        notificationManager.notify(0, notification);
    }
        public void openActivity() {
            Intent intent = new Intent(this, Admin_Page.class);
            startActivity(intent);
        }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = CHANNEL_ID;  // Use the CHANNEL_ID constant here
            CharSequence channelName = "Admin Announcement";
            String channelDescription = "Message from Admin";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
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
                //Announce.setText(s + "\n" + message);
            });
        }

        @Override
        public void onWebSocketClose(int code, String reason, boolean remote) {
            String closedBy = remote ? "server" : "local";
            runOnUiThread(() -> {
                String s = Announce.getText().toString();
                //Announce.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
            });
        }

        @Override
        public void onWebSocketOpen(ServerHandshake handshakedata) {
        }

        @Override
        public void onWebSocketError(Exception ex) {
        }
    }
