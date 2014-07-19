package org.auvua.server;

import org.auvua.server.DashboardEndpoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.auvua.agent.*;
import org.auvua.agent.tasks.DriveTask;
import org.auvua.model.*;

import org.glassfish.tyrus.server.Server;

public class AUVServer {
		
	public static void main(String args[]) {
		Model model = new Model();
		Agent agent = new Agent( model, new DriveTask(60.0, 10000));
		
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