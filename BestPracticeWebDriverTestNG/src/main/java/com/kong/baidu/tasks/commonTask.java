package com.kong.baidu.tasks;

import com.kong.baidu.model.ActionType;
import com.kong.util.PropUtils;
import com.kong.util.SimulateAction;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/11/13
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class commonTask {
    protected Properties elemMap;
    private final static String PROP_NAME = "baiduPages.properties";
    private commonTask ct = null;

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

}