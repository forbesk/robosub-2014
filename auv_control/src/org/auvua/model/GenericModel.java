package org.auvua.model;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class GenericModel {
	
	@SuppressWarnings("unchecked")
	public static void main( String[] args ) {
		
		Map<String,Object> hardware = newMap();
		hardware.put("surgeLeft",     ComponentFactory.create(Component.MOTOR));
		hardware.put("surgeRight",    ComponentFactory.create(Component.MOTOR));
		hardware.put("heaveLeft",     ComponentFactory.create(Component.MOTOR));
		hardware.put("heaveRight",    ComponentFactory.create(Component.MOTOR));
		hardware.put("sway",          ComponentFactory.create(Component.MOTOR));
		hardware.put("depthGauge",    ComponentFactory.create(Component.DEPTHGAUGE));
		hardware.put("compass",       ComponentFactory.create(Component.COMPASS));
		hardware.put("missionSwitch", ComponentFactory.create(Component.SWITCH));
		hardware.put("killSwitch",    ComponentFactory.create(Component.SWITCH));
		
		Map<String,Object> imageFilters = newMap();
		
		Map<String,Object> controlLoops = newMap();
		
		Map<String,Object> robot = newMap();
		robot.put("hardware", hardware);
		robot.put("imageFilters", imageFilters);
		robot.put("controlLoops", controlLoops);
		
		System.out.println(new JSONObject(robot).toJSONString());
	}
	
	public static Map<String,Object> newMap() {
		return new HashMap<String,Object>();
	}
}
