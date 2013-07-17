package com.kong.wd.handle;

import com.kong.wd.model.IBean;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public abstract class Handler {
	private WebDriver driver;
    public static final Logger logger = Logger.getLogger(Handler.class);

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

    public abstract WebDriver handle(IBean bean);

}
