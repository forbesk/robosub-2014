package org.auvua.model;

import org.auvua.model.actuators.Motor;
import org.auvua.model.sensors.DepthGauge;

public class Model implements Runnable {

	public Motor surgeLeft = new Motor(1);
	public Motor surgeRight = new Motor(2);
	public Motor heaveLeft = new Motor(3);
	public Motor heaveRight = new Motor(4);
	public Motor sway = new Motor(5);
	
	public DepthGauge depthGauge = new DepthGauge(6);
	
	public void run() {
		
		new Thread() {
			public void run() {
				while(true) {
					
					
					
				}
			}
		}.start();
		
		
	}

}
