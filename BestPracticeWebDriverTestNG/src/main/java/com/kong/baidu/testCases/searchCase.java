package com.kong.baidu.testCases;

import com.kong.baidu.controller.Helper;
import com.kong.baidu.controller.Suite;
import com.kong.baidu.handle.CaseHandler;
import com.kong.baidu.model.TestCase;
import com.kong.baidu.tasks.baiduTestConstant;
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
        Helper helper = CaseHandler.getInstance().getHelperContext(paraFile, paraRoles);
        driver = helper.getCurrentDriver();
        pagesMap = helper.getPagesMap();

        logger.debug("Start searchCase test!");
        logger.debug("click searchCase button");

        paraMap = helper.getParamMap();

        searchTask = new searchTask(driver, pagesMap);
        searchTask.enterSearchKeyword((String) pagesMap.get(baiduTestConstant.SEACH_KEYWORD_FIELD), (String) paraMap.get(baiduTestConstant.SEACH_KEYWORD_FIELD))
                .clickSearchButton((String) pagesMap.get(baiduTestConstant.SEACH_BUTTON));
    }
}
