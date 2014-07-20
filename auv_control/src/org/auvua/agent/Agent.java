package org.auvua.agent;

import org.auvua.model.Model;

public class Agent implements Runnable {
	
	public Task currentTask;
	
	public Agent( Task task ) {
		this.currentTask = task;
	}
	
	public void run() {

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
