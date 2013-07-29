package com.kong.wd.handle;

import com.kong.wd.model.ActionType;
import com.kong.wd.model.Description;
import com.kong.wd.model.WebElementType;
import com.kong.wd.util.ImageUtil;
import com.kong.wd.util.LogUtil;
import com.kong.wd.util.WebObjectUtil;
import org.apache.logging.log4j.Logger;
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
    private static Logger logger = LogUtil.getLogger(TestElementAction.class);
    private WebDriver driver;

    private Description description;
    // Mouse or keyboard action: click, input, submit
    private ActionType actionType;
    // keyword of by. id, xpath
    private String context;
    // by key, like id, xpath, cssSelector
    private String byKeyword;
    // Value of keyboard or part of file name of capture screenshot
    private String expression;

    WebElementsDigging digging = null;

    public TestElementAction(WebDriver driver, String type, Description description) {
        if (driver == null) {
            throw new NullPointerException();
        }
//        this.driver = driver;
        this.driver = driver;
        this.description = description;
        this.actionType = ActionType.valueOf(type);

        byKeyword = description.getBy();
        context = description.getAction();
        expression = description.getValue();
    }

    // web tag
    private enum Tag {
        a, input, form, table
    }

    public boolean simulateUserAction() {
        if (byKeyword == null || byKeyword.isEmpty()) {
            skipToCapture();
            return true;
        } else {
            digging = new WebElementsDigging(driver,
                    WebElementType.valueOf(byKeyword), context, expression);
        }
        // Real simulate method
        if (digging.hasNext()) {
            simulate(digging.next());
            return true;
        }

        return false;  //To change body of created methods use File | Settings | File Templates.
    }

    private void skipToCapture() {
        switch (actionType) {
            case CAPTURE:
                if (expression != null && !expression.isEmpty()) {
                    String fileName = expression;
                    ImageUtil.captureScreenshot(driver, fileName);
                }
                break;
        }
    }

    private void simulate(WebElement testElement) {
        // TODO Need check the result after acting. And add more components context.
        switch (actionType) {
            case CLICK:
                // Test Element click
                if (iselementAbility(testElement, Tag.a, Tag.input)) {
                    testElement.click();
                }
                break;
            case INPUT:
                // Test Element input
                if (iselementAbility(testElement, Tag.input)) {
                    testElement.sendKeys(expression);
                }
                break;

            case CHECKPOINT:
                if (expression == null || expression.isEmpty()) {
                    throw new IllegalArgumentException();
                }

                // Assert the checkpoint
                // TODO better assert, try to recover log4j
                logger.debug(testElement.getText() + ": " + expression + "=" + testElement.getText().equals(expression));
                break;
            default:
                break;
        }
    }

    private boolean iselementAbility(WebElement testElement, Tag... t) {
        if (testElement == null) {
            throw new NoSuchElementException("Failed to find element");
        }

        logger.debug(testElement.getAttribute("id"));

        Tag tag = Tag.valueOf(testElement.getTagName());
        for (Tag test : t) {
            if (tag.equals(test)) {
                return true;
            }
        }
        return false;
    }
}
