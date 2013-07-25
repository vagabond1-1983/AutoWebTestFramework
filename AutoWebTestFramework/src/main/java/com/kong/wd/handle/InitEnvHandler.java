package com.kong.wd.handle;

import com.kong.wd.model.IBean;
import com.kong.wd.model.Settings;
import com.kong.wd.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class InitEnvHandler extends Handler {
    public static final String DRIVER_IEDRIVER_SERVER_WIN32_EXE = "driver/IEDriverServer-win32.exe";
    public static final String WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";
    public static final String DRIVER_CHROMEDRIVER_EXE = "driver/chromedriver.exe";
    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    WebDriver driver = null;
    private static final Logger logger = LogUtil.getLogger(InitEnvHandler.class);
    public static final String DRIVER_IEDRIVER_SERVER_X64_EXE = "driver/IEDriverServer-x64.exe";

    @Override
    public WebDriver handle(IBean settings) {

        driver = getDriver(((Settings) settings).getBrowser());
        if (driver == null) {
            return null;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.debug("Selenium server started!");
        driver.get(((Settings) settings).getUrl());
        return driver;
    }

    private WebDriver getDriver(String browser) {
        if (browser.contains("ie")) {

            // Set webdriver.ie.driver and copy IEDriverServer.exe to any
            // location.
            // Else you will get java.lang.IllegalStateException: The path
            // to the driver executable must be set by the
            // webdriver.ie.driver system property
            driver = getIEDriver();
            // new Integer(seleniumPort).intValue());
        } else if (browser.contains("firefox")) {
                /*System.setProperty("webdriver.firefox.profile",
                        "driver/about config.xul");
				FirefoxProfile profile = new FirefoxProfile();*/
            driver = new FirefoxDriver();
        } else if (browser.contains("chrome")) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER,
                    DRIVER_CHROMEDRIVER_EXE);
            driver = new ChromeDriver();
        }
        logger.debug("Web driver under " + browser + " browser up.");
        if (driver == null) {
            throw new NullPointerException();
        }
        return driver;
    }

    private WebDriver getIEDriver() {
        String systemBit = System.getProperty("os.arch");
        if (systemBit.contains("64")) {
            System.setProperty(WEBDRIVER_IE_DRIVER,
                    DRIVER_IEDRIVER_SERVER_X64_EXE);
        } else {
            System.setProperty(WEBDRIVER_IE_DRIVER,
                    DRIVER_IEDRIVER_SERVER_WIN32_EXE);
        }
        DesiredCapabilities ieCapabilities = DesiredCapabilities
                .internetExplorer();
        ieCapabilities
                .setCapability(
                        InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                        true);
        return new InternetExplorerDriver(ieCapabilities);
    }
}
