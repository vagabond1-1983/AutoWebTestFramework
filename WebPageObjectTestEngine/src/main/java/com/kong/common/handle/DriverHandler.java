package com.kong.common.handle;

import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/6/13
 * Time: 9:19 PM
 * Get driver in runtime from browser provided
 */
public class DriverHandler {

    private DriverHandler() {
    }

    ;

    public static WebDriver getDriver(String browser) {
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

        if (null != driverInstance)
            return driverInstance.setupDriver();

        return null;

    }
}
