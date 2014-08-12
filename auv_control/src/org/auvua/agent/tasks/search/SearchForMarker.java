package org.auvua.agent.tasks.search;

import org.auvua.agent.Task;
import org.auvua.agent.control.AggregateController;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.Controller;
import org.auvua.agent.control.Function;
import org.auvua.agent.control.OpenLoop;
import org.auvua.agent.control.OpenLoopController;
import org.auvua.agent.control.PIDController;
import org.auvua.agent.control.PIDLoop;
import org.auvua.agent.control.Sinusoid;
import org.auvua.model.Model;
import org.auvua.vision.FloorMarkerFilter;
import org.auvua.vision.RedBuoyFilter;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class SearchForMarker extends Task {
	
	public double speed;
	public int duration;
	
	public SearchForMarker( double speed, int duration ) {
		this.speed = speed;
		this.duration = duration; // In milliseconds
	}

	@Override
	public TaskState runTaskBody() {
		System.out.println("Where's the marker?");
		//Model.getInstance().getGyroIntegrator().resetHeading();
		Model.getInstance().addImageFilter("bottomCamera", "markerFilter", new FloorMarkerFilter());
		
		Function constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		
		AggregateController heaveLeftControl = new AggregateController("hardware.heaveLeft.speed");
		heaveLeftControl.addLoop(new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		heaveLeftControl.addLoop(new OpenLoop(new Sinusoid(5000, 90, -.2)));
		
		AggregateController heaveRightControl = new AggregateController("hardware.heaveRight.speed");
		heaveRightControl.addLoop(new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		heaveRightControl.addLoop(new OpenLoop(new Sinusoid(5000, 90, .2)));
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), 0.025, 0.005, 0));
		surgeLeftControl.addLoop(new OpenLoop(new Constant(speed)));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), -0.025, -0.005, 0));
		surgeRightControl.addLoop(new OpenLoop(new Constant(speed)));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		
		boolean markerFound = false;
		
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			boolean markerVisible = (boolean) Model.getInstance().getComponentValue("imageFilters.markerFilter.markerVisible");
			double angle = (double) Model.getInstance().getComponentValue("imageFilters.markerFilter.angle");
			boolean markerOriented = angle < 30 && angle > -30;
			if( markerVisible && markerOriented ) {
				markerFound = true;
				break;
			}
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		surgeLeftControl.stop();
		surgeRightControl.stop();
		heaveLeftControl.stop();
		heaveRightControl.stop();
		
		if( markerFound ) return TaskState.SUCCEEDED;
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.TIMEDOUT;
	}

}
