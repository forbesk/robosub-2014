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
public class Rotate extends Task {
	
	public double targetAngle;
	
	public Rotate( double angle ) {
		this.targetAngle = angle;
	}

	@Override
	public TaskState runTaskBody() {
		
		Function constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(targetAngle), 0.025, 0.005, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(targetAngle), -0.025, -0.005, 0));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		
		boolean success = false;
		long lastAlignTime = System.currentTimeMillis();
		double angleThreshold = 5;
		
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + 60000 && !interrupted ) {
			double currHeading = (double) Model.getInstance().getComponentValue("hardware.compass.gyroHeading");
			if( currHeading < targetAngle - angleThreshold || currHeading > targetAngle + angleThreshold ) {
				lastAlignTime = System.currentTimeMillis();
			}
			if( System.currentTimeMillis() > lastAlignTime + 5000 ) success = true;
				
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
