package com.kong.common.controller;

import com.kong.common.model.BrowserSettings;
import com.kong.common.model.IBean;
import com.kong.util.*;

import java.util.Properties;

public class SetupProxy {
    public SetupProxy() {
    }// Server settings

    public static IBean getSettings(String setupParamProp) {
        Properties properties = PropUtils.getProperties(setupParamProp);
        String browser = properties.getProperty("browser");
        String server = properties.getProperty("server");
        String port = properties.getProperty("port");
        String url = properties.getProperty("url");

        if (null == server)
            server = ContextConstant.SERVER_IP;
        if (!port.matches("\\d+"))
            port = String.valueOf(ContextConstant.SERVER_PORT);

        IBean browserSettings = new BrowserSettings(browser, server, Integer.valueOf(port), url);

        Suite.logger.debug(browserSettings.toString());
        return browserSettings;
    }
}