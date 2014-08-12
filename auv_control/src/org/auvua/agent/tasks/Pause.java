package org.auvua.agent.tasks;

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

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class Pause extends Task {
	
	public int duration;
	
	public Pause( int duration ) {
		this.duration = duration; // In milliseconds
	}

	@Override
	public TaskState runTaskBody() {
		
		Function constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), 0.025, 0.005, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), -0.025, -0.005, 0));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		surgeLeftControl.stop();
		surgeRightControl.stop();
		heaveLeftControl.stop();
		heaveRightControl.stop();
		
		Model.getInstance().setComponentValue("hardware.indicatorLights.blue", 0);
		Model.getInstance().setComponentValue("hardware.indicatorLights.green", 0);
		Model.getInstance().setComponentValue("hardware.indicatorLights.red", 0);
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.SUCCEEDED;
	}

}
