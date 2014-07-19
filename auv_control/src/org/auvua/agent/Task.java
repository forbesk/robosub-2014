package org.auvua.agent;

import org.auvua.model.Model;

public abstract class Task implements Runnable {

	public Model model = null;
	public Task successTask = null;
	public Task failureTask = null;
	public Task timeoutTask = null;
	public TaskState state = TaskState.NOTSTARTED;
	public boolean completed = false;
	
	@Override
	abstract public void run();
	
	protected void cleanup() {
		synchronized(this) {
			completed = true;
			this.notifyAll();
		}
	}
	
	public void setModel(Model m) {
		model = m;
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
			default:        return this;
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
