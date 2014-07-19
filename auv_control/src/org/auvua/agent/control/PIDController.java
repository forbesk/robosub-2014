package org.auvua.agent.control;

import java.util.Map;

import org.auvua.model.Model;

public class PIDController {
	
	private String output;
	private String input;
	private TargetFunction function;
	private double kP;
	private double kI;
	private double kD;
	
	private double error = 0;
	private double integralError = 0;
	private double derivativeError = 0;
	private double currValue = 0;
	private double lastError = 0;
	private double outputValue = 0;
	
	public PIDController( String input, String output, TargetFunction function, double kP, double kI, double kD ) {
		this.output = output;
		this.input = input;
		this.function = function;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	public void advanceTimestep(long timeStep) {
		if(timeStep == 0) return;
		lastError = error;
		error = getError(timeStep);
		integralError += timeStep * (error) / 1000.0;
		derivativeError = (error - lastError) / (timeStep / 1000.0);
		outputValue = kP*error + kI*integralError + kD*derivativeError;
		setOutputValue(outputValue);
	}

	@SuppressWarnings("unchecked")
	private void setOutputValue(double outputValue) {
		Model model = Model.getInstance();
		Map<String,Object> component = model.robot;
		String[] strings = output.split(".");
		for(int i = 0; i < strings.length - 1; i++)
			component = (Map<String, Object>) component.get(strings[i]);
		component.put(strings[strings.length-1], outputValue);
	}

	@SuppressWarnings("unchecked")
	private double getError(long timeStep) {
		lastError = error;
		Model model = Model.getInstance();
		Map<String,Object> component = model.robot;
		String[] strings = input.split(".");
		for(int i = 0; i < strings.length - 1; i++)
			component = (Map<String, Object>) component.get(strings[i]);
		currValue = (double) component.get(strings[strings.length-1]);
		return currValue - function.getValue(timeStep);
	}
	
}
