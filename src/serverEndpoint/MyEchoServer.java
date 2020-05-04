package serverEndpoint;

import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{hostID}")
public class MyEchoServer {
	
//private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
private static final Map<String,Set<Session>> map = new Hashtable<String,Set<Session>>();

	public static Map<String, Set<Session>> getMap() {
	  return map;
    }


	@OnOpen
	public void onOpen(@PathParam("hostID") String hostID, Session userSession) throws IOException {
		hostID=hostID.toLowerCase();
		System.out.println("hostID="+hostID);
		Set<Session> hostID_allSessions = null;
		if(map.containsKey(hostID)) {
			hostID_allSessions = map.get(hostID);
			hostID_allSessions.add(userSession);
			map.put(hostID, hostID_allSessions);
		}else {
			hostID_allSessions = Collections.synchronizedSet(new HashSet<Session>());
			hostID_allSessions.add(userSession);
			map.put(hostID, hostID_allSessions);
		}
		for (Session session : hostID_allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText("count="+ (map.get(hostID).size()-1));
		}
		System.out.println("map.get(hostID).size()="+map.get(hostID).size());
	}

	
	@OnMessage
	public void onMessage(@PathParam("hostID") String hostID, Session userSession, String message) {
		hostID=hostID.toLowerCase();
		Set<Session> hostID_allSessions = map.get(hostID);
		
		for (Session session : hostID_allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){

	}
	
	@OnClose
	public void onClose(@PathParam("hostID") String hostID, Session userSession, CloseReason reason) {
		hostID=hostID.toLowerCase();
		Set<Session> hostID_allSessions = map.get(hostID);
		hostID_allSessions.remove(userSession);
		map.put(hostID, hostID_allSessions);
		for (Session session : hostID_allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText("count="+ (map.get(hostID).size()-1));
		}
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
		System.out.println("map.get(hostID).size()="+map.get(hostID).size());
	}
}
