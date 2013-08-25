package com.kong.baidu.tasks;

import com.kong.baidu.model.ActionType;
import com.kong.util.SimulateAction;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/11/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class searchTask extends commonTask {
    public searchTask(WebDriver driver, Properties pageElementsMap) {
        super(driver, pageElementsMap);
    }
}
