package org.auvua.server;

import org.auvua.logging.LoggingHandler;
import org.auvua.model.Model;
import org.auvua.vision.HTTPCameraStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.auvua.agent.*;
import org.auvua.agent.MissionFactory.MissionType;
import org.auvua.agent.tasks.align.AlignToMarker;
import org.auvua.agent.tasks.align.PositionOverBuoy;
import org.auvua.agent.tasks.search.SearchForManeuverPole;
import org.auvua.agent.tasks.search.SearchForRedDownBuoy;
import org.auvua.agent.tasks.special.CircumnavigateSimple;
import org.auvua.agent.tasks.special.ResetHeading;
import org.opencv.core.Core;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

public class AUVServer {
	private static Agent agent;
	
	public static void main(String args[]) {
		new LoggingHandler();

		try {
			System.out.println(Core.NATIVE_LIBRARY_NAME);
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME);
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Could not find resource: " + Core.NATIVE_LIBRARY_NAME);
			Logger.getLogger("LUMBERJACK").severe("Failed to load OpenCV. Disabling vision.");
			//TODO: disable video
		}

		try {
			System.loadLibrary("jzmq");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Could not find resource: jzmq");
			System.out.println("JZMQ not found. Exiting...");
			System.exit(-1);
		}

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
		//new Thread(new HTTPCameraStream("/dev/video0", 8080)).start();
		LoggingEndpoint logws = new LoggingEndpoint(new InetSocketAddress(8080));
		logws.start();
		Logger.getLogger("LUMBERJACK").info("Logging started.");

		Server server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8181);
        server.addConnector(connector);
         
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(false);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
 
        resource_handler.setResourceBase("http");
 
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
        server.setHandler(handlers);
		
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			System.err.println("Failed to create server instance");
			e.printStackTrace();
		}
	}
	
}