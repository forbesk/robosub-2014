package org.auvua.agent.tasks;

import org.auvua.agent.Task;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.Controller;
import org.auvua.agent.control.OpenLoopController;
import org.auvua.model.Model;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class DriveStraight extends Task {
	
	public double speed;
	public int duration;
	
	public DriveStraight( double speed, int duration ) {
		this.duration = duration; // In milliseconds
		this.speed = speed;
	}

	@Override
	public TaskState runTaskBody() {
		
		Controller surgeLeftControl =  new OpenLoopController("hardware.surgeLeft.speed", new Constant(speed));
		Controller surgeRightControl = new OpenLoopController("hardware.surgeRight.speed", new Constant(speed));
		
		long startTime = System.currentTimeMillis();
		long lastTime = startTime;
		long currTime = startTime;
		
		if(speed > 0.1)  {
            Model.getInstance().setComponentValue("hardware.indicatorLights.green", 1);
		} else if(speed < -0.1){
			Model.getInstance().setComponentValue("hardware.indicatorLights.red", 1);
		} else {
			Model.getInstance().setComponentValue("hardware.indicatorLights.blue", 1);
		}
		
		do {
			long timeStep = currTime - lastTime;
			
			surgeLeftControl.advanceTimestep(timeStep);
			surgeRightControl.advanceTimestep(timeStep);

			try { Thread.sleep(50); } catch (InterruptedException e) {	}
			
			lastTime = currTime;
			currTime = System.currentTimeMillis();

		} while(currTime < startTime + duration);
		
		Model.getInstance().setComponentValue("hardware.indicatorLights.blue", 0);
		Model.getInstance().setComponentValue("hardware.indicatorLights.green", 0);
		Model.getInstance().setComponentValue("hardware.indicatorLights.red", 0);
		
		Model.getInstance().setComponentValue("hardware.surgeLeft.speed", 0);
		Model.getInstance().setComponentValue("hardware.surgeRight.speed", 0);
		
		return TaskState.SUCCEEDED;
	}

}
