package org.auvua.agent.tasks.special;

import org.auvua.agent.Task;
import org.auvua.agent.Task.TaskState;
import org.auvua.agent.control.AggregateController;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.Controller;
import org.auvua.agent.control.Function;
import org.auvua.agent.control.OpenLoop;
import org.auvua.agent.control.OpenLoopController;
import org.auvua.agent.control.PIDController;
import org.auvua.agent.control.PIDLoop;
import org.auvua.model.Model;
import org.auvua.vision.ManeuverFilter;

/**
 * 
 * @author root
 * A task which runs into the maneuver pole and spins around it
 */
public class CircumnavigateSimple extends Task {
	
	public int duration;
	public double speed;
	public double depth;
	
	public CircumnavigateSimple( double speed, double depth, int duration ) {
		this.duration = duration;
		this.depth = depth;
		this.speed = speed;
	}

	@Override
	public TaskState runTaskBody() {
		
		Function constantDepth;
		if(depth == -1) {
			constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		} else {
			constantDepth = new Constant(depth);
		}
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, 0.025, 0.0, 0));
		
		Controller surgeLeftControl =  new OpenLoopController("hardware.surgeLeft.speed", new Constant(speed - .05));
		Controller surgeRightControl = new OpenLoopController("hardware.surgeRight.speed", new Constant(speed + .05));

		Controller swayControl = new OpenLoopController("hardware.sway.speed", new Constant(.5));

		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		swayControl.start();
		
		boolean success = false;
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			System.out.println(Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
			if( (double) Model.getInstance().getComponentValue("hardware.compass.gyroHeading") > 360 ) {
				success = true;
				break;
			}
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		surgeLeftControl.stop();
		surgeRightControl.stop();
		heaveLeftControl.stop();
		heaveRightControl.stop();
		swayControl.stop();
		
		if(success) return TaskState.SUCCEEDED;
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.TIMEDOUT;
	}

}
