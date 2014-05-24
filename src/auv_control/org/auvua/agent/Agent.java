package org.auvua.agent;

import org.auvua.model.Model;

public class Agent implements Runnable {
	
	public Model model;
	public Task currentTask;
	
	public Agent( Model model, Task task ) {
		this.model = model;
		this.currentTask = task;
	}
	
	public void run() {

		while(currentTask != null) {

			currentTask.setModel(model);

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
