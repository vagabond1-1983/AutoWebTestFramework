package com.kong.wd.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.kong.wd.model.WebElementType;

public class WebObjectUtil {
    public static WebElement findWebElement(WebDriver driver, WebElementType webElementType, String xpath) {
        WebElement element = null;
        By by = null;
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        switch (webElementType) {
            case xpath:
            default:
                by = By.xpath(xpath);
                break;
        }
        element = driver.findElement(by);
        return element;
    }

    public static String findDriverTitle(WebDriver driver) {
        if (driver == null) {
            return "";
        }
        return driver.getTitle();
    }
}
