package org.auvua.model;

import java.util.HashMap;
import java.util.Map;

public class ComponentFactory {

	public static Map<String,Object> create( Component c ) {
		Map<String,Object> component = new HashMap<String,Object>();
		
		switch(c) {
		case BATTERY:
			component.put("voltage", 0.0);
			component.put("current", 0.0);
			break;
		case CAMERA:
			break;
		case COMPASS:
			component.put("accelX", 0.0);
			component.put("accelY", 0.0);
			component.put("accelZ", 0.0);
			component.put("gyroX", 0.0);
			component.put("gyroY", 0.0);
			component.put("gyroZ", 0.0);
			component.put("roll", 0.0);
			component.put("pitch", 0.0);
			component.put("heading", 0.0);
			component.put("gyroHeading", 0.0);
			component.put("reset", 0L);
			break;
		case DEPTHGAUGE:
			component.put("depth", 0.0);
			break;
		case HUMIDITY_SENSOR:
			component.put("humidity", 0.0);
			break;
		case HYDROPHONE_BOARD:
			component.put("heading", 0.0);
			component.put("inclination", 0.0);
			break;
		case INDICATOR_LIGHTS:
			component.put("red", 0.0);
			component.put("green", 0.0);
			component.put("blue", 0.0);
			component.put("white", 0.0);
			break;
		case MOTOR:
			component.put("speed", 0.0);
			break;
		case SWITCH:
			component.put("on", 0L);
			break;
		}
		
		return component;
	}
	
}
