package com.kong.common.controller;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/22/13
 * Time: 9:54 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class ContextContainer {
    public HashMap<String, Object> getContext() {
        return context;
    }

    public void putContext(String key, Object value) {
        this.context.put(key, value);
    }

    private HashMap<String, Object> context = new HashMap<String, Object>();

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
}
