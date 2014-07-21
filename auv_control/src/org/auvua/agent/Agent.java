package org.auvua.agent;

import org.auvua.model.Model;

public class Agent implements Runnable {
	
	public Task currentTask;
	
	public Agent( Task task ) {
		this.currentTask = task;
	}
	
	public void run() {

		while((long)Model.getInstance().getComponentValue("hardware.missionSwitch.on") == 0L) {
			if(System.currentTimeMillis() % 1000 > 500) {
				Model.getInstance().setComponentValue("hardware.indicatorLights.white", 1);
			} else {
				Model.getInstance().setComponentValue("hardware.indicatorLights.white", 0);
			}
			
			try { Thread.sleep(50); } catch (InterruptedException e) {	}
		}
		
		Model.getInstance().setComponentValue("hardware.indicatorLights.white", 0);
		
		while(currentTask != null) {

			Thread t = new Thread(currentTask);
			t.start();

			synchronized(currentTask) {
				if(!currentTask.isComplete()) {
					try {
						currentTask.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			currentTask = currentTask.getNextTask();
		}

	}
	
}
