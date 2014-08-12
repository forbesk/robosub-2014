package org.auvua.agent.tasks.special;

import org.auvua.agent.Task;
import org.auvua.agent.Task.TaskState;
import org.auvua.agent.control.Constant;
import org.auvua.agent.control.Controller;
import org.auvua.agent.control.OpenLoopController;
import org.auvua.model.Model;

/**
 * A task to drive forward at full speed and maintain a certain depth
 */
public class ViewSensors extends Task {
	
	private int duration;
	
	public ViewSensors( int duration ) {
		this.duration = duration;
	}

	@Override
	public TaskState runTaskBody() {
		
		long startTime = System.currentTimeMillis();
		long lastTime = startTime;
		long currTime = startTime;
		
		double angleX = 0.0;
		double angleY = 0.0;
		double angleZ = 0.0;
		
		double driftSum = 0;
		int count = 0;
		
		System.out.println("Calibrating gyro...");
		while(System.currentTimeMillis() < startTime + 5000 && !interrupted) {
			driftSum += (double) Model.getInstance().getComponentValue("hardware.compass.gyroZ");
			count++;
			try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		double drift = driftSum / count;
		System.out.println("Calibration complete!");
		
		startTime = System.currentTimeMillis();
		lastTime = startTime;
		currTime = startTime;
		
		do {
			long timeStep = currTime - lastTime;
			
			double gyroX = (double) Model.getInstance().getComponentValue("hardware.compass.gyroX") + 1.133 * Math.PI / 180;
			double gyroY = (double) Model.getInstance().getComponentValue("hardware.compass.gyroY") - 0.559 * Math.PI / 180;
			double gyroZ = (double) Model.getInstance().getComponentValue("hardware.compass.gyroZ") - drift;
			double heading = (double) Model.getInstance().getComponentValue("hardware.compass.heading");
			
			angleX += (gyroX) * timeStep / 1000 * 180 / Math.PI;
			angleY += gyroY * timeStep / 1000 * 180 / Math.PI;
			angleZ += gyroZ * timeStep / 1000 * 180 / Math.PI;
			
			//System.out.println("" + ((currTime - startTime) / 1000.0) + "\t" + angleX + "\t" + angleY + "\t" + angleZ + "\t" + heading);
			
			if(lastTime % 1000 > currTime % 1000) {
				System.out.println(angleZ + "\t" + heading);
			}

			try { Thread.sleep(50); } catch (InterruptedException e) {	}
			
			lastTime = currTime;
			currTime = System.currentTimeMillis();

		} while(currTime < startTime + duration && !interrupted);
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.SUCCEEDED;
	}

}
