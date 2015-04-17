package org.auvua.vision;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HTTPCameraStream implements Runnable {

	private String device;
	private int port;
	
	public HTTPCameraStream(String device, int port) {
		this.device = device;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			String line;
			String[] cmd = {"mjpg_streamer", "-i", "\"input_uvc.so -d " + device + 
					" -f 25 -r 640x480\"", "-o", "\"output_http.so -p " + port + "\""};
			Process p = Runtime.getRuntime().exec
					("/home/catfish/Desktop/script.sh");
			BufferedReader input =
					new BufferedReader
					(new InputStreamReader(p.getInputStream()));
			System.out.println("Started mjpg_streamer: " + 
					"mjpg_streamer -i \"input_uvc.so -d " + device + 
					" -f 25 -r 640x480\" -o \"output_http.so -p " + port + "\"");
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}

}
