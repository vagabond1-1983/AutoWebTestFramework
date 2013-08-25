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
public class baiduLoginTask {
    private WebDriver driver;
    private Properties pageElementsMap;
    private SimulateAction simulateAction;

    public baiduLoginTask(WebDriver driver, Properties pageElementsMap) {
        this.driver = driver;
        this.pageElementsMap = pageElementsMap;
        simulateAction = new SimulateAction(driver);
    }

    public baiduLoginTask openLoginDialog(String loginDialogLinkField) {
        simulateAction.findActElm(loginDialogLinkField, ActionType.CLICK);
        return this;
    }

    public baiduLoginTask enterUserName(String userNameTxtField, String userNameValue) {
        simulateAction.findActElm(userNameTxtField, ActionType.INPUT, userNameValue);
        return this;
    }

    public baiduLoginTask enterPassword(String passwordTxtField, String passwordValue) {
        simulateAction.findActElm(passwordTxtField, ActionType.INPUT, passwordValue);
        return this;
    }

    public baiduLoginTask submit(String loginButtonField) {
        simulateAction.findActElm(loginButtonField, ActionType.CLICK);
        return this;
    }

    /*public baiduLoginTask captureScreenshot() {
        simulateAction.findActElm(captureLogin, ActionType.CAPTURE);
        return this;
    }*/
}
