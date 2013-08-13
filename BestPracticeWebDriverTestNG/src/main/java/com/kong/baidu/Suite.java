package com.kong.baidu;

import com.kong.baidu.handle.Handler;
import com.kong.baidu.handle.InitEnvHandler;
import com.kong.baidu.model.IBean;
import com.kong.baidu.model.Settings;
import com.kong.util.LogUtil;
import com.kong.util.PropUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/9/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Suite {
    private static WebDriver driver;

    private static Properties pagesMap;
    private static String server = "127.0.0.1";
    private static Integer port = 4444;
    public static Logger logger = LogUtil.getLogger(Suite.class);

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
        IBean settings = getSettings(setupParamProp);
        Handler handler = new InitEnvHandler();
        driver = handler.handle(settings);

        setPagesMap(baiduPagesProp);
    }

    private void setPagesMap(String baiduPagesProp) {
        pagesMap = PropUtils.getProperties(baiduPagesProp);
    }

    public static Properties getPagesMap() {
        return pagesMap;
    }

    // Server settings
    private IBean getSettings(String setupParamProp) {
        Properties properties = PropUtils.getProperties(setupParamProp);
        String browser = properties.getProperty("browser");
        String server = properties.getProperty("server");
        String port = properties.getProperty("port");
        String url = properties.getProperty("url");
        IBean settings = new Settings(browser, server, Integer.valueOf(port), url);
        if (server != null) this.server = server;
        if (port.matches("\\d+")) this.port = Integer.parseInt(port);
        logger.debug(settings.toString());
        return settings;
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000 * 10);
        if (driver != null)
            driver.quit();
    }


}
