package com.kong.wd.handle;

import com.kong.wd.model.WebElementType;
import com.kong.wd.util.LogUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/24/13
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebElementsDigging {
    private WebDriver driver;
    private WebElementType type;
    private String expression;
    private static String regex = " ";
    private static Logger logger = LogUtil.getLogger(WebElementsDigging.class);

    public WebElementsDigging(WebDriver driver) {
        if (driver == null) {
            throw new NullPointerException();
        }
        this.driver = driver;
    }

    public WebElementsDigging(WebDriver driver, WebElementType webElementType, String expression) {
        this(driver);
        this.type = webElementType;
        this.expression = expression;
    }

    public WebElement intelligentFindWebElement() {
        return intelligentPickElement(findElements());
    }

    private WebElement intelligentPickElement(List<WebElement> elementList) {
        if(elementList == null) {
            throw new NoSuchElementException("elements not found");
        }
        if (elementList.size() < 1) {
            logger.debug("No element found after pick.");
            return null;
        }

        if (elementList.size() == 1) {
            log4ElementAttributes(elementList.get(0));
            return elementList.get(0);
        }
        // TODO think solution about size > 1 picking which one and if we need the sub element instead
        return null;
    }

    public String findDriverTitle() {
        return driver.getTitle();
    }

    public WebElement findWebElementMatchTarget(String value) {
        List<WebElement> webElementList = multiElementsByPattern(new SupportSplitPath(expression));
        for (WebElement we : webElementList) {
            // TODO: package table, row, cell to easy get value. Continue tomorrow. It is good way to deal with selenium not support ui components.
            if (we.getTagName().equals("table")) {
                com.kong.wd.Components.Table t = new com.kong.wd.Components.Table(we);
                t.get(0).get(0).getAttrClass();
            }

            // TODO What if the value does not get from text, like attribute value
            if (we.getText().equals(value)) {
                logger.debug("Get the match by text. WEB ELEMENT @EXPRESSION:" + expression );
                log4ElementAttributes(we);
                return we;
            }
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private void log4ElementAttributes(WebElement we) {
        elementAttributeEnum[] atts= elementAttributeEnum.values();
        logger.debug("Web element @text: " + we.getText());
        logger.debug("Web element @tag: " + we.getTagName());
        for(int i = 0; i < atts.length; i++)   {
            logger.debug("Web element @" + atts[i].name() + ": " + we.getAttribute(atts[i].name()));
        }
    }

    private List<WebElement> findElements() {
        return findElements(this.expression);
    }

    private enum elementAttributeEnum {
        id,name, target, href;
    }

    /**
     * Find all elements match expression. If the expression has regex which follow the defination, it will call another method
     * to split expression by pattern.
     * @param expression  id, xpath, etc.
     * @return
     */
    private List<WebElement> findElements(String expression) {
        By by = null;
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        switch (type) {
            case xpath:
            default:
                logger.debug("Try to find element(s) match the @expression: " + expression);
                SupportSplitPath supportSplitPath = new SupportSplitPath(expression);
                if (supportSplitPath.matchRegex()) {
                    return multiElementsByPattern(supportSplitPath);
                }
                by = By.xpath(expression);
                break;
        }
        return driver.findElements(by);
    }

    /**
     * Split expression by regex, it only consider two parts of expression, not include more than 1 regex in expression.
     * @param supportSplitPath
     * @return
     */
    private List<WebElement> multiElementsByPattern(SupportSplitPath supportSplitPath) {
        List<WebElement> multiElements = null;
        switch (type) {
            case xpath:
            default:
                // Get first part of elements
                String firstPartPath = supportSplitPath.split()[0];
                List<WebElement> firstParts = findElements(firstPartPath);
                multiElements = new ArrayList<WebElement>();
                int count = 1;
                // Iterator first part
                for (WebElement we : firstParts) {
                    // Based on first part, find the rest of element
                    // Using [1] format to replace all regex, then find the whole path
                    // TODO What if it is not follow the xpath way?
                    String wholePart = supportSplitPath.buildWholePath("[" + String.valueOf(count) + "]");

                    // Find the first element after searching the whole part
                    WebElement fullElement = intelligentPickElement(findElements(wholePart));
                    if (fullElement == null) {
                        throw new NoSuchElementException("Cannot find right web element");
                    }

                    multiElements.add(fullElement);
                    count++;
                }
        }
        return multiElements;
    }

    public class SupportSplitPath {
        private String target;

        public SupportSplitPath(String target) {
            this.target = target;
        }

        public boolean matchRegex() {
            return target.contains(regex);
        }

        public String[] split() {
            String[] piecesOfXPath = target.split(regex);
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
