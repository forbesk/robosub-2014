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
import org.auvua.vision.RedBuoyFilter;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class SearchForRedBuoy extends Task {
	
	public int duration;
	
	public SearchForRedBuoy( int duration ) {
		this.duration = duration; // In milliseconds
	}

	@Override
	public TaskState runTaskBody() {
		System.out.println("Where's the buoy?");
		Model.getInstance().addImageFilter("frontCamera", "redBuoyFilter", new RedBuoyFilter());
		
		Function constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Sinusoid(30000, 0, 30), 0.025, 0.005, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Sinusoid(30000, 0, 30), -0.025, -0.005, 0));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		
		boolean found = false;
		int bound = 50;
		
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			boolean buoyVisible = (boolean) Model.getInstance().getComponentValue("imageFilters.redBuoyFilter.buoyVisible");
			double xPos = (double) Model.getInstance().getComponentValue("imageFilters.redBuoyFilter.xPosition");
			boolean buoyCentered = Math.abs(xPos - 320) < bound;
			if( buoyVisible && buoyCentered ) break;
			if(found) {
				break;
			}
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		surgeLeftControl.stop();
		surgeRightControl.stop();
		heaveLeftControl.stop();
		heaveRightControl.stop();
		
		if( (boolean) Model.getInstance().getComponentValue("imageFilters.redBuoyFilter.buoyVisible") ) {
			return TaskState.SUCCEEDED;
		}
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.TIMEDOUT;
	}

}
