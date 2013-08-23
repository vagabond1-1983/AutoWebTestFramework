package com.kong.baidu.controller;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/22/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
    public HashMap<String, Object> getContext() {
        return context;
    }

    public void putContext(String key, Object value) {
        this.context.put(key, value);
    }

    private HashMap<String, Object> context = new HashMap<String, Object>();

}
