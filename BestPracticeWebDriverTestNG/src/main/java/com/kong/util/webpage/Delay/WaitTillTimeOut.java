package com.kong.util.webpage.Delay;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WaitTillTimeOut {
    public static final int DEFAULT_TIMEOUT_SEC = 10;

    public static void timeoutWait(WebDriver driver) {
        timeoutWait(driver, null, null);
    }

    public static void timeoutWait(WebDriver driver, Integer timeout) {
        timeoutWait(driver, timeout, null);
    }

    public static void timeoutWait(WebDriver driver, Integer timeout, TimeUnit unit) {
        if (timeout == null) {
            timeout = DEFAULT_TIMEOUT_SEC;
        }

        if (unit == null) {
            unit = TimeUnit.SECONDS;
        }
        PageUntilLoaded.logger.debug("Wait for " + timeout + " " + unit.name());
        driver.manage().timeouts().implicitlyWait(timeout.longValue(), unit);
    }
}