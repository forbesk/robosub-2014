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
					JSONObject jsonmsg = new JSONObject();
					
					// Add variables to message here
					jsonmsg.put("surgeleft", new Double(Model.surgeLeft.getSpeed()));
					jsonmsg.put("surgeright", new Double(Model.surgeRight.getSpeed()));
					jsonmsg.put("heaveleft", new Double(Model.heaveLeft.getSpeed()));
					jsonmsg.put("heaveright", new Double(Model.heaveRight.getSpeed()));
					jsonmsg.put("sway", new Double(Model.sway.getSpeed()));
					jsonmsg.put("missionswitch", new Boolean(Model.missionSwitch.getState()));
					jsonmsg.put("killswitch", new Boolean(Model.killSwitch.getState()));
					jsonmsg.put("heading", new Double(Model.compass.getHeading()));
					jsonmsg.put("depth", new Double(Model.depthGauge.getDepth()));
					
					for(Session peer : peers) {
						// Add peer-specific variables to message here
						jsonmsg.put("wsid", peer.getId());
						
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
