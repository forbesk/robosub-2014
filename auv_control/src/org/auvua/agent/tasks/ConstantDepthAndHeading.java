package org.auvua.agent.tasks;

import org.auvua.agent.Task;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.PIDController;
import org.auvua.model.Model;
import org.auvua.model.actuators.Motor;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class ConstantDepthAndHeading extends Task {
	
	public double depth;
	public double heading;
	public int duration;
	
	public ConstantDepthAndHeading( double depth, double heading, int duration ) {
		this.depth = depth;
		this.heading = heading;
		this.duration = duration; // In milliseconds
	}

	@Override
	public void run() {
		state = TaskState.RUNNING;
		
		Constant constantHeading = new Constant(heading);
		Constant constantDepth = new Constant(depth);
		
		PIDController surgeLeftControl =  new PIDController("hardware.compass.heading", "hardware.surgeLeft.speed", constantHeading, 0.01, 0, 0);
		PIDController surgeRightControl = new PIDController("hardware.compass.heading", "hardware.surgeRight.speed", constantHeading, -0.01, 0, 0);
		
		PIDController heaveLeftControl =  new PIDController("hardware.depthGauge.depth", "hardware.heaveLeft.speed", constantDepth, -0.1, 0, 0);
		PIDController heaveRightControl = new PIDController("hardware.depthGauge.depth", "hardware.heaveRight.speed", constantDepth, -0.1, 0, 0);
		
		long startTime = System.currentTimeMillis();
		long lastTime = startTime;
		long currTime = startTime;
		
		double brightness = 0;
		
		do {
			long timeStep = currTime - lastTime;
			
			surgeLeftControl.advanceTimestep(timeStep);
			surgeRightControl.advanceTimestep(timeStep);
			heaveLeftControl.advanceTimestep(timeStep);
			heaveRightControl.advanceTimestep(timeStep);
			
			brightness = Math.abs(depth - (double)Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
			
			Model.getInstance().setComponentValue("hardware.indicatorLights.blue", brightness/30.0);

			lastTime = currTime;
			currTime = System.currentTimeMillis();
			
			try { Thread.sleep(50); } catch (InterruptedException e) {	}
		} while(currTime < startTime + duration);
		
		Model.getInstance().setComponentValue("hardware.surgeLeft.speed", 0);
		Model.getInstance().setComponentValue("hardware.heaveLeft.speed", 0);
		Model.getInstance().setComponentValue("hardware.heaveRight.speed", 0);
		Model.getInstance().setComponentValue("hardware.surgeRight.speed", 0);
		
		state = TaskState.SUCCEEDED;
		cleanup();
	}

}
