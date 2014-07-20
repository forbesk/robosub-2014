package org.auvua.server;

import org.auvua.model.*;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/dashboard")
public class DashboardEndpoint {
	ArrayList<Session> peers = new ArrayList<Session>();
	
	public DashboardEndpoint() {
		new Thread(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				while(true) {
					JSONObject jsonmsg = new JSONObject(Model.getInstance().getRobot());

					for(Session peer : peers) {
												
						try {
							peer.getBasicRemote().sendText(jsonmsg.toJSONString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	@OnOpen
	public void onOpen(Session session) {
		peers.add(session);
		System.out.println("Session started: " + session.getId());
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) {		
		
		
	}
	
	@OnClose
	public void onClose(Session session) {
		peers.remove(session);
		System.out.println("Session ended: " + session.getId());
	}
	
}
