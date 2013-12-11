package com.kong.common.controller;

import com.kong.common.model.BrowserSettings;
import com.kong.common.model.IBean;
import com.kong.common.model.JQueryEntity;
import com.kong.util.propertyParsing.PropUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SetupProxy {
    public SetupProxy() {
    }// Server settings

    public static List<IBean> getSettings(String setupParamProp) {
        List<IBean> list = null;

        Properties properties = PropUtils.getProperties(setupParamProp);
        String browser = properties.getProperty("browser");
        String server = properties.getProperty("server");
        String port = properties.getProperty("port");
        String url = properties.getProperty("url");
        String jquery = properties.getProperty("jquery");

        if (null == server)
            server = ContextConstant.SERVER_IP;
        if (!port.matches("\\d+"))
            port = String.valueOf(ContextConstant.SERVER_PORT);

        IBean browserSettings = new BrowserSettings(browser, server, Integer.valueOf(port), url);
        IBean jqueryEntity = new JQueryEntity(jquery);

        list = new ArrayList<IBean>();
        list.add(browserSettings);
        list.add(jqueryEntity);

        Suite.logger.debug(browserSettings.toString());
        return list;
    }
}