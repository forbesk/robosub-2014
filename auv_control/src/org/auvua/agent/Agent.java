package org.auvua.agent;

import org.auvua.model.Model;

public class Agent implements Runnable {
	
	private Task currentTask;
	private boolean waitForMissionSwitch = false;
	
	public Agent( Task startTask ) {
		this.currentTask = startTask;
	}

	public void run() {
		if(waitForMissionSwitch) {
			while((long)Model.getInstance().getComponentValue("hardware.missionSwitch.on") == 0L) {
				if(System.currentTimeMillis() % 1000 > 500) {
					Model.getInstance().setComponentValue("hardware.indicatorLights.white", 1);
				} else {
					Model.getInstance().setComponentValue("hardware.indicatorLights.white", 0);
				}

				try { Thread.sleep(50); } catch (InterruptedException e) {	}
			}
			Model.getInstance().setComponentValue("hardware.indicatorLights.white", 0);
		}

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
		
		Model.getInstance().finish();
		
		System.out.println("Mission finished!");
	}
	
}
