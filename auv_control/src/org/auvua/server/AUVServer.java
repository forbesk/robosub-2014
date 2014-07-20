package org.auvua.server;

import org.auvua.server.DashboardEndpoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.auvua.agent.*;
import org.auvua.agent.tasks.DriveStraight;
import org.auvua.model.*;

import org.glassfish.tyrus.server.Server;

public class AUVServer {
	private static Agent agent;
	
	public static void main(String args[]) {
		Task task0 = new DriveStraight(0.0, 3000);
		Task task1 = new DriveStraight(0.3, 3000);
		Task task2 = new DriveStraight(-0.5, 3000);
		Task task3 = new DriveStraight(0.0, 3000);
		
		task0.setSuccessTask(task1);
		task1.setSuccessTask(task2);
		task2.setSuccessTask(task3);
		
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