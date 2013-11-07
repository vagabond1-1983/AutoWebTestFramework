package com.kong.util.webpage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSExecution {
    public static Object executeJS(WebDriver driver, String javascript) {
        Object result;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        result = js.executeScript(javascript);
        return result;
    }
}