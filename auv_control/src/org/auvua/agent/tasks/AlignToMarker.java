package org.auvua.agent.tasks;

import org.auvua.agent.Task;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.PIDController;
import org.auvua.model.Model;
import org.auvua.vision.FloorMarkerFilter;

public class AlignToMarker extends Task {
	
	private long duration;
	
	public AlignToMarker( long duration ) {
		this.duration = duration;
	}
	
	@Override
	public TaskState runTaskBody() {
		Constant constantHeading = new Constant(0);
		
		Model.getInstance().addImageFilter("frontCamera", "markerFilter", new FloorMarkerFilter());

		PIDController heaveLeftControl =  new PIDController("imageFilters.markerFilter.angle", "hardware.heaveLeft.speed", constantHeading, .5, 0, 0);
		PIDController heaveRightControl = new PIDController("imageFilters.markerFilter.angle", "hardware.heaveRight.speed", constantHeading, .5, 0, 0);

		long startTime = System.currentTimeMillis();
		long lastTime = startTime;
		long currTime = startTime;
		
		do {
			long timeStep = currTime - lastTime;
			
			heaveLeftControl.advanceTimestep(timeStep);
			heaveRightControl.advanceTimestep(timeStep);

			lastTime = currTime;
			currTime = System.currentTimeMillis();
			
			try { Thread.sleep(50); } catch (InterruptedException e) {	}
		} while(currTime < startTime + duration);
		
		Model.getInstance().setComponentValue("hardware.surgeLeft.speed", 0);
		Model.getInstance().setComponentValue("hardware.heaveLeft.speed", 0);
		Model.getInstance().setComponentValue("hardware.heaveRight.speed", 0);
		Model.getInstance().setComponentValue("hardware.surgeRight.speed", 0);
		
		return TaskState.SUCCEEDED;
	}

}
