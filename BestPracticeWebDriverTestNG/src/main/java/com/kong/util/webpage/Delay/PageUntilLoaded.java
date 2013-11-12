package com.kong.util.webpage.Delay;

import com.kong.util.webpage.JSExecution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PageUntilLoaded {
    public static Logger logger = LogManager.getLogger(PageUntilLoaded.class);

    public static void pageLoaded(WebDriver driver) {
        String pageReadyComplete = "return document.readyState=='complete'";

        boolean loaded = false;
        while (!loaded) {
            Object execJSResult = runningJSUnderCondition(driver, pageReadyComplete);
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
        while (null == actualPageTitle || !actualPageTitle.toString().equals(expectPageTitle)) {
            actualPageTitle = runningJSUnderCondition(driver, getTitleScript);
        }
        logger.debug("Wait complete to find the web page which title is: " + actualPageTitle);
    }

    private static Object runningJSUnderCondition(WebDriver driver, String js) {
        WaitTillTimeOut.timeoutWait(driver);
        return JSExecution.executeJS((JavascriptExecutor) driver, js);
    }
}
