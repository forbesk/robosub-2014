package org.auvua.agent;

public abstract class Task implements Runnable {

	public Task successTask = null;
	public Task failureTask = null;
	public Task timeoutTask = null;
	public TaskState state = TaskState.NOTSTARTED;
	public boolean completed = false;
	
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
		switch(state) {
			case SUCCEEDED: return successTask;
			case FAILED:    return failureTask;
			case TIMEDOUT:  return timeoutTask;
			default:        return null;
		}
	}
	
	public boolean isComplete() {
		return completed;
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
