package com.kong.baidu.testCases;

import com.kong.baidu.tasks.PageConst;
import com.kong.common.controller.ContextContainer;
import com.kong.common.handle.CaseImportHandler;
import com.kong.common.Tasks.commonTask;
import com.kong.baidu.tasks.searchTask;
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
 * To change this template use File | BrowserSettings | File Templates.
 */
public class searchCase {
    public static Logger logger = LogUtil.getLogger(searchCase.class);
    private WebDriver driver;
    private commonTask searchTask;

    @Parameters({"searchCase", "baiduRoles"})
    @Test
    public void search(String paraFile, String paraRoles) {
        ContextContainer context = CaseImportHandler.getInstance().getContextContainer(paraFile, paraRoles);
        driver = context.getCurrentDriver();

        logger.debug("Start searchCase loginMainPage!");
        logger.debug("click searchCase button");

        searchTask = new searchTask(driver);
        searchTask.enterValue2Element((String) context.getPageValue(PageConst.SEACH_KEYWORD_FIELD), (String) context.getParamValue(PageConst.SEACH_KEYWORD_FIELD))
                .clickElement((String) context.getPageValue(PageConst.SEACH_BUTTON));
    }
}
