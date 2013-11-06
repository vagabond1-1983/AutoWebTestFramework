package com.kong.common.handle;

import com.kong.common.model.IBean;
import com.kong.common.model.BrowserSettings;
import com.kong.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class BrowserStartUpHandler extends Handler {
    WebDriver driver = null;
    private static final Logger logger = LogUtil.getLogger(BrowserStartUpHandler.class);

    /**
     * Startup the browser and url get from BrowserSettings
     *
     * @param settings
     * @return
     */
    @Override
    public WebDriver handle(IBean settings) {
        // Get driver in runtime from browser of BrowserSettings
        driver = DriverHandler.getDriver(((BrowserSettings) settings).getBrowser());

        if (null == driver) {
            throw new NullPointerException();
        }

        logger.debug("Web driver under " + ((BrowserSettings) settings).getBrowser() + " browser up.");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.debug("Selenium server started!");
        driver.get(((BrowserSettings) settings).getUrl());
        return driver;
    }

}
