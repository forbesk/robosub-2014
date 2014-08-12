package org.auvua.agent.tasks.align;

import org.auvua.agent.Task;
import org.auvua.agent.control.AggregateController;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.PIDController;
import org.auvua.agent.control.PIDLoop;
import org.auvua.model.Model;
import org.auvua.vision.FloorMarkerFilter;
import org.auvua.vision.RedDownBuoyFilter;

public class PositionOverBuoy extends Task {
	
	private long duration;
	
	public PositionOverBuoy( long duration ) {
		this.duration = duration;
	}
	
	@Override
	public TaskState runTaskBody() {
		
		double rotationKp = 0.005; // Power per degree
		double surgeKp = -.0025; // Power per pixel
		double swayKp = .005; // Power per pixel
		double depthKp = 0.06; // Power per inch
		
		int positionTimeThreshold = 1000; // Milliseconds
		double positionThreshold = 100; // Pixels from screen center
		
		Constant constantDepth = new Constant((double) Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
		PIDController heaveLeftControl =  new PIDController("hardware.heaveLeft.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, depthKp, 0, 0));
		PIDController heaveRightControl = new PIDController("hardware.heaveRight.speed", new PIDLoop("hardware.depthGauge.depth", constantDepth, depthKp, 0, 0));
		
		Model.getInstance().addImageFilter("bottomCamera", "downBuoyFilter", new RedDownBuoyFilter());
		
		AggregateController surgeLeftControl =  new AggregateController("hardware.surgeLeft.speed");
		surgeLeftControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), rotationKp, 0, 0));
		surgeLeftControl.addLoop(new PIDLoop("imageFilters.downBuoyFilter.yPosition", new Constant(240), surgeKp, 0, 0));
		
		AggregateController surgeRightControl = new AggregateController("hardware.surgeRight.speed");
		surgeRightControl.addLoop(new PIDLoop("hardware.compass.gyroHeading", new Constant(0), -rotationKp, 0, 0));
		surgeRightControl.addLoop(new PIDLoop("imageFilters.downBuoyFilter.yPosition", new Constant(240), surgeKp, 0, 0));
		
		PIDController swayControl = new PIDController("hardware.sway.speed", new PIDLoop("imageFilters.downBuoyFilter.xPosition", new Constant(320), swayKp, 0, 0));
		
		surgeLeftControl.start();
		surgeRightControl.start();
		heaveLeftControl.start();
		heaveRightControl.start();
		swayControl.start();
		
		boolean success = false;
		
		long startTime = System.currentTimeMillis();
		long successAlignStartTime = System.currentTimeMillis();
		while( System.currentTimeMillis() < startTime + duration && !interrupted ) {
			double x = (double) Model.getInstance().getComponentValue("imageFilters.downBuoyFilter.xPosition");
			double y = (double) Model.getInstance().getComponentValue("imageFilters.downBuoyFilter.yPosition");
			
			boolean withinCircle = Math.hypot((320 - x), (240 - y)) < positionThreshold;
			
			if((boolean) Model.getInstance().getComponentValue("imageFilters.downBuoyFilter.buoyVisible")) {
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
			if( System.currentTimeMillis() - successAlignStartTime > positionTimeThreshold ) {
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
