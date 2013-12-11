package com.kong.util.webpage.Delay;

import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/7/13
 * Time: 9:23 PM
 * Window(s) handle like close all windows, close others windows except current.
 */
public class WindowHandle {
    public static boolean closeAllWindows(WebDriver driver) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            driver.close();
        }
        return true;
    }

    public static WebDriver getCurrentWindowCloseOthers(WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (!driver.getCurrentUrl().equals(currentUrl)) {
                driver.close();
            }
        }
        return driver;
    }
}
