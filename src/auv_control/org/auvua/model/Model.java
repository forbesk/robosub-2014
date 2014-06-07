package org.auvua.model;

import org.auvua.model.actuators.Motor;
import org.auvua.model.sensors.Compass;
import org.auvua.model.sensors.DepthGauge;
import org.auvua.model.sensors.Switch;

public class Model implements Runnable {

	public static Motor surgeLeft = new Motor(1);
	public static Motor surgeRight = new Motor(2);
	public static Motor heaveLeft = new Motor(3);
	public static Motor heaveRight = new Motor(4);
	public static Motor sway = new Motor(5);
	
	public static DepthGauge depthGauge = new DepthGauge(6);
	public static Compass compass = new Compass(7);
	public static Switch missionSwitch = new Switch(false);
	public static Switch killSwitch = new Switch(false);
	
	public void run() {
		
		new Thread() {
			public void run() {
				while(true) {
					
					
					
				}
			}
		}.start();
		
		
	}

}
