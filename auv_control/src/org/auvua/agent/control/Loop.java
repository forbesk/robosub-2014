package org.auvua.agent.control;

public interface Loop {
	public double getOutputValue();
	
	public void start();
	
	public void stop();
}
