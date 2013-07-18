package com.kong.wd.handle;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kong.wd.util.ImageUtil;
import com.kong.wd.util.WebObjectUtil;
import com.kong.wd.model.ActionType;
import com.kong.wd.model.IBean;
import com.kong.wd.model.Step;
import com.kong.wd.model.WebElementType;

public class StepHandler extends Handler {
    private Step step;
    private WebDriver driver;

    public StepHandler(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver handle(IBean bean) {
        step = (Step) bean;
        TestElementAction testElementAction = null;


        // Set test element
        testElementAction = new TestElementAction(driver, step.getType(), step.getDescription());

        testElementAction.simulateUserAction();

        return driver;
    }

}
