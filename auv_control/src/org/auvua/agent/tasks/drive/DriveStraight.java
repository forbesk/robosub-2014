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
public class DriveStraight extends Task {
	
	public double speed;
	public double depth;
	public double heading;
	public int duration;
	
	public DriveStraight( double speed, double depth, double heading, int duration ) {
		this.duration = duration; // In milliseconds
		this.heading = heading;
		this.depth = depth;
		this.speed = speed;
	}

	@Override
	public TaskState runTaskBody() {
		System.out.println("Driving straight!");
		
		Function constantDepth;
		if(depth == -1) {
			constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		} else {
			constantDepth = new Constant(depth);
		}
		
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new OpenLoop(new Constant(speed)));
		surgeLeftControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(heading), 0.025, 0.01, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new OpenLoop(new Constant(speed)));
		surgeRightControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(heading), -0.025, -0.01, 0));
		
		if(speed > 0.1)  {
            Model.getInstance().setComponentValue("hardware.indicatorLights.green", 1);
		} else if(speed < -0.1){
			Model.getInstance().setComponentValue("hardware.indicatorLights.red", 1);
		} else {
			Model.getInstance().setComponentValue("hardware.indicatorLights.blue", 1);
		}
		
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
