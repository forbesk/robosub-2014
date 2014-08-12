package org.auvua.agent;

public abstract class Task implements Runnable {

	public Task successTask = null;
	public Task failureTask = null;
	public Task timeoutTask = null;
	public TaskState state = TaskState.NOTSTARTED;
	public boolean completed = false;
	public boolean interrupted = false;
	
	public abstract TaskState runTaskBody();
	
	public void run() {
		state = TaskState.RUNNING;
		
		state = runTaskBody();
		
		synchronized(this) {
			completed = true;
			this.notifyAll();
		}
	}
	
	public void setSuccessTask(Task t) {
		successTask = t;
	}
	
	public void setFailureTask(Task t) {
		failureTask = t;
	}
	
	public void setTimeoutTask(Task t) {
		timeoutTask = t;
	}
	
	public Task getNextTask() {
		if(interrupted) return null;
		
		switch(state) {
			case SUCCEEDED:   return successTask;
			case FAILED:      return failureTask;
			case TIMEDOUT:    return timeoutTask;
			case INTERRUPTED: return null;
			default:          return null;
		}
	}
	
	public boolean isComplete() {
		return completed;
	}
	
	public void stop() {
		interrupted = true;
	}
	
	public enum TaskState {
		SUCCEEDED,
		FAILED,
		TIMEDOUT,
		INTERRUPTED,
		RUNNING,
		NOTSTARTED
	}
	
}
