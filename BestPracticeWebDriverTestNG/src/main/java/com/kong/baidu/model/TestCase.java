package com.kong.baidu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/12/13
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestCase implements IBean {
    private HashMap<String, Object> paramMap;
    private List<Step> steps;

    public TestCase() {
        paramMap = new HashMap<String, Object>();
        steps = new ArrayList<Step>();
    }

    private void setParamMap(Step step) {
        paramMap.put(step.getId(), step.getValue());
    }

    public HashMap<String, Object> getParamMap() {
        return paramMap;
    }

    public void addStep(Step step) {
        steps.add(step);
        setParamMap(step);
    }


}
