package org.auvua.agent.tasks.drive;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.auvua.agent.Task;
import org.auvua.model.Model;

public class RemoteControlTask extends Task {
	
	Map<Character,Double> keyStates = new HashMap<Character,Double>();
	int duration;
	
	public RemoteControlTask(int duration) {
		this.duration = duration;
	}

	@Override
	public TaskState runTaskBody() {
		keyStates.put('w', 0.0);
		keyStates.put('a', 0.0);
		keyStates.put('s', 0.0);
		keyStates.put('d', 0.0);
		keyStates.put('q', 0.0);
		keyStates.put('e', 0.0);
		keyStates.put('r', 0.0);
		keyStates.put('f', 0.0);
		
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				keyStates.put(arg0.getKeyChar(), 1.0);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				keyStates.put(arg0.getKeyChar(), 0.0);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.setSize(200,200);
		frame.setVisible(true);
		
		
		
		long startTime = System.currentTimeMillis();
		long currTime = startTime;

		Model.getInstance().setComponentValue("hardware.indicatorLights.green", 1.0);
		
		do {
			System.out.println(Model.getInstance().getComponentValue("hardware.depthGauge.depth"));
			
			double w = keyStates.get('w');
			double a = keyStates.get('a');
			double s = keyStates.get('s');
			double d = keyStates.get('d');
			double q = keyStates.get('q');
			double e = keyStates.get('e');
			double r = keyStates.get('r');
			double f = keyStates.get('f');
			
			Model.getInstance().setComponentValue("hardware.surgeLeft.speed", 0.5 * (w - s - a + d));
			Model.getInstance().setComponentValue("hardware.surgeRight.speed", 0.5 * (w - s + a - d));
			Model.getInstance().setComponentValue("hardware.heaveLeft.speed", 0.5 * (r - f));
			Model.getInstance().setComponentValue("hardware.heaveRight.speed", 0.5 * (r - f));
			Model.getInstance().setComponentValue("hardware.sway.speed", 0.75 * (e - q));
			
			try { Thread.sleep(50); } catch (InterruptedException exception) {	}
			
			currTime = System.currentTimeMillis();

		} while(currTime < startTime + duration && !interrupted);
		
		Model.getInstance().setComponentValue("hardware.surgeLeft.speed", 0.0);
		Model.getInstance().setComponentValue("hardware.surgeRight.speed", 0.0);
		Model.getInstance().setComponentValue("hardware.heaveLeft.speed", 0.0);
		Model.getInstance().setComponentValue("hardware.heaveRight.speed", 0.0);
		Model.getInstance().setComponentValue("hardware.sway.speed", 0.0);
		
		Model.getInstance().setComponentValue("hardware.indicatorLights.green", 0.0);
		
		frame.dispose();
		
		return interrupted ? TaskState.INTERRUPTED : TaskState.SUCCEEDED;
	}
	
}
