package org.auvua.agent;

import java.util.LinkedList;
import java.util.List;

import org.auvua.agent.Task.TaskState;
import org.auvua.model.Model;

public class Agent implements Runnable {
	
	private Task startTask;
	private Task currentTask;
	private boolean waitForMissionSwitch = true;
	private List<Task> cleanedTasks = new LinkedList<Task>();

	public Agent( Task startTask ) {
		this.startTask = startTask;
	}

	public void run() {
		while(true) {
			currentTask = startTask;
			System.out.println("Waiting for mission...");
			if(waitForMissionSwitch) waitForMissionSwitch();
			Model.getInstance().getGyroIntegrator().resetHeading();
			
			System.out.println("Mission started!");
			
			startMissionSwitchListener();

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
				
				new Thread() {
					public void run() {
						Model.getInstance().setComponentValue("hardware.indicatorLights.white", 1.0);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Model.getInstance().setComponentValue("hardware.indicatorLights.white", 0.0);
					}
				}.start();
				
				System.out.println(">>>>" + currentTask.getClass().getName() + ": " + currentTask.state.name());

				if(currentTask.interrupted) break;
				
				Task lastTask = currentTask;
				currentTask = currentTask.getNextTask();
				
				// Clean the task that was just run, in case it will be re-run
				lastTask.completed = false;
				lastTask.interrupted = false;
				lastTask.state = TaskState.NOTSTARTED;
			}
			
			if(currentTask != null && currentTask.interrupted) {
				System.out.println("Mission Interrupted!");
			} else {
				System.out.println("Mission Completed!");
				Model.getInstance().setComponentValue("hardware.missionComplete", 1L);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			cleanTaskTree(startTask);
			cleanedTasks.clear();
		}
		
		// Model.getInstance().finish();
	}

	private void cleanTaskTree(Task task) {
		if(task == null || cleanedTasks.contains(task)) return;
		cleanedTasks.add(task);
		task.completed = false;
		task.interrupted = false;
		task.state = TaskState.NOTSTARTED;
		cleanTaskTree(task.successTask);
		cleanTaskTree(task.failureTask);
		cleanTaskTree(task.timeoutTask);
	}

	private void startMissionSwitchListener() {
		new Thread() {
			public void run() {
				while( (long) Model.getInstance().getComponentValue("hardware.missionSwitch.on") == 1L) {
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
				}
				if(currentTask != null) currentTask.stop();
			}
		}.start();
	}

	public void waitForMissionSwitch() {
		while( (long) Model.getInstance().getComponentValue("hardware.missionSwitch.on") == 0L) {
			if(System.currentTimeMillis() % 1000 > 500) {
				Model.getInstance().setComponentValue("hardware.indicatorLights.white", 1);
			} else {
				Model.getInstance().setComponentValue("hardware.indicatorLights.white", 0);
			}

			try { Thread.sleep(50); } catch (InterruptedException e) {	}
		}
		Model.getInstance().setComponentValue("hardware.indicatorLights.white", 0);
	}
	
	
}
