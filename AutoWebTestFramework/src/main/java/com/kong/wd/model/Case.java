package com.kong.wd.model;

public class Case implements IBean {
	private Steps steps;
	private String name;
    private Integer timeout;

	public Case() {
		super();
	}

	public Steps getSteps() {
		return steps;
	}

	public void setSteps(Steps steps) {
		this.steps = steps;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

	@Override
	public String toString() {
		return "Case[ name="+name+", timeout="+timeout+", [steps=" + steps + "]";
	}
    
    
}
