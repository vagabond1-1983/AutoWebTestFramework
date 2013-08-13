package com.kong.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebObjectUtil {
    public static Logger logger = LogManager.getLogger(WebObjectUtil.class);
    public static final int DEFAULT_TIMEOUT_SEC = 10;

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
        logger.debug("Wait for " + timeout + " " + unit.name());
        driver.manage().timeouts().implicitlyWait(timeout.longValue(), unit);
    }

    public static void pageLoaded(WebDriver driver) {
        String pageReadyComplete = "return document.readyState=='complete'";

        boolean loaded = false;
        while (!loaded) {
            timeoutWait(driver);
            Object execJSResult = executeJS(driver, pageReadyComplete);
            if (execJSResult != null) {
                loaded = (Boolean) execJSResult;
            }
        }
        logger.debug("Page is completely loaded!");
    }

    public static void pageLoaded(WebDriver driver, String title) {
        String expectPageTitle = title;
        String getTitleScript = "return document.title";
        Object actualPageTitle = null;
        while (actualPageTitle == null || !actualPageTitle.toString().equals(expectPageTitle)) {
            timeoutWait(driver);
            actualPageTitle = executeJS(driver, getTitleScript);
        }
        logger.debug("Wait complete to find the web page which title is: " + actualPageTitle);
    }

    private static Object executeJS(WebDriver driver, String javascript) {
        Object result;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        result = js.executeScript(javascript);
        return result;
    }


}
