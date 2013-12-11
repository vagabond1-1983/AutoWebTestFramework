package com.kong.common.model;

import com.kong.util.log.LogUtil;
import com.kong.util.xmlDataParsing.MethodAutoMatches;
import org.apache.logging.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/9/13
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class StepInfo {
    public static Logger logger = LogUtil.getLogger(StepInfo.class);

    private String name;
    private String refInstance;
    private String method;
    private String params;

    private final String regex = "\\|\\|";

    public void setScenario(CaseScenario scenario) {
        this.scenario = scenario;
    }

    private CaseScenario scenario;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefInstance() {
        return refInstance;
    }

    public void setRefInstance(String refInstance) {
        this.refInstance = refInstance;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    /**
     * Dec 10 2013
     * It will detect all parameters and then combined class, method, params together to run. It already has scenario object.
     * @return
     */
    public Object stepExecute() {
        // log start to execute which step name
        logger.debug("Start execute step: " + this.name);
        Object result = null;
        // refInstance exist or not. If has, look up instance in current scenario
        if(null != this.refInstance) {
            if(null != this.scenario) {
                if(null != this.refInstance) {
                    Class<?> targetClass = this.scenario.findPageObjectClass(this.refInstance);
                    if(null != this.method) {
                        //      separate params from string
                        Object[] paramsArray = handleParamsIntoObjectArray();
                        //          push params to methods
                        logger.debug("Try to execute method: " + this.method + " under instance: " + this.refInstance);
                        result = MethodAutoMatches.methodExec(targetClass, this.method, paramsArray);
                        if(null != result) {
                            logger.debug("After method: " + this.method + " executed, get the result: " + result.toString());
                        }
                    }
                }
            }   else {
                // scenario is null.
                throw new RuntimeException("Scenario is wrong to find");
            }
        }
        logger.debug("End execute step: " + this.name);
        /*else {

        // If not, include default handler
        }*/
        return result;
    }

    private Object[] handleParamsIntoObjectArray() {
        Object[] ps = null;
        if(null != params) {
            String[] strParams = this.params.split(regex);
            int count = strParams.length;
            ps = new Object[count];
            for (int i = 0; i < count; i++) {
                if(strParams[i].contains("=")) {
                    ps[i] = strParams[i].split("=")[1];
                } else {
                    ps[i] = strParams[i];
                }
            }
        }
        return ps;
    }

    @Override
    public String toString() {
        return "StepInfo{" +
                "name='" + name + '\'' +
                ", refInstance='" + refInstance + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
