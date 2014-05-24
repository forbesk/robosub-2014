package org.auvua.agent;

import org.auvua.model.Model;

public abstract class Task implements Runnable {

	public Model model = null;
	public Task successTask = null;
	public Task failureTask = null;
	public Task timeoutTask = null;
	
	@Override
	abstract public void run();
	
	abstract public void cleanup();
	
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
	
}
