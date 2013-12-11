package com.kong.common.handle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/5/13
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class IEDriver implements IDriver {
    public static final String DRIVER_IEDRIVER_SERVER_WIN32_EXE = "driver/IEDriverServer-win32.exe";
    public static final String DRIVER_IEDRIVER_SERVER_X64_EXE = "driver/IEDriverServer-x64.exe";
    public static final String WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";

    @Override
    public WebDriver setupDriver() {
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
