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
import org.auvua.model.Model;
import org.auvua.vision.ManeuverFilter;
import org.auvua.vision.ManeuverRedPoleFilter;
import org.auvua.vision.RedBuoyFilter;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class SearchForManeuverPole extends Task {
	
	public int duration;
	public double speed;
	public double depth;
	
	public SearchForManeuverPole( double speed, double depth, int duration ) {
		this.duration = duration;
		this.depth = depth;
		this.speed = speed;
	}

	@Override
	public TaskState runTaskBody() {
		System.out.println("Circumnavigating!");

		Function constantDepth;
		if(depth == -1) {
			constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		} else {
			constantDepth = new Constant(depth);
		}
		
		Model.getInstance().addImageFilter("frontCamera", "maneuverFilter", new ManeuverRedPoleFilter());
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new OpenLoop(new Constant(speed)));
		surgeLeftControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), 0.025, 0.01, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new OpenLoop(new Constant(speed)));
		surgeRightControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), -0.025, -0.01, 0));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		
		boolean success = false;
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			double width = (double) Model.getInstance().getComponentValue("imageFilters.maneuverFilter.width");
			boolean visible = (boolean) Model.getInstance().getComponentValue("imageFilters.maneuverFilter.poleVisible");
			
			if( visible && width > 200 ) {
				success = true;
				break;
			}
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		surgeLeftControl.stop();
		surgeRightControl.stop();
		heaveLeftControl.stop();
		heaveRightControl.stop();
		
		if(success) return TaskState.SUCCEEDED;
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.TIMEDOUT;
	}

}
