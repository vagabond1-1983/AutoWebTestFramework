package com.kong.wd.model;

import java.util.ArrayList;
import java.util.List;

public class Steps {
	private List<Step> steps;

	public Steps() {
		steps = new ArrayList<Step>();
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	public void addStep(Step step) {
		steps.add(step);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(10);
		for(Step step : steps) {
			sb.append(step.toString());
		}
		return sb.append("steps: ").toString();
	}
	
}
