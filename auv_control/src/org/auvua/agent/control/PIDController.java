package org.auvua.agent.control;

import java.util.Map;

import org.auvua.model.Model;

public class PIDController implements Controller {
	
	private String output;
	private String input;
	private Function function;
	private double kP;
	private double kI;
	private double kD;
	
	private double error = 0;
	private double integralError = 0;
	private double derivativeError = 0;
	private double currValue = 0;
	private double lastError = 0;
	private double outputValue = 0;
	
	public PIDController( String input, String output, Function function, double kP, double kI, double kD ) {
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
		outputValue = outputValue > 1 ? 1 : outputValue;
		outputValue = outputValue < -1 ? -1 : outputValue;
		Model.getInstance().setComponentValue(output, outputValue);
	}

	@SuppressWarnings("unchecked")
	private double getError(long timeStep) {
		lastError = error;
		currValue = (double) Model.getInstance().getComponentValue(input);
		return currValue - function.getValue(timeStep);
	}
	
}
