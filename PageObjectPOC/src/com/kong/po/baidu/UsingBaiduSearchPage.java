package com.kong.po.baidu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/2/13
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UsingBaiduSearchPage {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://www.google.com");

        GoogleSearchPage page = PageFactory.initElements(driver, GoogleSearchPage.class);

        page.searchFor("Cheese");
    }
}
