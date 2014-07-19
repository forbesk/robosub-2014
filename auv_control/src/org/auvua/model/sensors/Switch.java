package org.auvua.model.sensors;

import org.auvua.model.RobotComponent;

public class Switch implements RobotComponent {
	private boolean state = false;
	
	public Switch(boolean initState) {
		state = initState;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public boolean getState() {
		return state;
	}
}
