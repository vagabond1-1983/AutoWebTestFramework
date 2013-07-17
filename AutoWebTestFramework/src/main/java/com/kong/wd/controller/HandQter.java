package com.kong.wd.controller;

import com.kong.wd.handle.Handler;
import com.kong.wd.handle.InitEnvHandler;
import com.kong.wd.handle.StepHandler;
import com.kong.wd.handle.TestCaseHandler;
import com.kong.wd.model.Case;
import com.kong.wd.model.Step;
import com.kong.wd.model.Suite;
import com.kong.wd.util.XmlRulesDriver;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/12/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HandQter {
    public  static void  main(String[] args) {
        String inputFile = "AutoWebTestFramework/UseCase/BaiduDWTest.xml";
        String roleFile = "AutoWebTestFramework/UseCase/baidu-rules.xml";
        XmlRulesDriver dataDriver = new XmlRulesDriver(inputFile, roleFile);
        Suite suite = dataDriver.xml2Bean();
        WebDriver driver = null;
        if(suite != null) {
            Handler handler = null;

            // Initialize header for environment
            handler = new InitEnvHandler();
            driver = handler.handle(suite.getSettings());
            handler = null;

            // Deal with one case
            for(Case item: suite.getCases()) {
                if(driver != null) {
                    handler =  new TestCaseHandler(driver);
                    driver = handler.handle(item);
                    handler = null;

                    // Deal with steps iterator
                    //TODO item.getSteps().getSteps().... writeen format is very strengy.
                    for(Step step : item.getSteps().getSteps()) {
                        if(driver != null) {
                            handler = new StepHandler(driver);
                            driver = handler.handle(step);
                            handler = null;
                        }
                    }
                }
            }

            // Close driver and test object browser
            if(driver != null) {
//                driver.quit();
                driver.close();
            }
        }
    }
}
