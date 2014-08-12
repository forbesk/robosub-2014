package org.auvua.agent.tasks.special;

import org.auvua.agent.Task;
import org.auvua.model.Model;

public class ResetHeading extends Task {

	@Override
	public TaskState runTaskBody() {
		Model.getInstance().getGyroIntegrator().resetHeading();
		return TaskState.SUCCEEDED;
	}
	
}
