package org.auvua;

import org.auvua.agent.*;
import org.auvua.agent.tasks.DriveTask;
import org.auvua.model.*;

public class Server {
		
	public static void main(String args[]) {
		Model model = new Model();
		Agent agent = new Agent( model, new DriveTask(60.0, 5000));
		
		new Thread(agent).start();
	}
	
}