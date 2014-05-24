package org.auvua.agent;

import org.auvua.model.Model;

public abstract class Task implements Runnable {

	public Model model = null;
	public Task successTask = null;
	public Task failureTask = null;
	public Task timeoutTask = null;
	public boolean completed = false;
	public boolean succeeded = false;
	public boolean failed = false;
	public boolean timedOut = false;
	
	@Override
	abstract public void run();
	
	protected void cleanup() {
		synchronized(this) {
			completed = true;
			notifyAll();
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
		if(succeeded) return successTask;
		if(failed) return failureTask;
		if(timedOut) return timeoutTask;
		return null;
	}
	
	public boolean isComplete() {
		return completed;
	}
	
}
