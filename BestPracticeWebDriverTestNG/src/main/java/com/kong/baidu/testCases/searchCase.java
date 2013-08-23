package com.kong.baidu.testCases;

import com.kong.baidu.controller.Suite;
import com.kong.baidu.model.TestCase;
import com.kong.baidu.tasks.searchTask;
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
public class searchCase {
    private HashMap<String, Object> paraMap;
    private Properties pagesMap;
    public static Logger logger = LogUtil.getLogger(searchCase.class);
    private WebDriver driver;
    private searchTask searchTask;

    @Parameters({"searchCase", "baiduRoles"})
    @Test
    public void search(String paraFile, String paraRoles) {

        driver = Suite.getDriver();
        pagesMap = Suite.getPagesMap();

        logger.debug("Start searchCase test!");
        logger.debug("click searchCase button");

        paraMap = ((TestCase) new XmlRulesDriver(paraFile, paraRoles).xml2Bean()).getParamMap();

        searchTask = new searchTask(driver, pagesMap);
        searchTask.enterSearchKeyword(paraMap);
        searchTask.clickSearchButton();
    }

}
