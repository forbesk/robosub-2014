package org.auvua.model.sensors;

import org.auvua.model.RobotComponent;

public class Battery implements RobotComponent {
	
	private double voltage;

	public Battery() {
		voltage = -1;
	}
	
	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

}
