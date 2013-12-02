package com.kong.po.baidu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/2/13
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoogleSearchPage {
    @FindBy(how = How.NAME, using = "q")
    private WebElement searchBox = null;

    public void searchFor(String text) {
        searchBox.sendKeys(text);
        searchBox.submit();
    }
}
