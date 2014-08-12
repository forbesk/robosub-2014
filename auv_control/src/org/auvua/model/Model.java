package org.auvua.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.auvua.vision.Camera;
import org.auvua.vision.CameraViewer;
import org.auvua.vision.ImageFilter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.zeromq.*;
import org.zeromq.ZMQ.*;

public class Model {
	
	private Map<String,Object> robot = new HashMap<String,Object>();
	private Map<String,Object> cameras = new HashMap<String,Object>();
	
	private static final Model instance = new Model();
	private final long refreshPeriod = 20;	//req/rep update period in ms
	private final String SOCKET_ADDRESS = "tcp://127.0.0.1:5560";
	
	private GyroIntegrator gyroIntegrator = new GyroIntegrator();
	
	private boolean finished = false;
	
	public Model() {
		
		Map<String,Object> hardware = newMap();
		hardware.put("surgeLeft",       ComponentFactory.create(Component.MOTOR));
		hardware.put("surgeRight",      ComponentFactory.create(Component.MOTOR));
		hardware.put("heaveLeft",       ComponentFactory.create(Component.MOTOR));
		hardware.put("heaveRight",      ComponentFactory.create(Component.MOTOR));
		hardware.put("sway",            ComponentFactory.create(Component.MOTOR));
		hardware.put("depthGauge",      ComponentFactory.create(Component.DEPTHGAUGE));
		hardware.put("compass",         ComponentFactory.create(Component.COMPASS));
		hardware.put("humiditySensor",  ComponentFactory.create(Component.HUMIDITY_SENSOR));
		hardware.put("missionSwitch",   ComponentFactory.create(Component.SWITCH));
		hardware.put("killSwitch",      ComponentFactory.create(Component.SWITCH));
		hardware.put("indicatorLights", ComponentFactory.create(Component.INDICATOR_LIGHTS));
		hardware.put("missionComplete", 0L);
		
		cameras.put("frontCamera", new Camera(0));
		cameras.put("bottomCamera", new Camera(1));
		
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
				
				CameraViewer frontViewer = new CameraViewer();
				CameraViewer bottomViewer = new CameraViewer();
				
				gyroIntegrator.start();
				
				while(!finished) {
					
					req.send(JSONValue.toJSONString(getComponentValue("hardware")));
					
					JSONObject data = (JSONObject)JSONValue.parse(req.recvStr());
					setComponentValue("hardware.depthGauge", data.get("depthGauge"));
					setComponentValue("hardware.compass", data.get("compass"));
					setComponentValue("hardware.humiditySensor", data.get("humiditySensor"));
					setComponentValue("hardware.missionSwitch", data.get("missionSwitch"));
					
					if((long) data.get("missionComplete") == 1L) {
						setComponentValue("hardware.missionComplete", 0L);
					}
					
//					System.out.print(Model.getInstance().getComponentValue("hardware.compass.gyroHeading"));
//					System.out.println("\t" + Model.getInstance().getComponentValue("hardware.compass.heading"));
					
					
					Camera frontCam = (Camera) cameras.get("frontCamera");
					frontCam.capture();
					frontCam.applyFilters();
					frontViewer.setImage(frontCam.getImage());
					for (Entry<String,Object> e : frontCam.getFilterOutputs().entrySet()) {
						setComponentValue("imageFilters." + e.getKey(), e.getValue());
					}
					
					
					Camera bottomCam = (Camera) cameras.get("bottomCamera");
					bottomCam.capture();
					bottomCam.applyFilters();
					bottomViewer.setImage(bottomCam.getImage());
					for (Entry<String,Object> e : bottomCam.getFilterOutputs().entrySet()) {
						setComponentValue("imageFilters." + e.getKey(), e.getValue());
					}
					
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

	public void addImageFilter(String cameraName, String filterName, ImageFilter filter) {
		if(getComponentValue("imageFilters." + filterName) != null) return;
		Camera cam = (Camera) cameras.get(cameraName);
		cam.addFilter(filterName, filter);
		cam.capture();
		cam.applyFilters();
		setComponentValue("imageFilters." + filterName, cam.getFilterOutputs().get(filterName));
	}
	
	public void removeImageFilter(String cameraName, String filterName) {
		Camera cam = (Camera) cameras.get(cameraName);
		cam.removeFilter(filterName);
		setComponentValue("imageFilters." + filterName, null);
	}

	public void finish() {
		finished = true;
	}
	
	public GyroIntegrator getGyroIntegrator() {
		return gyroIntegrator;
	}
}
