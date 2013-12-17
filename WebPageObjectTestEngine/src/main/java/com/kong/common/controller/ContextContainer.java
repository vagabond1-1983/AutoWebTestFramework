package com.kong.common.controller;

import com.kong.common.model.IBean;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/22/13
 * Time: 9:54 PM
 * Context container including driver, pagasMap and paramsMap.
 * pagesMap is from pages properties.
 * paramsMap is from case xml. Instance of HashMap.
 */
public class ContextContainer {
    private HashMap<String, Object> context = new HashMap<String, Object>();

    public static final String DRIVER = "driver";
    public static final String JQUERY = "jQuery";
    public static final String PAGES_MAP = "pagesMap";
    public static final String PARAM_MAP = "paramsMap";

    public static final String ACTION_HELPER = "actionHelper";

    public static final String SCENARIO_OBJECT = "scenarioObject";

    public static final String SERVER_IP = "127.0.0.1";
    public static final Integer SERVER_PORT = 4444;

    public HashMap<String, Object> getContext() {
        return context;
    }

    public void putContext(String key, Object value) {
        this.context.put(key, value);
    }

    public WebDriver getCurrentDriver() {
        return (WebDriver) context.get(DRIVER);
    }

    public Properties getPagesMap() {
        return (Properties) context.get(PAGES_MAP);
    }

    public Map<String, Object> getParamMap() {
        if (context.get(PARAM_MAP) != null) {
            return (Map<String, Object>) context.get(PARAM_MAP);
        }
        return null;
    }

    public Object getPageValue(String key) {
        if (null == getValue(getPagesMap(), key)) {
            throw new NullPointerException("The key: " + key + " did not find in Pages Map");
        }
        return getValue(getPagesMap(), key);
    }

    public Object getParamValue(String key) {
        if (null == getValue(getParamMap(), key)) {
            throw new NullPointerException("The key: " + key + " did not find in Params Map");
        }
        return getValue(getParamMap(), key);
    }

    public IBean getScenarioBean() {
        if(context.containsKey(SCENARIO_OBJECT)) {
            return (IBean)context.get(SCENARIO_OBJECT);
        }
        return null;
    }

    private Object getValue(Map map, String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }
}