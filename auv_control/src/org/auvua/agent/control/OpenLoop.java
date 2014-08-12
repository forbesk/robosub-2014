package org.auvua.agent.control;

public class OpenLoop implements Loop {
	
	Function function;
	double value = 0.0;
	boolean stop = false;
	
	public OpenLoop( Function function ) {
		this.function = function;
	}

	@Override
	public double getOutputValue() {
		return value;
	}

	@Override
	public void start() {
		new Thread() {
			public void run() {
				long startTime = System.currentTimeMillis();
				while(!stop) {
					long time = System.currentTimeMillis() - startTime;
					value = function.getValue(time);
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
				}
			}
		}.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		stop = true;
	}
}
