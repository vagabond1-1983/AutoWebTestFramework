package com.kong.wd.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/20/13
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableRow {
    private WebElement elmData;
    public TableRow(WebElement e) {
        if(e == null) {
            throw new NoSuchElementException("No element in table row");
        }
        elmData = e;
    }

    public TableCell get(int index) {
        List<TableCell> cells = gets();
        if(cells == null) {
            throw new NullPointerException();
        }

        if(cells.size() - index < 0 ) {
            throw new IndexOutOfBoundsException();
        }
        return cells.get(index);
    }

    public List<TableCell> gets() {
        List<TableCell> cells = new ArrayList<TableCell>();
        List<WebElement> elms = elmData.findElements(By.tagName("td"));
        if(elms ==null) {
            elms =    elmData.findElements(By.tagName("th"));
            if(elms == null) {
                throw new NoSuchElementException("No elements td or th in table");
            }
        }

        for(WebElement e : elms) {
            cells.add(new TableCell(e));
        }
        return cells;
    }
}
