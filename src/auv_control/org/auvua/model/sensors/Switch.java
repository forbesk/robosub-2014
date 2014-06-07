package org.auvua.model.sensors;

public class Switch {
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
