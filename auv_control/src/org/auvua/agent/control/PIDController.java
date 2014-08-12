package org.auvua.agent.control;

import org.auvua.model.Model;

public class PIDController implements Controller {
	
	private String output;
	private boolean stop = false;
	
	private PIDLoop loop;
	
	public PIDController( String output, PIDLoop loop ) {
		this.loop = loop;
		this.output = output;
	}

	@Override
	public void start() {
		loop.start();
		new Thread() {
			public void run() {
				while(!stop) {
					Model.getInstance().setComponentValue(output, loop.getOutputValue());
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
				}
				Model.getInstance().setComponentValue(output, 0.0);
			}
		}.start();
	}

	@Override
	public void stop() {
		loop.stop();
		stop = true;
	}
	
}
