package com.kong.common.handle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/5/13
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChromeKDriver implements IDriver {
    public static final String DRIVER_CHROMEDRIVER_EXE = "driver/chromedriver.exe";
    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    @Override
    public WebDriver setupDriver() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER,
                DRIVER_CHROMEDRIVER_EXE);
        return new ChromeDriver();
    }
}
