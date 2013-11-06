package com.kong.baidu.testCases;

import com.kong.common.controller.ContextContainer;
import com.kong.common.handle.CaseImportHandler;
import com.kong.baidu.tasks.baiduLoginTask;
import com.kong.baidu.tasks.baiduTestConstant;
import com.kong.baidu.tasks.commonTask;
import com.kong.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/9/13
 * Time: 10:22 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class loginCase {
    private HashMap<String, Object> paraMap;
    private Properties pagesMap;
    public static Logger logger = LogUtil.getLogger(loginCase.class);
    private WebDriver driver;
    private commonTask loginTask;

    @Parameters({"baiduLoginCase", "baiduRoles"})
    @Test
    public void loginMainPage(String paraFile, String paraRoles) throws InterruptedException {
//        TODO TestNG + Spring: not easy to manage suite or contextContainer in each case code
        ContextContainer contextContainer = CaseImportHandler.getInstance().getContextContainer(paraFile, paraRoles);
        driver = contextContainer.getCurrentDriver();
        pagesMap = contextContainer.getPagesMap();

        logger.debug("Start loginCase loginMainPage!");
        logger.debug("click loginCase button");

        paraMap = contextContainer.getParamMap();

        loginTask = new baiduLoginTask(driver, pagesMap);
        loginTask.clickElement((String) pagesMap.get(baiduTestConstant.LOGIN_BUTTON_IN_PAGE))
                .enterValue2Element((String) pagesMap.get(baiduTestConstant.LOGIN_USERNAME_TXT_FIELD), (String) paraMap.get(baiduTestConstant.LOGIN_USERNAME_TXT_FIELD))
                .enterValue2Element((String) pagesMap.get(baiduTestConstant.LOGIN_PASSWORD_TXT_FIELD), (String) paraMap.get(baiduTestConstant.LOGIN_PASSWORD_TXT_FIELD))
                .clickElement((String) pagesMap.get(baiduTestConstant.LOGIN_SUBMIT_BUTTON));
        // Explain:
        // After clicking login, popup dialog still exist in page.
        // Driver is pointed to sub frame not the main frame.
        // First wait the click action happened then judge the page is loaded
        // If not, continue to wait. If yes, then next.
        // TODO need extract executescript to common util
        do {

            Thread.sleep(5000);
        } while ((Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState == 'completed'"));

        if (paraMap.containsKey(baiduTestConstant.CAPTURE)) {
            loginTask.captureScreenshot((String) paraMap.get(baiduTestConstant.CAPTURE));
        }

        // TODO    vagabond1_1983 should from user field defined before
        Assert.assertTrue(loginTask.verifyField((String) pagesMap.get(baiduTestConstant.LOGIN_SUCCEED_USERNAME_FIELD), "vagabond1_1983"));
    }

}
