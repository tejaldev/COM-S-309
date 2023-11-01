package com.cs309.websocket3.notification;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // this is needed for this to be an endpoint to springboot
@ServerEndpoint(value = "/notification")
public class NotificationSocket {

	private static NotificationRepository notificationRepo;

	@Autowired
	public void setMessageRepository(NotificationRepository repo) {
		notificationRepo = repo;
	}

	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(NotificationSocket.class);

	@OnOpen
	public void onOpen(Session session) throws IOException {
		logger.info("User connected: " + session.getId());

		// Store only session now, as we're not obtaining a username from the URL
		sessionUsernameMap.put(session, session.getId()); // Use session ID as a default user identifier
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		String username = sessionUsernameMap.get(session);
		if (isAdmin(username)) { // if the user is an admin
			adminBroadcast(message); // broadcast message to all
		}
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);
		String msg = username + " disconnected";
		broadcast(msg);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}
	

	private void sendMessageToPArticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}
		});
	}

	private void adminBroadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText("Admin Notification: " + message);
			} catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}
		});
	}

	private boolean isAdmin(String username) {
		return "admin".equalsIgnoreCase(username);
	}

}
