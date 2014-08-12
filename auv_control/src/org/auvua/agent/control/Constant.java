package org.auvua.agent.control;

public class Constant implements Function {

	public double target;
	
	public Constant(double target) {
		this.target = target;
	}
	
	@Override
	public double getValue(long time) {
		return target;
	}

}
