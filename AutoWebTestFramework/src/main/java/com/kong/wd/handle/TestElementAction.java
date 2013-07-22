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

    private enum Tag {
        a, input, form, table
    }

    public boolean simulateUserAction() {
        // find element with which way like xpath, css, id
        String byKeyword = description.getBy();
        // Value of keyboard or part of file name of capture screenshot
        String value = description.getValue();
        // keyword of by. id, xpath
        action = description.getAction();

        // Find element
        if(actionType != ActionType.CHECKPOINT) {
            findElement(byKeyword, action);
        }
        // Real simulate method
        simulate(value);

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
            throw new NoSuchElementException("You action is not supported to find: " + action.toString());
        }*/
    }

    private void simulate(String value) {
        // TODO Need check the result after acting. And add more components action.
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
                    testElement.sendKeys(value);
                }
                break;
            case CAPTURE:
                if(value != null && !value.isEmpty()) {
                    String fileName = value;
                    ImageUtil.captureScreenshot(driver, fileName);
                }
                break;
            case CHECKPOINT:
                if(value == null || value.isEmpty()) {
                    throw new IllegalArgumentException();
                }

                if(new WebObjectUtil.SupportSplitPath(action).matchRegex())  {
                    testElement = WebObjectUtil.findWebElementMatchTarget(driver,
                            WebElementType.valueOf(description.getBy()), action, value);
                }      else {
                    testElement = WebObjectUtil.intelligentFindWebElement(driver,
                            WebElementType.valueOf(description.getBy()), action);
                }

                // Assert the checkpoint
                // TODO better assert, try to recover log4j
                System.out.println(testElement.getText() + ": " + value + "=" +testElement.getText().equals(value));
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
