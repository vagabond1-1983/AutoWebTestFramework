package com.kong.baidu.testCases;

import com.kong.baidu.controller.Helper;
import com.kong.baidu.handle.CaseHandler;
import com.kong.baidu.tasks.baiduLoginTask;
import com.kong.baidu.tasks.baiduTestConstant;
import com.kong.baidu.tasks.commonTask;
import com.kong.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.Strings;
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
 * To change this template use File | Settings | File Templates.
 */
public class loginCase {
    private HashMap<String, Object> paraMap;
    private Properties pagesMap;
    public static Logger logger = LogUtil.getLogger(loginCase.class);
    private WebDriver driver;
    private commonTask loginTask;

    @Parameters({"baiduLoginCase", "baiduRoles"})
    @Test
    public void test(String paraFile, String paraRoles) {
//        TODO TestNG + Spring: not easy to manage suite or helper in each case code
        Helper helper = CaseHandler.getInstance().getHelperContext(paraFile, paraRoles);
        driver = helper.getCurrentDriver();
        pagesMap = helper.getPagesMap();

        logger.debug("Start loginCase test!");
        logger.debug("click loginCase button");

        paraMap = helper.getParamMap();

        loginTask = new baiduLoginTask(driver, pagesMap);
        loginTask.clickElement((String) pagesMap.get(baiduTestConstant.LOGIN_BUTTON_IN_PAGE))
                .enterValue2Element((String) pagesMap.get(baiduTestConstant.LOGIN_USERNAME_TXT_FIELD), (String) paraMap.get(baiduTestConstant.LOGIN_USERNAME_TXT_FIELD))
                .enterValue2Element((String) pagesMap.get(baiduTestConstant.LOGIN_PASSWORD_TXT_FIELD), (String) paraMap.get(baiduTestConstant.LOGIN_PASSWORD_TXT_FIELD))
                .clickElement((String) pagesMap.get(baiduTestConstant.LOGIN_SUBMIT_BUTTON));
        if (paraMap.containsKey(baiduTestConstant.CAPTURE)) {
            loginTask.captureScreenshot((String) paraMap.get(baiduTestConstant.CAPTURE));
        }
        Assert.assertTrue(loginTask.verifyField((String) pagesMap.get(baiduTestConstant.LOGIN_SUCCEED_USERNAME_FIELD), (String) paraMap.get(baiduTestConstant.LOGIN_USERNAME_TXT_FIELD)));
    }

}
