package org.auvua.agent.tasks.drive;

import org.auvua.agent.Task;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.PIDController;
import org.auvua.agent.control.PIDLoop;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class ConstantDepth extends Task {
	
	public double depth;
	public int duration;
	
	public ConstantDepth( double depth, int duration ) {
		this.depth = depth;
		this.duration = duration; // In milliseconds
	}

	@Override
	public TaskState runTaskBody() {
		Constant constantDepth = new Constant(depth);
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.06, 0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.06, 0, 0));
		
		heaveLeftControl.start();
		heaveRightControl.start();
		
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}

		heaveLeftControl.stop();
		heaveRightControl.stop();
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.SUCCEEDED;
	}

}
