package org.auvua.server;

import org.auvua.server.DashboardEndpoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.auvua.agent.*;
import org.auvua.agent.MissionFactory.MissionType;
import org.auvua.agent.tasks.align.AlignToMarker;
import org.auvua.agent.tasks.align.PositionOverBuoy;
import org.auvua.agent.tasks.search.SearchForManeuverPole;
import org.auvua.agent.tasks.search.SearchForRedDownBuoy;
import org.auvua.agent.tasks.special.CircumnavigateSimple;
import org.auvua.agent.tasks.special.ResetHeading;
import org.glassfish.tyrus.server.Server;
import org.opencv.core.Core;

public class AUVServer {
	private static Agent agent;
	
	public static void main(String args[]) {
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
		Task startTask = MissionFactory.buildMission(MissionType.BUOYS_ABOVE);
		
		Task remoteControl = MissionFactory.buildMission(MissionType.TELEOP);
		
		Task resetGyro = new ResetHeading();
		Task buoySearch = new SearchForRedDownBuoy(0.0, 60000);
		Task buoyPosition = new PositionOverBuoy(60000);
		Task markerAlign = new AlignToMarker(60000);
		
		Task maneuverPoleSearch = new SearchForManeuverPole(.3, -1, 60000);
		Task circumnavigate = new CircumnavigateSimple(.1, -1, 120000);
		resetGyro.setSuccessTask(maneuverPoleSearch);
		maneuverPoleSearch.setSuccessTask(circumnavigate);
		
		agent = new Agent(remoteControl);
		
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