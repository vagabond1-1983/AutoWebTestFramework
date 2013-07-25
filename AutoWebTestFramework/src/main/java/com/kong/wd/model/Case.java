package com.kong.wd.model;

import java.util.ArrayList;
import java.util.List;

public class Case implements IBean {
    private List<Step> steps;

    private String name;
    private Integer timeout;

    public Case() {
        steps = new ArrayList<Step>();
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
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

    public void addStep(Step step) {
        if (steps == null) {
            steps = new ArrayList<Step>();
        }
        steps.add(step);
    }

    @Override
    public String toString() {
        return "Case[ name=" + name + ", timeout=" + timeout + ", [steps=" + steps.toArray().toString() + "]";
    }


}
