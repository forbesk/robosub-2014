package org.auvua.agent.tasks;

import java.util.Map;

import org.auvua.agent.Task;
import org.auvua.agent.Task.TaskState;
import org.auvua.agent.vision.MarkerFilter;
import org.auvua.model.sensors.Camera;

public class AlignToMarker extends Task {
	
	public Camera camera;
	public int timeOut; // Timeout in milliseconds
	public MarkerFilter filter;
	
	public AlignToMarker( Camera camera, int timeOut ) {
		this.camera = camera;
		this.timeOut = timeOut;
		this.filter = new MarkerFilter(this.camera);
	}

	@Override
	public void run() {
		state = TaskState.RUNNING;
		
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		long alignTime = startTime; // Time when robot started to become aligned
		
		do {
			
			Map<String,Object> values = filter.captureValues();
			if((Integer) values.get("markerAngle") > 10 || (Integer) values.get("markerAngle") < -10) {
				alignTime = System.currentTimeMillis();
			}
			if( currTime > alignTime + 3000 ) {
				model.surgeLeft.stop();
				model.surgeRight.stop();
				model.heaveLeft.stop();
				model.heaveRight.stop();
				
				state = TaskState.SUCCEEDED;
				cleanup();
			}
			currTime = System.currentTimeMillis();
			
			// ROTATION CONTROL HERE
			
		} while(currTime < startTime + timeOut);
		
		model.surgeLeft.stop();
		model.surgeRight.stop();
		model.heaveLeft.stop();
		model.heaveRight.stop();
		
		state = TaskState.FAILED;
		cleanup();
	}

}
