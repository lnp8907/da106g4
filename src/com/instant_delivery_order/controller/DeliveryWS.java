package android.com.foodporn.websocketchat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import android.com.foodporn.websocketchat.jedis.JedisHandleMessage;
import android.com.foodporn.websocketchat.model.ChatMessage;
import android.com.foodporn.websocketchat.model.State;

@ServerEndpoint("/DeliveryWS/{userId}")
public class DeliveryWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session userSession) throws IOException {
		/* save the new user in the map */
		System.out.println("OnOpen進來了!!");
		sessionsMap.put(userId, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userIds = sessionsMap.keySet();
		State stateMessage = new State("open", userId, userIds);
		String stateMessageJson = gson.toJson(stateMessage);
		System.out.println(stateMessageJson);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(), userId,
				userIds);
		System.out.println(text);
	}

	@OnMessage
	// 員工傳送定位資料(Json)過來
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		String backend = chatMessage.getBackend();
		Session receiverSession = sessionsMap.get(receiver);
		Session backendSession = sessionsMap.get(backend);
//		if ("history".equals(chatMessage.getType())) {
//			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
//			String historyMsg = gson.toJson(historyData);
//			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
//			if (userSession != null && userSession.isOpen()) {
//				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
//				return;
//			}
//		}

		if ("location".equals(chatMessage.getType())) {
			if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(message);
			}
			if (backendSession != null && backendSession.isOpen()) {
				backendSession.getAsyncRemote().sendText(message);
			}
			System.out.println("Message received: " + message);
		}
	}

//			
//		Session receiverSession = sessionsMap.get(receiver);
//		if (receiverSession != null && receiverSession.isOpen()) {
//			receiverSession.getAsyncRemote().sendText(message);
//			JedisHandleMessage.saveChatMessage(sender, receiver, message);
//		}
//		System.out.println("Message received: " + message);
//	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
