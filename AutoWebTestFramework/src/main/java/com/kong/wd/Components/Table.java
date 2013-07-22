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
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Table {
    private TableRow row;
    private TableCell cell;
    private WebElement elmData;

    public Table(WebElement elm) {
        if(elm == null) {
            throw new NoSuchElementException("No such element in table");
        }
        elmData = elm;
    }

    public TableRow get(int index) {
        List<TableRow> rows = gets();
        if(rows == null) {
            throw new NullPointerException();
        }

        if(rows.size() - index < 0 ) {
            throw new IndexOutOfBoundsException();
        }
        return rows.get(index);
    }

    public List<TableRow> gets() {
        List<TableRow> rows = new ArrayList<TableRow>();
        for(WebElement e : elmData.findElements(By.tagName("tr"))) {
            rows.add(new TableRow(e));
        }
        return rows;
    }


}
