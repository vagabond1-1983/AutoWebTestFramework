package com.kong.common.handle;

import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/5/13
 * Time: 10:38 PM
 * Driver interface to lead different driver instance.
 */
public interface IDriver {
    public WebDriver setupDriver();
}
