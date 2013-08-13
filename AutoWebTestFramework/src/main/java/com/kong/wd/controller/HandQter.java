package com.kong.wd.controller;

import com.kong.wd.handle.Handler;
import com.kong.wd.handle.InitEnvHandler;
import com.kong.wd.handle.StepHandler;
import com.kong.wd.handle.TestCaseHandler;
import com.kong.wd.model.Case;
import com.kong.wd.model.Step;
import com.kong.wd.model.Suite;
import com.kong.wd.util.WebObjectUtil;
import com.kong.wd.util.XmlRulesDriver;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/12/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HandQter {
    public static void main(String[] args) {
//        String inputFile = "AutoWebTestFramework/UseCase/BaiduDWTest.xml";
        String inputFile = "AutoWebTestFramework/UseCase/JDDWTest.xml";
        String roleFile = "AutoWebTestFramework/UseCase/baidu-rules.xml";
        XmlRulesDriver dataDriver = new XmlRulesDriver(inputFile, roleFile);
        Suite suite = dataDriver.xml2Bean();
        WebDriver driver = null;
        if (suite != null) {
            Handler handler = null;

            // Initialize header for environment
            handler = new InitEnvHandler();
            driver = handler.handle(suite.getSettings());
            handler = null;

            // Deal with one case
            for (Case item : suite.getCases()) {
                if (handler == null) {
                    handler = new TestCaseHandler(driver);
                }

                driver = handler.handle(item);

                // Deal with steps iterator
                handler = new StepHandler(driver);
                for (Step step : item.getSteps()) {
                    if (driver != null) {
                        driver = handler.handle(step);
                    }
                }
                handler = null;
            }
        }

        // Close driver and test object browser
        if (driver != null) {
//                driver.quit();
            WebObjectUtil.closeAllWindows(driver);
        }
    }
}
