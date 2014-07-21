package org.auvua.model;

import java.util.HashMap;
import java.util.Map;

import org.auvua.model.actuators.Motor;
import org.auvua.model.sensors.Compass;
import org.auvua.model.sensors.DepthGauge;
import org.auvua.model.sensors.Switch;
import org.auvua.sim.MockHardware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.zeromq.*;
import org.zeromq.ZMQ.*;

public class Model {
	
	private Map<String,Object> robot = new HashMap<String,Object>();
	
	private static final Model instance = new Model();
	private final long refreshPeriod = 20;	//req/rep update period in ms
	private final String SOCKET_ADDRESS = "tcp://127.0.0.1:5560";
	
	public Model() {
		Map<String,Object> hardware = newMap();
		hardware.put("surgeLeft",      ComponentFactory.create(Component.MOTOR));
		hardware.put("surgeRight",     ComponentFactory.create(Component.MOTOR));
		hardware.put("heaveLeft",      ComponentFactory.create(Component.MOTOR));
		hardware.put("heaveRight",     ComponentFactory.create(Component.MOTOR));
		hardware.put("sway",           ComponentFactory.create(Component.MOTOR));
		hardware.put("depthGauge",     ComponentFactory.create(Component.DEPTHGAUGE));
		hardware.put("compass",        ComponentFactory.create(Component.COMPASS));
		hardware.put("humiditySensor", ComponentFactory.create(Component.HUMIDITY_SENSOR));
		hardware.put("missionSwitch",  ComponentFactory.create(Component.SWITCH));
		hardware.put("killSwitch",     ComponentFactory.create(Component.SWITCH));
		hardware.put("indicatorLights",ComponentFactory.create(Component.INDICATOR_LIGHTS));
		
		Map<String,Object> imageFilters = newMap();
		
		Map<String,Object> controlLoops = newMap();
		
		robot = newMap();
		robot.put("hardware", hardware);
		robot.put("imageFilters", imageFilters);
		robot.put("controlLoops", controlLoops);
		
		new Thread() {
			public void run() {
				// Communication with hardware here		
				Context ctx = ZMQ.context(1);
				
				Socket req = ctx.socket(ZMQ.REQ);
				req.connect(SOCKET_ADDRESS);
				
				while(true) {
					req.send(JSONValue.toJSONString(getComponentValue("hardware")));
					JSONObject data = (JSONObject)JSONValue.parse(req.recvStr());
					setComponentValue("hardware.depthGauge", data.get("depthGauge"));
					setComponentValue("hardware.compass", data.get("compass"));
					setComponentValue("hardware.humiditySensor", data.get("humiditySensor"));
					setComponentValue("hardware.missionSwitch", data.get("missionSwitch"));
					
					try {
						Thread.sleep(refreshPeriod);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public static Model getInstance() {
		return instance;
	}
	
	public void setRobot(Map<String,Object> robot) {
		this.robot = robot;
	}
	
	public Map<String,Object> getRobot() {
		return robot;
	}
	
	public static Map<String,Object> newMap() {
		return new HashMap<String,Object>();
	}
	
	public void getState() {
		// Receive hardware update via ZMQ
		MockHardware.getInstance().getState();
	}

	public void setState() {
		// Send hardware update via ZMQ
		MockHardware.getInstance().setState();
	}
	
	@SuppressWarnings("unchecked")
	public void setComponentValue(String component, Object value) {
		String[] strings = component.split("\\.");
		Map<String, Object> comp = robot;
		for(int i = 0; i < strings.length - 1; i++) {
			comp = (Map<String, Object>) comp.get(strings[i]);
		}
		comp.put(strings[strings.length-1], value);
	}
	
	@SuppressWarnings("unchecked")
	public Object getComponentValue(String component) {
		String[] strings = component.split("\\.");
		Map<String, Object> comp = robot;
		for(int i = 0; i < strings.length - 1; i++)
			comp = (Map<String, Object>) comp.get(strings[i]);
		return comp.get(strings[strings.length-1]);
	}
}
