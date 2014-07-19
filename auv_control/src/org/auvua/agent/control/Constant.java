package org.auvua.agent.control;

public class Constant implements TargetFunction {

	public double target;
	
	public Constant(double target) {
		this.target = target;
	}
	
	@Override
	public double getValue(long timeStep) {
		return target;
	}

}
