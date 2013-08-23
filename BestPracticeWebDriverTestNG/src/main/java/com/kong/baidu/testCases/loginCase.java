package com.kong.baidu.testCases;

import com.kong.baidu.controller.ContextConstant;
import com.kong.baidu.controller.Helper;
import com.kong.baidu.controller.Suite;
import com.kong.baidu.handle.CaseHandler;
import com.kong.baidu.tasks.baiduLoginTask;
import com.kong.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
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
    private baiduLoginTask loginTask;

    @Parameters({"baiduLoginCase", "baiduRoles"})
    @Test
    public void test(String paraFile, String paraRoles) {
//        TODO TestNG + Spring: not easy to manage suite or helper in each case code
        Helper helper = CaseHandler.getInstance().getHelperContext(paraFile, paraRoles);
        driver = (WebDriver) helper.getContext().get(ContextConstant.DRIVER_CONTEXT);
        pagesMap = (Properties) helper.getContext().get(ContextConstant.PAGES_MAP_CONTEXT);

        logger.debug("Start loginCase test!");
        logger.debug("click loginCase button");

        paraMap = (HashMap<String, Object>) helper.getContext().get(ContextConstant.PARAM_MAP_CONTEXT);

        loginTask = new baiduLoginTask(driver, pagesMap);
        loginTask.openLoginDialog();
        loginTask.enterUserName(paraMap);
        loginTask.enterPassword(paraMap);
        loginTask.submit();
    }

}
