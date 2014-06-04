package org.auvua.server;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/vision")
public class VisionEndpoint {
	ArrayList<Session> peers = new ArrayList<Session>();
	
	@OnOpen
	public void onOpen(Session session) {
		peers.add(session);
		System.out.println("Session started: " + session.getId());
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.println("Message: " + msg);
		System.out.println("\tFrom: " + session.getId());
	}
	
	@OnClose
	public void onClose(Session session) {
		peers.remove(session);
		System.out.println("Session ended: " + session.getId());
	}
	
}
