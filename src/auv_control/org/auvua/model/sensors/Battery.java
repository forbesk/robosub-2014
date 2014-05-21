package org.auvua.model.sensors;

public class Battery {
	
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
