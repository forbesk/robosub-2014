package org.auvua.model;

public class GyroIntegrator {

	public double heading;
	public boolean calibrating = true;

	public GyroIntegrator() {
		heading = 0;
	}

	public void start() {
		new Thread() {
			public void run() {
				long startTime = System.currentTimeMillis();
				long lastTime = startTime;
				long currTime = startTime;
				
				double drift = 0;

				while(true) {
					if(calibrating) {
						System.out.println("Calibrating gyro...");
						double driftSum = 0;
						int count = 0;
						
						startTime = System.currentTimeMillis();
						
						while(System.currentTimeMillis() < startTime + 2000) {
							driftSum += (double) Model.getInstance().getComponentValue("hardware.compass.gyroZ");
							count++;
							try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
						}
						drift = driftSum / count;
						
						System.out.println("Drift: " + drift + " rad/sec");
						System.out.println("Calibration complete!");
						calibrating = false;
						startTime = System.currentTimeMillis();
						
						lastTime = startTime;
						currTime = startTime;
					}

					long timeStep = currTime - lastTime;

					double gyroZ = (double) Model.getInstance().getComponentValue("hardware.compass.gyroZ");// - drift;

					heading += gyroZ * timeStep / 1000 * 180 / Math.PI;

					try { Thread.sleep(50); } catch (InterruptedException e) {	}

					lastTime = currTime;
					currTime = System.currentTimeMillis();
					
					Model.getInstance().setComponentValue("hardware.compass.gyroHeading", heading);
				}

			}
		}.start();
	}

	public void calibrate() {
		calibrating = true;
	}
	
	public void resetHeading() {
		heading = 0;
	}
}
