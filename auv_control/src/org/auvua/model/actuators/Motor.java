package org.auvua.model.actuators;

import org.auvua.model.RobotComponent;

public class Motor implements RobotComponent {
	
	private final int id;
	private double speed;
	public static double FULLFORWARD = 1.0;
	public static double FULLBACKWARD = -1.0;
	
	public Motor( int id ) {
		this.id = id;
		this.speed = 0;
	}
	
	public void setSpeed( double setSpeed ) {
		this.speed = setSpeed;
		this.speed = this.speed > 1 ? 1 : this.speed;
		this.speed = this.speed < -1 ? -1 : this.speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void stop() {
		speed = 0;
	}
	
	public int getId() {
		return id;
	}
	
}
