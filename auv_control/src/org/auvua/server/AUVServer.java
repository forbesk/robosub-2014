package org.auvua.server;

import org.auvua.server.DashboardEndpoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.auvua.agent.*;
import org.auvua.agent.tasks.*;
import org.glassfish.tyrus.server.Server;

public class AUVServer {
	private static Agent agent;
	
	public static void main(String args[]) {
		Task task0 = new ConstantDepthAndHeading(-29, 45, 120000);
		
		agent = new Agent(task0);
		
		new Thread(agent).start();
		
		runServer();
	}
	
	public static void runServer() {
		Server server = new Server("localhost", 8080, "", null, 
				DashboardEndpoint.class,
				VisionEndpoint.class, 
				MissionEndpoint.class);
		
		try {
	        server.start();
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Please press a key to stop the server.");
	        reader.readLine();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        server.stop();
	    }
	}
	
}