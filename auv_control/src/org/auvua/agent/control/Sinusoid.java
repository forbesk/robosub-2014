package org.auvua.agent.control;

public class Sinusoid implements Function {
	
	private double period;
	private double phase; // degrees
	private double amplitude;
	
	public Sinusoid( double period, double phase, double amplitude ) {
		this.period = period;
		this.phase = phase;
		this.amplitude = amplitude;
	}

	@Override
	public double getValue(long time) {
		return amplitude * Math.sin( (2 * Math.PI) / period * time - phase * Math.PI / 180 )  ;
	}

}
