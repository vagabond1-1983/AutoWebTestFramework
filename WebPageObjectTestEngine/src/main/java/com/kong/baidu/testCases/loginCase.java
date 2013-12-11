package com.kong.baidu.testCases;

import com.kong.baidu.pageObjects.ILogin;
import com.kong.baidu.tasks.PageConst;
import com.kong.common.Tasks.commonTask;
import com.kong.common.controller.ContextContainer;
import com.kong.common.handle.CaseImportHandler;
import com.kong.util.image.ImageUtil;
import com.kong.util.log.LogUtil;
import com.kong.util.webpage.Delay.PageUntilLoaded;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/9/13
 * Time: 10:22 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class loginCase {
    public static Logger logger = LogUtil.getLogger(loginCase.class);
    private WebDriver driver;
    private commonTask loginTask;

    // TODO Devin Dec 11 2013
    // As there is common way to load scenarios and steps and execute methods also, we should consider common handler to build scenario and stepExecute
    @Parameters({"baiduLoginCase", "baiduRoles", "loginPage"})
    @Test
    public void loginMainPage(String paraFile, String paraRoles, String loginPage) throws InterruptedException {
        ContextContainer context = CaseImportHandler.getInstance().getContextContainer(paraFile, paraRoles);
        driver = context.getCurrentDriver();

        logger.debug("Start loginCase loginMainPage!");
        logger.debug("click loginCase button");

//        driver.manage().window().maximize();
        try {
            pageObjectLogin(context, Class.forName(loginPage));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        /*loginTask = new baiduLoginTask(driver);
        loginTask.clickElement((String) context.getPageValue(PageConst.LOGIN_BUTTON_IN_PAGE))
                .enterValue2Element((String) context.getPageValue(PageConst.LOGIN_USERNAME_TXT_FIELD), (String) context.getParamValue(PageConst.LOGIN_USERNAME_TXT_FIELD))
                .enterValue2Element((String) context.getPageValue(PageConst.LOGIN_PASSWORD_TXT_FIELD), (String) context.getParamValue(PageConst.LOGIN_PASSWORD_TXT_FIELD))
                .clickElement((String) context.getPageValue(PageConst.LOGIN_SUBMIT_BUTTON));
        // Explain:
        // After clicking login, popup dialog still exist in page.
        // Driver is pointed to sub frame not the main frame.
        // First wait the click action happened then judge the page is loaded
        // If not, continue to wait. If yes, then next.
        *//*do {

            Thread.sleep(5000);
        } while ((Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState == 'completed'"));*//*
        PageUntilLoaded.pageLoaded(driver);

        if (null != context.getParamValue(PageConst.CAPTURE)) {
            loginTask.captureScreenshot((String) context.getParamValue(PageConst.CAPTURE));
        }

        if (null != context.getParamValue(PageConst.VERIFY)) {
            Assert.assertTrue(loginTask.verifyField((String) context.getPageValue(PageConst.LOGIN_SUCCEED_USERNAME_FIELD), (String) context.getParamValue(PageConst.VERIFY)));
        }*/
    }

    public void pageObjectLogin(ContextContainer context, Class<?> loginPageClass) {
        //TODO some thought here 2013/12/05
        // Write methods and parameters in test case, then auto recogninaze step by step.
        // Match page object first, then match methods, find parameters also in test case xml, then run them
        ILogin iLogin = (ILogin) PageFactory.initElements(driver, loginPageClass);
        iLogin.loginForm((String) context.getParamValue(PageConst.LOGIN_USERNAME_TXT_FIELD), (String) context.getParamValue(PageConst.LOGIN_PASSWORD_TXT_FIELD));

        PageUntilLoaded.pageLoaded(driver);

        if(null != context.getParamValue(PageConst.CAPTURE)) {
            ImageUtil.captureScreenshot(driver, (String) context.getParamValue(PageConst.CAPTURE));
        }
        Assert.assertTrue(iLogin.isLogin((String) context.getParamValue(PageConst.VERIFY)));
    }
}
