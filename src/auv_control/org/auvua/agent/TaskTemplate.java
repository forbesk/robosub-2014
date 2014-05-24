package org.auvua.agent;

public class TaskTemplate extends Task {

	@Override
	public void run() {
		while(true) {
			//do something here
		}
	}

	@Override
	public void cleanup() {
		// interrupt any spawned threads, etc
	}

}
