package com.kong.common.handle;

import com.kong.common.model.IBean;
import com.kong.common.model.BrowserSettings;
import com.kong.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserStartUpHandler extends Handler {
    WebDriver driver = null;
    private static final Logger logger = LogUtil.getLogger(BrowserStartUpHandler.class);


    @Override
    public WebDriver handle(IBean settings) {
        getDriver(((BrowserSettings) settings).getBrowser());

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

    private void getDriver(String browser) {
        IDriver driverInstance = null;

        if (browser.contains("ie")) {

            // Set webdriver.ie.driver and copy IEDriverServer.exe to any
            // location.
            // Else you will get java.lang.IllegalStateException: The path
            // to the driver executable must be set by the
            // webdriver.ie.driver system property
            driverInstance = new IEDriver();
            // new Integer(seleniumPort).intValue());
        } else if (browser.contains("firefox")) {
                /*System.setProperty("webdriver.firefox.profile",
                        "driver/about config.xul");
				FirefoxProfile profile = new FirefoxProfile();*/
            driverInstance = new FirefoxKDriver();
        } else if (browser.contains("chrome")) {

            driverInstance = new ChromeKDriver();
        }

        driver = driverInstance.setupDriver();
    }
}
