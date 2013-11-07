package com.kong.common.controller;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Properties;
import java.util.Map;

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

    public HashMap<String, Object> getContext() {
        return context;
    }

    public void putContext(String key, Object value) {
        this.context.put(key, value);
    }

    public WebDriver getCurrentDriver() {
        return (WebDriver) context.get(ContextConstant.DRIVER_CONTEXT);
    }

    public Properties getPagesMap() {
        return (Properties) context.get(ContextConstant.PAGES_MAP_CONTEXT);
    }

    public HashMap<String, Object> getParamMap() {
        if (context.get(ContextConstant.PARAM_MAP_CONTEXT) != null) {
            return (HashMap<String, Object>) context.get(ContextConstant.PARAM_MAP_CONTEXT);
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

    private Object getValue(Map map, String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }
}