package org.auvua.agent.tasks.drive;

import org.auvua.agent.Task;
import org.auvua.agent.control.AggregateController;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.OpenLoop;
import org.auvua.agent.control.PIDController;
import org.auvua.agent.control.PIDLoop;
import org.auvua.model.Model;
import org.auvua.vision.FloorMarkerFilter;
import org.auvua.vision.RedBuoyFilter;

public class DriveTowardsRedBuoy extends Task {
	
	private long duration;
	
	public DriveTowardsRedBuoy( long duration ) {
		this.duration = duration;
	}
	
	@Override
	public TaskState runTaskBody() {
		
		Model.getInstance().addImageFilter("frontCamera", "redBuoyFilter", new RedBuoyFilter());
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new OpenLoop(new Constant(.1)));
		surgeLeftControl.addLoop(new PIDLoop("imageFilters.redBuoyFilter.xPosition", new Constant(320), .00075, 0, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new OpenLoop(new Constant(.1)));
		surgeRightControl.addLoop(new PIDLoop("imageFilters.redBuoyFilter.xPosition", new Constant(320), -.00075, 0, 0));
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("imageFilters.redBuoyFilter.yPosition", new Constant(240), -0.00075, 0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("imageFilters.redBuoyFilter.yPosition", new Constant(240), -0.00075, 0, 0));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		
		int screenSize = 640 * 480;
		boolean hit = true;
		
		long startTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			hit = (double) Model.getInstance().getComponentValue("imageFilters.redBuoyFilter.size") > screenSize * .5;
			if( hit ) break;
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		surgeLeftControl.stop();
		surgeRightControl.stop();
		heaveLeftControl.stop();
		heaveRightControl.stop();
		
		if(hit) return TaskState.SUCCEEDED;
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.SUCCEEDED;
	}

}
