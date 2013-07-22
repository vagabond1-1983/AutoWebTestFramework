package com.kong.wd.handle;

import org.openqa.selenium.WebDriver;
import com.kong.wd.model.IBean;
import com.kong.wd.model.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StepHandler extends Handler {
    private static Logger logger = LogManager.getLogger(StepHandler.class);
    private Step step;
    private WebDriver driver;
    private TestElementAction testElementAction = null;

    public StepHandler(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver handle(IBean bean) {

        step = (Step) bean;
        logger.trace("Start to handle step: [" + step.getIndex() + "] " + step.getName());

        // Set test element
        testElementAction = new TestElementAction(driver, step.getType(), step.getDescription());

        testElementAction.simulateUserAction();
        logger.trace("End with handle step: [" + step.getIndex() + "] " + step.getName());

        return driver;
    }

}
