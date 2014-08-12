package org.auvua.agent.tasks.align;

import org.auvua.agent.Task;
import org.auvua.agent.control.AggregateController;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.PIDController;
import org.auvua.agent.control.PIDLoop;
import org.auvua.model.Model;
import org.auvua.vision.FloorMarkerFilter;

public class AlignToMarker extends Task {
	
	private long duration;
	
	public AlignToMarker( long duration ) {
		this.duration = duration;
	}
	
	@Override
	public TaskState runTaskBody() {
		
		double rotationKp = 0.005; // Power per degree
		double surgeKp = -.0015; // Power per pixel
		double swayKp = .0015; // Power per pixel
		double swayKi = .001; // Power per pixel-second
		double depthKp = 0.06; // Power per inch
		
		int alignTimeThreshold = 2000; // Milliseconds
		double angleThreshold = 5; // Degrees, plus or minus
		double positionThreshold = 100; // Pixels from screen center
		
		// Marker angle is in degrees
		// Marker position is in pixels
		Constant constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		
		Model.getInstance().addImageFilter("bottomCamera", "markerFilter", new FloorMarkerFilter());
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new PIDLoop("imageFilters.markerFilter.angle", new Constant(0), rotationKp, 0, 0));
		surgeLeftControl.addLoop(new PIDLoop("imageFilters.markerFilter.yPosition", new Constant(240), surgeKp, 0, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new PIDLoop("imageFilters.markerFilter.angle", new Constant(0), -rotationKp, 0, 0));
		surgeRightControl.addLoop(new PIDLoop("imageFilters.markerFilter.yPosition", new Constant(240), surgeKp, 0, 0));
		
		PIDController swayControl = new PIDController("hardware.sway.speed", new PIDLoop("imageFilters.markerFilter.xPosition", new Constant(320), swayKp, swayKi, 0));
		
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, depthKp, 0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, depthKp, 0, 0));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		swayControl.start();
		
		boolean success = false;
		
		long startTime = System.currentTimeMillis();
		long successAlignStartTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			double angle = (double) Model.getInstance().getComponentValue("imageFilters.markerFilter.angle");
			double x = (double) Model.getInstance().getComponentValue("imageFilters.markerFilter.xPosition");
			double y = (double) Model.getInstance().getComponentValue("imageFilters.markerFilter.yPosition");
			

			
			System.out.println("angle: " + angle);
			boolean markerVisible = (boolean) Model.getInstance().getComponentValue("imageFilters.markerFilter.markerVisible");
			boolean withinAngle = angle > -angleThreshold && angle < angleThreshold;
			boolean withinCircle = Math.hypot((320 - x), (240 - y)) < positionThreshold;
			
			if(markerVisible) {
				Model.getInstance().setComponentValue("hardware.indicatorLights.blue", 1.0);
			} else {
				Model.getInstance().setComponentValue("hardware.indicatorLights.blue", 0.0);
			}
			
			if( !(withinCircle) ) {
				successAlignStartTime = System.currentTimeMillis();
				Model.getInstance().setComponentValue("hardware.indicatorLights.red", 1.0);
				Model.getInstance().setComponentValue("hardware.indicatorLights.green", 0.0);
			} else {
				Model.getInstance().setComponentValue("hardware.indicatorLights.green", 1.0);
				Model.getInstance().setComponentValue("hardware.indicatorLights.red", 0.0);
			}
			
			if( !(markerVisible && withinAngle && withinCircle) ) {
				successAlignStartTime = System.currentTimeMillis();
			}
			if( System.currentTimeMillis() - successAlignStartTime > alignTimeThreshold ) {
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
		
		Model.getInstance().setComponentValue("hardware.indicatorLights.red", 0.0);
		Model.getInstance().setComponentValue("hardware.indicatorLights.blue", 0.0);
		Model.getInstance().setComponentValue("hardware.indicatorLights.green", 0.0);
		
		if(success) return TaskState.SUCCEEDED;
		return interrupted ? TaskState.INTERRUPTED : TaskState.TIMEDOUT;
	}

}
