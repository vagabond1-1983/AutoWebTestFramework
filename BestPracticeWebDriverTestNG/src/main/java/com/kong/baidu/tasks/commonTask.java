package com.kong.baidu.tasks;

import com.kong.baidu.model.ActionType;
import com.kong.util.ImageUtil;
import com.kong.util.LogUtil;
import com.kong.util.PropUtils;
import com.kong.util.SimulateAction;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/11/13
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class commonTask {
    private static final Logger logger = LogUtil.getLogger(commonTask.class);

    protected Properties elemMap;
    private final static String PROP_NAME = "baiduPages.properties";

    protected WebDriver driver;
    protected Properties pageElementsMap;
    protected SimulateAction simulateAction;

    public commonTask(WebDriver driver, Properties pageElementsMap) {
        this.driver = driver;
        this.pageElementsMap = pageElementsMap;
        simulateAction = new SimulateAction(driver);
    }

    public Properties getPageElements() {
        elemMap = PropUtils.getProperties(PROP_NAME);
        return elemMap;
    }

    public commonTask clickElement(String elementField) {
        simulateAction.findActElm(elementField, ActionType.CLICK);
        return this;
    }

    public commonTask enterValue2Element(String elementField, String value) {
        simulateAction.findActElm(elementField, ActionType.INPUT, value);
        return this;
    }

    public commonTask captureScreenshot(String value) {
        ImageUtil.captureScreenshot(driver, value);
        return this;
    }

    public boolean verifyField(String elementField, String value) {
        WebElement elm = verifyField(elementField);
        if (elm != null) {
            logger.debug("Verify the field is not null and compare the text value.");
            return elm.getText().contains(value);
        }
        return false;
    }

    public WebElement verifyField(String elementField) {
        return simulateAction.findElm(elementField);
    }
}