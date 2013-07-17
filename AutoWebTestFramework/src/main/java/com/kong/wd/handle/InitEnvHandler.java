package com.kong.wd.handle;

import com.kong.wd.model.IBean;
import com.kong.wd.model.Settings;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class InitEnvHandler extends Handler {
	WebDriver driver = null;
	private static final Logger logger = Logger.getLogger(InitEnvHandler.class);

    @Override
	public WebDriver handle(IBean settings) {

		driver = getDriver(((Settings)settings).getBrowser());
		if(driver == null) {
			return null;
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.debug("Selenium server started!");
		driver.get(((Settings)settings).getUrl());
		return driver;
	}
	
	private WebDriver getDriver(String browser) {
		try {
			if (browser.contains("ie")) {

				// Set webdriver.ie.driver and copy IEDriverServer.exe to any
				// location.
				// Else you will get java.lang.IllegalStateException: The path
				// to the driver executable must be set by the
				// webdriver.ie.driver system property
				String systemBit = System.getProperty("os.arch");
				if (systemBit.contains("64")) {
					System.setProperty("webdriver.ie.driver",
							"driver/IEDriverServer-x64.exe");
				} else {
					System.setProperty("webdriver.ie.driver",
							"driver/IEDriverServer-win32.exe");
				}
				DesiredCapabilities ieCapabilities = DesiredCapabilities
						.internetExplorer();
				ieCapabilities
						.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
				driver = new InternetExplorerDriver(ieCapabilities);
				// new Integer(seleniumPort).intValue());
			} else if (browser.contains("firefox")) {
				/*System.setProperty("webdriver.firefox.profile",
						"driver/about config.xul");
				FirefoxProfile profile = new FirefoxProfile();*/
				driver = new FirefoxDriver();
			} else if (browser.contains("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"driver/chromedriver.exe");
				driver = new ChromeDriver();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return driver;
	}
}
