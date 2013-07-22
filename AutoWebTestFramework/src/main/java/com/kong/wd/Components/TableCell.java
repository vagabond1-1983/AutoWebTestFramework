package com.kong.wd.Components;

import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/20/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableCell {
    private WebElement elmData;
    public TableCell(WebElement elm) {
        if(elm == null) {
            throw new NoSuchElementException("No element under table cell");
        }
        elmData = elm;
    }

    public String getAttrClass() {
        return elmData.getAttribute("class").toString();
    }
}
