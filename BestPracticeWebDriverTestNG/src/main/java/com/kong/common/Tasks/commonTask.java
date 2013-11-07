package com.kong.common.Tasks;

import com.kong.common.model.ActionType;
import com.kong.util.ImageUtil;
import com.kong.util.LogUtil;
import com.kong.util.webpage.SimulateAction;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/11/13
 * Time: 10:31 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public abstract class commonTask {
    private static final Logger logger = LogUtil.getLogger(commonTask.class);

    protected WebDriver driver;
    protected SimulateAction simulateAction;

    public commonTask(WebDriver driver) {
        this.driver = driver;
        simulateAction = new SimulateAction(driver);
    }

    /**
     * Click page element
     *
     * @param elementField from pagesMap: page element in GUI
     * @return
     */
    public commonTask clickElement(String elementField) {
        simulateAction.findActElm(elementField, ActionType.CLICK);
        return this;
    }

    public commonTask submitForm(String formField) {
        simulateAction.findActElm(formField, ActionType.SUBMIT);
        return this;
    }

    /**
     * Enter value to element
     *
     * @param elementField from pagesMap: page element in GUI
     * @param value        from paramsMap: value in case xml defined
     * @return
     */
    public commonTask enterValue2Element(String elementField, String value) {
        simulateAction.findActElm(elementField, ActionType.INPUT, value);
        return this;
    }

    public commonTask captureScreenshot(String value) {
        // TODO simulateAction interface not direct calling ImageUtil
        ImageUtil.captureScreenshot(driver, value);
        return this;
    }

    /**
     * Get text in web element field and compare with expected value
     *
     * @param elementField from pagesMap: page element in GUI
     * @param value        from paramsMap: value in case xml defined
     * @return true or false
     */
    public boolean verifyField(String elementField, String value) {
        WebElement elm = verifyField(elementField);
        if (elm != null) {
            logger.debug("Verify the field is not null and compare the text value.");
            if (!elm.getText().isEmpty()) {
                return elm.getText().contains(value);
            }
        }
        return false;
    }

    /**
     * Can find web element in page
     *
     * @param elementField
     * @return
     */
    public WebElement verifyField(String elementField) {
        return simulateAction.findElm(elementField);
    }
}