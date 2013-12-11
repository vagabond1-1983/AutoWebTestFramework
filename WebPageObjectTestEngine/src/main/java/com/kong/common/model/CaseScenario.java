package com.kong.common.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/9/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class CaseScenario implements IBean {
    private List<PageInfo> PageInfos = new LinkedList<PageInfo>();
    private List<StepInfo> StepInfos = new LinkedList<StepInfo>();

    public List<PageInfo> getPageInfos() {
        return PageInfos;
    }

    public void setPageInfos(List<PageInfo> pageInfos) {
        this.PageInfos = pageInfos;
    }

    public List<StepInfo> getStepInfos() {
        return StepInfos;
    }

    public void setStepInfos(List<StepInfo> stepInfos) {
        this.StepInfos = stepInfos;
    }

    public void addPage(PageInfo page) {
        if (null != PageInfos) {
            PageInfos.add(page);
        }
    }

    public void addStep(StepInfo step) {
        if (null != StepInfos) {
            StepInfos.add(step);
            step.setScenario(this);
        }
    }

    public Class<?> findPageObjectClass(String name) {
        for(PageInfo p : PageInfos) {
            if(name.contains(p.getName())) {
                return p.getPageObjectClass();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CaseScenario{" +
                "PageInfos=" + PageInfos +
                ", StepInfos=" + StepInfos +
                '}';
    }
}
