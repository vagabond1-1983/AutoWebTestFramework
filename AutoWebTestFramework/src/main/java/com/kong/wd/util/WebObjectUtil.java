package com.kong.wd.util;

import org.openqa.selenium.WebDriver;

public class WebObjectUtil {
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
