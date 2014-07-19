package org.auvua.sim;

import java.util.Map;

import org.auvua.model.Model;

public class MockHardware {
	
	public double[] pos = {0,0,0};
	public double[] vel = {0,0,0};
	
	public double[] angle = {0,0,0};
	public double[] omega = {0,0,0};
	
	public double[] force = {0,0,0};
	public double[] tau = {0,0,0};
	
	public double mass = 30;
	public double inertiaMoment = 5;
	
	public long lastTime = System.currentTimeMillis();
	public long currTime = System.currentTimeMillis();
	
	private static final MockHardware instance = new MockHardware();
	
	public MockHardware() {}
	
	public static MockHardware getInstance() {
		return instance;
	}

	public void getState() {
		// TODO Auto-generated method stub
		
	}
	
	public void setState() {
		long deltaT = currTime - lastTime;
		// TODO Auto-generated method stub
		Map<String,Object> robot = Model.getInstance().robot;
		double surgeLeft = (double) Model.getComponentValue("hardware.surgeLeft.speed", robot) * 100;
		double surgeRight = (double) Model.getComponentValue("hardware.surgeRight.speed", robot) * 100;
		double heaveLeft = (double) Model.getComponentValue("hardware.heaveLeft.speed", robot) * 100;
		double heaveRight = (double) Model.getComponentValue("hardware.heaveRight.speed", robot) * 100;
		double sway = (double) Model.getComponentValue("hardware.sway.speed", robot) * 40;
		
		force[0] = (surgeLeft + surgeRight);
		force[1] = sway;
		force[2] = heaveLeft + heaveRight;
		
		tau[0] = heaveLeft * .25 - heaveRight * .25;
		tau[1] = 0;
		tau[2] = surgeRight * .25 - surgeLeft * .25;
		
		vel[0] = vel[0] + force[0] / mass * deltaT / 1000.0;
		vel[1] = vel[1] + force[1] / mass * deltaT / 1000.0;
		vel[2] = vel[2] + force[2] / mass * deltaT / 1000.0;
		
		pos[0] = pos[0] + vel[0] * deltaT / 1000.0;
		pos[1] = pos[1] + vel[1] * deltaT / 1000.0;
		pos[2] = pos[2] + vel[2] * deltaT / 1000.0;
		
		omega[0] = omega[0] + tau[0] / inertiaMoment * deltaT / 1000.0;
		omega[1] = omega[1] + tau[1] / inertiaMoment * deltaT / 1000.0;
		omega[2] = omega[2] + tau[2] / inertiaMoment * deltaT / 1000.0;
		
		angle[0] = angle[0] + omega[0] * deltaT / 1000.0;
		angle[1] = angle[1] + omega[1] * deltaT / 1000.0;
		angle[2] = angle[2] + omega[2] * deltaT / 1000.0;
	}
}
