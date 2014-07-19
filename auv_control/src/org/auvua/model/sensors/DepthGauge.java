package org.auvua.model.sensors;

import org.auvua.model.RobotComponent;

public class DepthGauge implements RobotComponent {
	
	private final int id;

	public DepthGauge(int i) {
		id = i;
	}
	
	/**
	 * 
	 * @return - depth of the AUV in inches
	 */
	public double getDepth() {
		return 0;
	}

}
