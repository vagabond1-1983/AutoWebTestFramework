package com.kong.util;

import com.kong.baidu.model.ActionType;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/10/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimulateAction {
    private WebDriver driver;
    private WebElementsDigging digging;

    public SimulateAction(WebDriver driver) {
        this.driver = driver;
    }

    public SimulateAction findActElm(String expression, ActionType type, String... keywords) {
        if (type.equals(ActionType.CAPTURE)) {
            ImageUtil.captureScreenshot(driver, expression);
            return this;
        }
        WebElement element = findElm(expression);
        if (element == null) {
            throw new NoSuchElementException("No element like " + expression);
        }
        switch (type) {
            case INPUT:
                element.sendKeys(keywords);
                break;
            case CLICK:
                element.click();
                break;
        }
        return this;
    }

    public WebElement findElm(String expression) {
        WebElement elm = null;
        digging = new WebElementsDigging(driver, expression);

        if (digging.hasNext()) {
            elm = digging.next();
            digging = null;
        }
        return elm;
    }
}
