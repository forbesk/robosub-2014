package org.auvua.agent.tasks.drive;

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
public class RunMotors extends Task {
	
	public int duration;
	public Function function;
	
	public RunMotors( int duration, Function function ) {
		this.duration = duration; // In milliseconds
		this.function = function;
	}

	@Override
	public TaskState runTaskBody() {
		
		Model.getInstance().getGyroIntegrator().resetHeading();
		
		Controller surgeLeftControl =  new OpenLoopController("hardware.surgeLeft.speed", function);
		Controller surgeRightControl = new OpenLoopController("hardware.surgeRight.speed", function);
		
		surgeLeftControl.start();
		surgeRightControl.start();
		
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		surgeLeftControl.stop();
		surgeRightControl.stop();
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.SUCCEEDED;
	}

}
