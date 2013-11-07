package com.kong.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/24/13
 * Time: 9:11 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class LogUtil {
    public static Logger getLogger(Class clazz) {
        return LogManager.getLogger(clazz);
    }
}
