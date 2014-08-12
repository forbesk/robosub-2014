package org.auvua.agent.control;

import java.util.LinkedList;
import java.util.List;

import org.auvua.model.Model;

public class AggregateController implements Controller {
	
	private String output;
	private List<Loop> loops = new LinkedList<Loop>();
	private boolean stop = false;
	
	public AggregateController( String output ) {
		this.output = output;
	}
	
	public void addLoop(Loop loop) {
		loops.add(loop);
	}

	@Override
	public void start() {
		for (Loop loop : loops) {
			loop.start();
		}
		new Thread() {
			public void run() {
				double outputValue = 0.0;
				while(!stop) {
					outputValue = 0.0;
					for (Loop loop : loops) {
						outputValue += loop.getOutputValue();
					}
					Model.getInstance().setComponentValue(output, outputValue);
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
				}
				Model.getInstance().setComponentValue(output, 0.0);
			}
		}.start();
	}

	@Override
	public void stop() {
		for (Loop loop : loops) {
			loop.stop();
		}
		stop = true;
	}

}
