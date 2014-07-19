package org.auvua.agent.tasks;

import org.auvua.agent.Task;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.PIDController;
import org.auvua.model.Model;
import org.auvua.model.actuators.Motor;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class DriveConstantDepth extends Task {
	
	public double setDepth;
	public int duration;
	
	public DriveConstantDepth( double setDepth, int duration ) {
		this.setDepth = setDepth;
		this.duration = duration; // In milliseconds
	}

	@Override
	public void run() {
		state = TaskState.RUNNING;
		
		Constant constantHeading = new Constant(0);
		Constant constantDepth = new Constant(36);
		
		PIDController surgeLeftControl =  new PIDController("hardware.compass.yaw", "hardware.surgeLeft.speed", constantHeading, 1, 0, 0);
		PIDController surgeRightControl = new PIDController("hardware.compass.yaw", "hardware.surgeRight.speed", constantHeading, -1, 0, 0);
		
		PIDController heaveLeftControl =  new PIDController("hardware.depthGauge.depth", "hardware.heaveLeft.speed", constantDepth, -1, 0, 0);
		PIDController heaveRightControl = new PIDController("hardware.depthGauge.depth", "hardware.heaveRight.speed", constantDepth, -1, 0, 0);
		
		long startTime = System.currentTimeMillis();
		long lastTime = startTime;
		long currTime = startTime;
		
		do {
			model.getState();
			long timeStep = currTime - lastTime;
			
			surgeLeftControl.advanceTimestep(timeStep);
			surgeRightControl.advanceTimestep(timeStep);
			heaveLeftControl.advanceTimestep(timeStep);
			heaveRightControl.advanceTimestep(timeStep);

			model.setState();
			lastTime = currTime;
			currTime = System.currentTimeMillis();
		} while(currTime < startTime + duration);
		
		state = TaskState.SUCCEEDED;
		cleanup();
	}

}
