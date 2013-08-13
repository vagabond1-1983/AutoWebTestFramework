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

    public void openLoginDialog() {
        simulateAction.findElm((String) pageElementsMap.get(baiduTestConstant.LOGIN_BUTTON_IN_PAGE), ActionType.CLICK);
    }

    public void enterUserName(HashMap<String, Object> paraMap) {
        simulateAction.findElm((String) pageElementsMap.get(baiduTestConstant.LOGIN_USERNAME_TXT_FIELD), ActionType.INPUT, (String) paraMap.get(baiduTestConstant.LOGIN_USERNAME_TXT_FIELD));
    }

    public void enterPassword(HashMap<String, Object> paraMap) {
        simulateAction.findElm((String) pageElementsMap.get(baiduTestConstant.LOGIN_PASSWORD_TXT_FIELD), ActionType.INPUT, (String) paraMap.get(baiduTestConstant.LOGIN_PASSWORD_TXT_FIELD));
    }

    public void submit() {
        simulateAction.findElm((String) pageElementsMap.get(baiduTestConstant.LOGIN_SUBMIT_BUTTON), ActionType.CLICK);
    }

    public void captureScreenshot() {
//        simulateAction.findElm(captureLogin, ActionType.CAPTURE);
    }
}
