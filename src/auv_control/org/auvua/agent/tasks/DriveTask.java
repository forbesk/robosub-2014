package org.auvua.agent.tasks;

import org.auvua.agent.Task;
import org.auvua.model.actuators.Motor;

public class DriveTask extends Task {
	
	public double setDepth;
	public int duration;
	
	public DriveTask( double setDepth, int duration ) {
		this.setDepth = setDepth;
		this.duration = duration; // In milliseconds
	}

	@Override
	public void run() {
		state = TaskState.RUNNING;
		
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() < startTime + duration) {
			double depth = model.depthGauge.getDepth();
			model.surgeLeft.setSpeed(Motor.FULLFORWARD);
			model.surgeRight.setSpeed(Motor.FULLFORWARD);
			
			double speed = (setDepth - depth) / 12;
			model.heaveLeft.setSpeed(speed);
			model.heaveRight.setSpeed(speed);
		}
		
		model.surgeLeft.stop();
		model.surgeRight.stop();
		model.heaveLeft.stop();
		model.heaveRight.stop();
		
		state = TaskState.SUCCEEDED;
		cleanup();
	}

}
