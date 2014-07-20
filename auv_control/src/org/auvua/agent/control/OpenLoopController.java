package org.auvua.agent.control;

import org.auvua.model.Model;

public class OpenLoopController implements Controller {
	
	private String component;
	private Function function;
	
	public OpenLoopController(String component, Function function ) {
		this.component = component;
		this.function = function;
	}

	@Override
	public void advanceTimestep(long timeStep) {
		Model.getInstance().setComponentValue(component, function.getValue(timeStep));
	}
	
}
