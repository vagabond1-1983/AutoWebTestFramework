package com.kong.baidu.tasks;

import com.kong.util.PropUtils;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/11/13
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class commonTask {
    protected Properties elemMap;
    private final static String PROP_NAME = "baiduPages.properties";
    private commonTask ct = null;

    private commonTask() {
    }

    public commonTask getInstance() {
        if (ct != null)
            return ct;
        ct = new commonTask();
        return ct;
    }

    public Properties getPageElements() {
        elemMap = PropUtils.getProperties(PROP_NAME);
        return elemMap;
    }


}