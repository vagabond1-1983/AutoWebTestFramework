package com.kong.baidu.testCases;

import com.kong.baidu.Suite;
import com.kong.baidu.model.TestCase;
import com.kong.baidu.tasks.baiduLoginTask;
import com.kong.util.LogUtil;
import com.kong.util.XmlRulesDriver;
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
        driver = Suite.getDriver();
        pagesMap = Suite.getPagesMap();

        logger.debug("Start loginCase test!");
        logger.debug("click loginCase button");

        paraMap = ((TestCase) new XmlRulesDriver(paraFile, paraRoles).xml2Bean()).getParamMap();

        loginTask = new baiduLoginTask(driver, pagesMap);
        loginTask.openLoginDialog();
        loginTask.enterUserName(paraMap);
        loginTask.enterPassword(paraMap);
        loginTask.submit();
    }

}
