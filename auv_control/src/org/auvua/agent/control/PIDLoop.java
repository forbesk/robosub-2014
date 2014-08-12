package org.auvua.agent.control;

import org.auvua.model.Model;

public class PIDLoop implements Loop {
	
	private String input;
	private Function function;
	private boolean stop = false;
	private double kP;
	private double kI;
	private double kD;
	
	private double error = 0;
	private double integralError = 0;
	private double derivativeError = 0;
	private double currValue = 0;
	private double lastError = 0;
	private double outputValue = 0;
	
	public PIDLoop( String input, Function function, double kP, double kI, double kD ) {
		this.input = input;
		this.function = function;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	public double getOutputValue() {
		return outputValue;
	}
	
	public void start() {
		new Thread() {
			public void run() {
				long startTime = System.currentTimeMillis();
				long lastTime = startTime;
				long currTime = startTime;
				long timeStep;
				while(!stop) {
					currTime = System.currentTimeMillis();
					timeStep = currTime - lastTime;
					
					lastError = error;
					error = getError(currTime - startTime);
					integralError += timeStep * (error) / 1000.0;
					derivativeError = (error - lastError) / (timeStep / 1000.0);
					outputValue = kP*error + kI*integralError + kD*derivativeError;
					
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
					lastTime = currTime;
				}
			}
		}.start();
	}
	
	public void stop() {
		stop = true;
	}
	
	private double getError(long time) {
		lastError = error;
		currValue = (double) Model.getInstance().getComponentValue(input);
		return currValue - function.getValue(time);
	}
	
}
