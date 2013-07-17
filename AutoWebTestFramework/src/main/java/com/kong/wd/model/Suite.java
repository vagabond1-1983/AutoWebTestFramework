package com.kong.wd.model;

import java.util.ArrayList;
import java.util.List;

public class Suite implements IBean{
	private String name;
	private Settings settings;
	private List<Case> cases = new ArrayList<Case>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSettings(Settings settings) {
		this.settings = settings;
	}

	public void addCase(Case cs) {
		cases.add(cs);
	}

	public Settings getSettings() {
		return settings;
	}

	public List<Case> getCases() {
		return cases;
	}

	public String toString() {
		String newline = System.getProperty("line.separator");
		StringBuffer buf = new StringBuffer();

		buf.append("--- Settings ---").append(newline);
		if (settings != null) {
			buf.append(settings).append(newline);
		}

		if (cases != null) {
			buf.append("--- Cases ---").append(newline);
			for (int i = 0; i < cases.size(); i++) {
				buf.append(cases.get(i)).append(newline);
			}
		}

		return buf.toString();
	}
}
