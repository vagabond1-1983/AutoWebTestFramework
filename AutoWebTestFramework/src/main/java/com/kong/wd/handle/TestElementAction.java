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
    private WebElement testElement;
    private Description description;
    private ActionType actionType;
    private String context;
    public TestElementAction(WebDriver driver, String type, Description description) {
        if(driver == null) {
            throw new NullPointerException();
        }
        this.driver = driver;
        this.description = description;
        this.actionType = ActionType.valueOf(type);
    }

    private enum Tag {
        a, input, form, table
    }

    public boolean simulateUserAction() {
        // find element with which way like xpath, css, id
        String byKeyword = description.getBy();
        // Value of keyboard or part of file name of capture screenshot
        String expression = description.getValue();
        // keyword of by. id, xpath
        context = description.getAction();

        // Find element
        if(actionType != ActionType.CHECKPOINT) {
            findElement(byKeyword, context);
        }
        // Real simulate method
        simulate(expression);

        return false;  //To change body of created methods use File | Settings | File Templates.
    }

    private void findElement(String byKeyword, String action) {
        if(byKeyword == null || byKeyword.isEmpty()) {
            return;
        }
        //TODO If there are not only one element to be found. Need to navigate or find the right one.
        testElement = WebObjectUtil.intelligentFindWebElement(driver,
                WebElementType.valueOf(byKeyword), action);
        // No need to check null for that element. In some condition, it will not exist like only value in description or there is checkpoint to consider
        /*if (testElement == null) {
            throw new NoSuchElementException("You context is not supported to find: " + context.toString());
        }*/
    }

    private void simulate(String expression) {
        // TODO Need check the result after acting. And add more components context.
        switch (actionType) {
            case CLICK:
                // Test Element click
                if(iselementAbility(testElement, Tag.a, Tag.input)) {
                    testElement.click();
                }
                break;
            case INPUT:
                // Test Element input
                if(iselementAbility(testElement, Tag.input)) {
                    testElement.sendKeys(expression);
                }
                break;
            case CAPTURE:
                if(expression != null && !expression.isEmpty()) {
                    String fileName = expression;
                    ImageUtil.captureScreenshot(driver, fileName);
                }
                break;
            case CHECKPOINT:
                if(expression == null || expression.isEmpty()) {
                    throw new IllegalArgumentException();
                }

                WebElementsDigging digging = new WebElementsDigging(driver,
                        WebElementType.valueOf(description.getBy()), context);

                if(new WebObjectUtil.SupportSplitPath(context).matchRegex())  {
                    testElement = digging.findWebElementMatchTarget(expression);
                }      else {
                    testElement = digging.intelligentFindWebElement();
                }

                // Assert the checkpoint
                // TODO better assert, try to recover log4j
                logger.debug(testElement.getText() + ": " + expression + "=" +testElement.getText().equals(expression));
                break;
            default:
                break;
        }
    }

    private boolean iselementAbility(WebElement testElement, Tag... t) {
        if(testElement == null) {
            throw new NoSuchElementException("Failed to find element");
        }

        logger.debug(testElement.getAttribute("id"));

        Tag tag = Tag.valueOf(testElement.getTagName());
        for (Tag test : t) {
            if(tag.equals(test)) {
                return true;
            }
        }
        return false;
    }
}
