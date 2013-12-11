package com.kong.baidu.testCases;

import com.kong.common.Tasks.commonTask;
import com.kong.common.controller.ContextConstant;
import com.kong.common.controller.ContextContainer;
import com.kong.common.handle.CaseImportHandler;
import com.kong.util.log.LogUtil;
import com.kong.util.webpage.JSExecutor.JQuery;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

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

        /*searchTask = new searchTask(driver);
        searchTask.enterValue2Element((String) context.getPageValue(PageConst.SEACH_KEYWORD_FIELD), (String) context.getParamValue(PageConst.SEACH_KEYWORD_FIELD))
                .clickElement((String) context.getPageValue(PageConst.SEACH_BUTTON));*/

        // Debug JQuery
        //TODO two ways now in picture:    2013/11/15
        // 1. task way: define each task in task class and use them in case level
        // 2. In case level, auto detect id, value in paramMap, call corresponding methonds. Like: id contains jquery
        //     then jquery to runJS; id and value pairs then entervalue2element; only id, just clickelement. Define action
        //     first in xml
        // If first way, task can be common. But need define them first
        // If second way, xml should be followed the rules without exception.
        for (Map.Entry entry : context.getParamMap().entrySet())    {
            if(entry.getKey().toString().startsWith("jquery_")) {
                ((JQuery) context.getContext().get(ContextConstant.JQUERY_CONTEXT)).runJs((String) entry.getValue());
            }
        }
    }
}
