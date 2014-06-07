package org.auvua.model.sensors;

public class Compass {
	
	private final int id;

	public Compass(int i) {
		id = i;
	}
	
	/**
	 * 
	 * @return - heading of the AUV in degrees cw from North
	 */
	public double getHeading() {
		return 0;
	}

}
