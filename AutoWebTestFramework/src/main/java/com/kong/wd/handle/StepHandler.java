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
        WebElement testElement = null;

        String type = step.getType();
        String byKeyword = step.getDescription().getBy();
        String value = step.getDescription().getValue();
        String action = step.getDescription().getAction();

        // Set test element
        if (null != byKeyword && !byKeyword.isEmpty()) {
            testElement = WebObjectUtil.findWebElement(driver,
                    WebElementType.XPATH, action);
            if (testElement == null) {
                throw new NoSuchElementException("You action is not supported to find: " + action.toString());
            }
        }

        ActionType actionType = ActionType.valueOf(type);

        switch (actionType) {
            case CLICK:
                // Test Element click
                testElement.click();
                break;
            case INPUT:
                // Test Element input
                testElement.sendKeys(value);
                break;
            case CAPTURE:
                String fileName = value;
                ImageUtil.captureScreenshot(driver, fileName);
                break;
            case CHECKPOINT:
                // If title element has value, need to check title matches
                if (null != value && !value.isEmpty()) {
                    logger.debug(WebObjectUtil.findDriverTitle(driver).indexOf(
                            value) > -1 ? "Driver title: '" + value
                            + "' matches the real title." : "Driver title: '"
                            + value + "' does not match the real title.");
                }

                // If value element has value, need to check value matches
                // testElement value
                if (null != value && !value.isEmpty()) {
                    //TODO Hard coding way to get attribute value. Not very good.
                    // Try to consider structure of xml. Especially, STEP element and sub element
                    String actualValue = testElement.getText();
                    if (null != action
                            && !action.isEmpty()
                            && action.split(":")[0].toLowerCase().equals(
                            "attribute")) {
                        actualValue = testElement
                                .getAttribute(action.split(":")[1]);
                    }
                    logger.debug(actualValue.indexOf(value) > -1 ? "Value: '"
                            + value + "' matches the real value on page tag: '"
                            + testElement.getTagName() + "'." : "Value: '" + value
                            + "' does not match the real value on page tag: '"
                            + testElement.getTagName() + "'.");

                }
                break;
            default:
                break;
        }
        return driver;
    }

}
