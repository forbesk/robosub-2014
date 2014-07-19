package org.auvua.sim;

import java.util.ArrayList;

public class PhysicalObject {
	
	public double mass;
	public double inertiaMoment;
	public ArrayList<PhysicalObject> children = new ArrayList<PhysicalObject>();
	
	public PhysicalObject(double mass, double inertiaMoment) {
		this.mass = mass;
		this.inertiaMoment = inertiaMoment;
	}
	
	public void addChild(PhysicalObject child) {
		children.add(child);
	}
}
