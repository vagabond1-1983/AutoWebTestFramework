package com.kong.baidu.handle;

import com.kong.baidu.model.IBean;
import org.openqa.selenium.WebDriver;


public abstract class Handler {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public abstract WebDriver handle(IBean bean);

}
