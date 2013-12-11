package com.kong.common.handle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/5/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirefoxKDriver implements IDriver {


    @Override
    public WebDriver setupDriver() {
        return new FirefoxDriver();
    }
}
