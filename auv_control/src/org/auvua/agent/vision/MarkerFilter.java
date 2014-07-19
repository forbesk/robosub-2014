package org.auvua.agent.vision;

import java.util.HashMap;
import java.util.Map;

import org.auvua.model.sensors.Camera;

public class MarkerFilter implements ImageFilter {
	
	Camera camera;
	Map<String,Object> values = new HashMap<String,Object>();
	
	public MarkerFilter(Camera camera) {
		this.camera = camera;
		values.put("markerVisible", false);
		values.put("markerX", 0);
		values.put("markerY", 0);
		values.put("markerAngle", 0.0);
		values.put("markerSize", 1.0);
	}

	@Override
	public Map<String, Object> captureValues() {
		return values;
	}

}
