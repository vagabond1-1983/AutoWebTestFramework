package com.kong.baidu.tasks;

import com.kong.baidu.model.ActionType;
import com.kong.util.SimulateAction;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/11/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class searchTask {
    private WebDriver driver;
    private Properties pageElementsMap;
    private SimulateAction simulateAction;

    public searchTask(WebDriver driver, Properties pageElementsMap) {
        this.driver = driver;
        this.pageElementsMap = pageElementsMap;
        simulateAction = new SimulateAction(this.driver);
    }

    public searchTask enterSearchKeyword(String searchKeywordTxtField, String searchKeywordValue) {
        simulateAction.findActElm(searchKeywordTxtField, ActionType.INPUT, searchKeywordValue);
        return this;
    }

    public searchTask clickSearchButton(String searchButtonField) {
        simulateAction.findActElm(searchButtonField, ActionType.CLICK);
        return this;
    }
}
