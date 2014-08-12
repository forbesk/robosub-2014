package org.auvua.agent.control;

import org.auvua.model.Model;

public class OpenLoopController implements Controller {
	
	private String component;
	private Function function;
	private boolean stop = false;
	
	public OpenLoopController(String component, Function function ) {
		this.component = component;
		this.function = function;
	}

	@Override
	public void start() {
		new Thread() {
			public void run() {
				long startTime = System.currentTimeMillis();
				while(!stop) {
					long currTime = System.currentTimeMillis() - startTime;
					Model.getInstance().setComponentValue(component, function.getValue(currTime));
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
				}
				Model.getInstance().setComponentValue(component, 0.0);
			}
		}.start();
	}

	@Override
	public void stop() {
		stop = true;
	}
	
}
