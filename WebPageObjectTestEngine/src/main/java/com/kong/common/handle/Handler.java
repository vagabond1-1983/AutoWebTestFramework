package com.kong.common.handle;

import com.kong.common.model.IBean;
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
