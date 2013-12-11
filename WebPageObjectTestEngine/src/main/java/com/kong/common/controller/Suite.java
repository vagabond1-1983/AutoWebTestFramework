package com.kong.common.controller;

import com.kong.common.handle.BrowserStartUpHandler;
import com.kong.common.model.BrowserSettings;
import com.kong.common.model.IBean;
import com.kong.common.model.JQueryEntity;
import com.kong.util.log.LogUtil;
import com.kong.util.propertyParsing.PropUtils;
import com.kong.util.webpage.JSExecutor.JQuery;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/9/13
 * Time: 10:24 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class Suite {
    private static WebDriver driver;
    private static JQuery jQuery;
    private static Properties pagesMap;
    // No need these two parameters.
    // Devin 2013-11-05
//    private static String server = "127.0.0.1";
//    private static Integer port = 4444;
    private ContextContainer contextContainer = new ContextContainer();
    public static Logger logger = LogUtil.getLogger(Suite.class);

    private static Suite suite = new Suite();

    private Suite() {
    }

    public static Suite getInstance() {
        return suite;
    }

    /**
     * Provide the driver which already created
     *
     * @return
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Set up driver, pages properties and initial settings for browser and server
     *
     * @param setupParamProp
     * @param baiduPagesProp
     */
    @Parameters({"setupParamProp", "baiduPagesProp"})
    @BeforeSuite(alwaysRun = true)
    public void setupBeforeSuite(String setupParamProp, String baiduPagesProp) {
        // Get the settings including server, port, url, browser
        List<IBean> settingsList = SetupProxy.getSettings(setupParamProp);

        for(IBean bean : settingsList) {
            if (bean instanceof BrowserSettings) {
                // Start up browser and put default url to browse
                driver = new BrowserStartUpHandler().handle(bean);
            }
            else if(bean instanceof JQueryEntity) {
                if(driver != null) {
                    try {
                        jQuery = JQuery.getInstance((JavascriptExecutor) driver);
                        jQuery.setJqueryScript(((JQueryEntity) bean).getJqueryScriptURI());
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }

        setPagesMap(baiduPagesProp);
    }

    private void setPagesMap(String baiduPagesProp) {
        pagesMap = PropUtils.getProperties(baiduPagesProp);
    }

    public ContextContainer getContextContainer() {
        contextContainer.putContext(ContextConstant.DRIVER_CONTEXT, driver);
        contextContainer.putContext(ContextConstant.PAGES_MAP_CONTEXT, pagesMap);
        contextContainer.putContext(ContextConstant.JQUERY_CONTEXT, jQuery);
        return contextContainer;
    }

    public static Properties getPagesMap() {
        return pagesMap;
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000 * 10);
        if (driver != null)
            driver.quit();
    }
}
