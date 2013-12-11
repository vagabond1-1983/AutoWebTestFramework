package com.kong.bpwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/9/13
 * Time: 9:18 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class test {
    private static Logger logger = LogManager.getLogger(test.class);
    private WebDriver driver;
    @Parameters({"loginMainPage"})
    @BeforeSuite
    public void before(String test) {
        System.out.println("before suite " + test);
    }

    @Parameters({"loginMainPage"})
    @BeforeTest
    public void beforeMethod(String test) {
        System.out.println("before loginMainPage " + test);
    }

    @Parameters({"loginMainPage"})
    @Test
    public void test(String test) {
        // log4j2.xml should be placed at src/main/resources or src/loginMainPage/resources.
        // then it will compile to target/loginMainPage-classes folder. Then log4j can find that file.
        logger.debug(test);

        driver = new FirefoxDriver();
        driver.get(test);

    }
}
