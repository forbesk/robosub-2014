package org.auvua.agent.tasks.drive;

import org.auvua.agent.Task;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.PIDController;
import org.auvua.agent.control.PIDLoop;
import org.auvua.model.Model;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class ConstantHeadingAndDepth extends Task {
	
	public double depth;
	public double heading;
	public int duration;
	
	public ConstantHeadingAndDepth( double depth, double heading, int duration ) {
		this.depth = depth;
		this.heading = heading;
		this.duration = duration; // In milliseconds
	}

	@Override
	public TaskState runTaskBody() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		heading = (double) Model.getInstance().getComponentValue("hardware.compass.heading");
		
		Constant constantHeading = new Constant(heading);
		Constant constantDepth = new Constant(depth);
		
		PIDController surgeLeftControl =  new PIDController("hardware.surgeLeft.speed", new PIDLoop("hardware.compass.heading", constantHeading, -0.01, 0, 0) );
		PIDController surgeRightControl = new PIDController("hardware.surgeRight.speed", new PIDLoop("hardware.compass.heading", constantHeading, 0.01, 0, 0));
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.03, 0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.03, 0, 0));
		
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
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.SUCCEEDED;
	}

}
