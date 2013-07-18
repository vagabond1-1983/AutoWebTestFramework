package com.kong.wd.handle;

import com.kong.wd.model.ActionType;
import com.kong.wd.model.Description;
import com.kong.wd.model.WebElementType;
import com.kong.wd.util.ImageUtil;
import com.kong.wd.util.WebObjectUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/18/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestElementAction {
    private static Logger logger = Logger.getLogger(TestElementAction.class);

    private WebDriver driver;
    private WebElement testElement;
    private Description description;
    private ActionType actionType;
    private String action;
    public TestElementAction(WebDriver driver, String type, Description description) {
        this.driver = driver;
        this.description = description;
        this.actionType = ActionType.valueOf(type);
    }

    public boolean simulateUserAction() {
        // find element with which way like xpath, css, id
        String byKeyword = description.getBy();
        // Value of keyboard or part of file name of capture screenshot
        String value = description.getValue();
        // keyword of by. id, xpath
        action = description.getAction();

        // Find element
        findElement(byKeyword, action);
        // Real simulate method
        simulate(value);

        return false;  //To change body of created methods use File | Settings | File Templates.
    }

    private void findElement(String byKeyword, String action) {
        if(byKeyword == null || byKeyword.isEmpty()) {
            return;
        }

        testElement = WebObjectUtil.findWebElement(driver,
                WebElementType.valueOf(byKeyword), action);
        if (testElement == null) {
            throw new NoSuchElementException("You action is not supported to find: " + action.toString());
        }
    }

    private void simulate(String value) {
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
    }
}
