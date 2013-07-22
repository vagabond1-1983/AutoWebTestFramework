package com.kong.wd.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.kong.wd.model.WebElementType;

// TODO Reflactor this util, too many things included in it
public class WebObjectUtil {
    private static String regex = " ";

    public static WebElement intelligentFindWebElement(WebDriver driver, WebElementType webElementType, String xpath) {
        return intelligentPickElement(findElements(driver, webElementType, xpath));
    }

    private static List<WebElement> findElements(WebDriver driver, WebElementType webElementType, String xpath) {
        By by = null;
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        switch (webElementType) {
            case xpath:
            default:
                SupportSplitPath supportSplitPath = new SupportSplitPath(xpath);
                if (supportSplitPath.matchRegex()) {
                    return multiElementsByPattern(driver, webElementType, supportSplitPath);
                }
                by = By.xpath(xpath);
                break;
        }
        return driver.findElements(by);
    }

    // Now it just support length of xpath is 2. if it is more than 2, ignore them.
    private static List<WebElement> multiElementsByPattern(WebDriver driver, WebElementType webElementType, SupportSplitPath supportSplitPath) {

        List<WebElement> multiElements = null;
        if (driver != null) {

            switch (webElementType) {
                case xpath:
                default:
                    // Get first part of elements
                    String firstPartPath = supportSplitPath.split()[0];
                    List<WebElement> firstParts = driver.findElements(By.xpath(firstPartPath));
                    multiElements = new ArrayList<WebElement>();
                    int count = 1;
                    // Iterator first part
                    for (WebElement we : firstParts) {
                        // Based on first part, find the rest of element
                        // Using [1] format to replace all regex, then find the whole path
                        // TODO What if it is not follow the xpath way?
                        String wholePart = supportSplitPath.buildWholePath("[" + String.valueOf(count) + "]");
                        WebElement fullElement = we.findElement(By.xpath(wholePart));
                        if (fullElement == null) {
                            throw new NullPointerException("Cannot find right web element");
                        }

                        multiElements.add(fullElement);
                        count++;
                    }
            }
        }
        return multiElements;
    }

    private static WebElement intelligentPickElement(List<WebElement> elementList) {
        if (elementList.size() < 1) {
            return null;
        }

        if (elementList.size() == 1) {
            return elementList.get(0);
        }
        // TODO think solution about size > 1 picking which one and if we need the sub element instead
        return null;
    }

    public static String findDriverTitle(WebDriver driver) {
        if (driver == null) {
            return "";
        }
        return driver.getTitle();
    }

    public static WebElement findWebElementMatchTarget(WebDriver driver, WebElementType webElementType, String action, String value) {
        List<WebElement> webElementList = multiElementsByPattern(driver, webElementType, new SupportSplitPath(action));
        for (WebElement we : webElementList) {
            // TODO: package table, row, cell to easy get value. Continue tomorrow. It is good way to deal with selenium not support ui components.
            if (we.getTagName().equals("table")) {
                com.kong.wd.Components.Table t = new com.kong.wd.Components.Table(we);
                t.get(0).get(0).getAttrClass();
            }

            // TODO What if the value does not get from text, like attribute value
            if (we.getText().equals(value)) {
                return we;
            }
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static String getRegex() {
        return regex;
    }

    public static void setRegex(String regex) {
        WebObjectUtil.regex = regex;
    }

    public static class SupportSplitPath {
        private String target;

        public SupportSplitPath(String target) {
            this.target = target;
        }

        public boolean matchRegex() {
            return target.contains(WebObjectUtil.regex);
        }

        public String[] split() {
            String[] piecesOfXPath = target.split(WebObjectUtil.regex);
            if (piecesOfXPath == null || piecesOfXPath.length <= 1) {
                throw new NullPointerException("xpath split by pattern failed");
            } else if (piecesOfXPath.length > 2) {
                throw new ArrayIndexOutOfBoundsException("the pattern in action attribute does not match the defination");
            }
            return piecesOfXPath;
        }

        public String buildWholePath(String replacement) {
            return target.replaceAll(regex, replacement);
        }


    }
}
