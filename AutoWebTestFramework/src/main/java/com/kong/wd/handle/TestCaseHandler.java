package com.kong.wd.handle;

import java.util.concurrent.TimeUnit;

import com.kong.wd.util.WebObjectUtil;
import org.openqa.selenium.WebDriver;

import com.kong.wd.model.IBean;
import com.kong.wd.model.Case;


public class TestCaseHandler extends Handler {
    private WebDriver driver;


    public TestCaseHandler(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver handle(IBean bean) {
        if (bean == null) {
            throw new NullPointerException("test case bean does not exist");
        }
        Case testBean = (Case) bean;
        if (driver == null) {
            throw new NullPointerException("driver is null while under test case handling");
        }
        WebObjectUtil.timeoutWait(driver, testBean.getTimeout());
        return driver;
    }


}
