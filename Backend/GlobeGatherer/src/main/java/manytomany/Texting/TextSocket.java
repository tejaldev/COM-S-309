package manytomany.Texting;

import manytomany.Persons.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@ServerEndpoint(value = "/chat/{username}")
public class TextSocket {

	private static MessageRepository msgRepo;

	private static NotificationRepository announcementRepo;
	private static PersonRepository personRepository;

	@Autowired
	public void setMessageRepository(MessageRepository repo) {
		msgRepo = repo;  // we are setting the static variable
	}

	@Autowired
	public void setAnnouncementRepository(NotificationRepository repo) {
		announcementRepo = repo;
	}

	@Autowired
	public void setPersonRepository(PersonRepository repo) {
		personRepository = repo;
	}


	// Store all socket session and their corresponding username.
	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(TextSocket.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username)
			throws IOException {

		logger.info("Entered into Open");

		// Store connecting user information
		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		// Send chat history to the newly connected user
		sendMessageToPArticularUser(username, getChatHistory());

		// Broadcast that a new user has joined
		String message = "User: " + username + " has joined the globeChat";
		broadcast(message);

		markMessagesAsSeen(username);
	}





	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		// Handle new messages
		logger.info("Entered into Message: Got Message:" + message);
		String username = sessionUsernameMap.get(session);

		// Direct message to a user using the format "@username <message>"
		if (message.startsWith("@")) {
			String destUsername = message.split(" ")[0].substring(1);

			// send the message to the sender and receiver
			sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
			sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);

		} else if (message.startsWith("/announcement")) { // Handle announcements
			String announcementContent = message.substring("/announcement".length()).trim();
			sendAnnouncement(username, announcementContent);
		} else { // broadcast
			broadcast(username + ": " + message);
			msgRepo.save(new Message(username, message));

			// Mark messages as seen for the user
			markMessagesAsSeen(username);
		}

	}

	private void markMessagesAsSeen(String username) {
		// Check the number of connected users
		int numberOfConnectedUsers = sessionUsernameMap.size();

		// Find all messages sent by the user
		List<Message> userMessages = msgRepo.findByUserNameAndSeen(username, false);

		// Mark each message as seen based on the number of connected users
		boolean markAsSeen = numberOfConnectedUsers > 1; // true if more than one user is connected

		for (Message message : userMessages) {
			message.setSeen(markAsSeen);
			msgRepo.save(message);
		}
	}



	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

    // remove the user connection information
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

    // broadcase that the user disconnected
		String message = username + " disconnected";
		broadcast(message);

		markMessagesAsSeen(username);

	}


	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}


	private void sendMessageToPArticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		}
    catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}


	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			}
      catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});

	}


  // Gets the Chat history from the repository
	private String getChatHistory() {
		List<Message> messages = msgRepo.findAll();

    // convert the list to a string
		StringBuilder sb = new StringBuilder();
		if(messages != null && messages.size() != 0) {
			for (Message message : messages) {
				sb.append(message.getUserName() + ": " + message.getContent() + "\n");
			}
		}
		return sb.toString();
	}

	private void sendAnnouncement(String username, String content) {
		Notification announcement = new Notification(username, content);
		announcementRepo.save(announcement);

		// Broadcast the announcement to all users
		broadcast("[ADMIN Announcement] " + username + ": " + content);
	}

	private String getAnnouncements() {
		List<Notification> announcements = announcementRepo.findAll();

		// Convert the list of announcements to a string
		StringBuilder sb = new StringBuilder();
		if (announcements != null && announcements.size() != 0) {
			for (Notification announcement : announcements) {
				sb.append(announcement.getUserName() + ": " + announcement.getContent() + "\n");
			}
		}
		return sb.toString();
	}

}
