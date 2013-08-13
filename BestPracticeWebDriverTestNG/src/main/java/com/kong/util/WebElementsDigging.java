package com.kong.util;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Change its data structure and way to pick data. Same like ArrayList, the only different is contents of data.
 * We should provide unified interface of accessing data to caller.
 * User: devin
 * Date: 7/24/13
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebElementsDigging {


    private WebDriver driver;
    private WebElementType type;

    private String expression;
    private String value;
    private static String regex = " ";
    private int size = 0;
    private int position = 0;
    private boolean matchTargetFlag = false;
    private boolean findTargetElementFlag = false;
    private List<WebElement> elements;
    private static Logger logger = LogUtil.getLogger(WebElementsDigging.class);

    public WebElementsDigging(WebDriver driver) {
        if (driver == null) {
            throw new NullPointerException();
        }
        this.driver = driver;
    }

    public WebElementsDigging(WebDriver driver, String expression) {
        this(driver, WebElementType.xpath, expression);
    }

    public WebElementsDigging(WebDriver driver, WebElementType webElementType, String expression) {
        this(driver, webElementType, expression, null);
    }

    /**
     * Constructor elements data for web element.
     *
     * @param driver         WebDriver
     * @param webElementType way of by. Like: id, xpath, cssSelector...
     * @param expression     Refer to action attribute in xml.
     * @param value          Real value. It can be keys.
     */
    public WebElementsDigging(WebDriver driver, WebElementType webElementType, String expression, String value) {
        this(driver);
        this.type = webElementType;
        this.expression = expression;
        this.value = value;

        // Get elements from two diff ways. One is directly API call, the other need separate expression by regex.
        elements = new ArrayList<WebElement>();
        SupportSplitPath ssp = new SupportSplitPath(this.expression);
        if (ssp.matchRegex()) {
            elements = multiElementsByPattern(ssp);
            matchTargetFlag = true;
        } else {
            elements = findElements();
        }

        // Set the size of collection
        if (elements != null) {
            size = elements.size();
        }
    }

    public boolean hasNext() {
        return position - size < 0;
    }

    public WebElement next() {
        if (matchTargetFlag) {
            position = findWebElementMatchTarget();
            if (position <= size) {
                return elements.get(position);
            }
        }
        return intelligentPickElement();
    }

    private WebElement intelligentPickElement() {
        if (elements == null) {
            throw new NoSuchElementException("No element found");
        }
        return intelligentPickElement(this.elements);
    }

    private WebElement intelligentPickElement(List<WebElement> elements) {
        if (elements == null) {
            throw new NoSuchElementException("elements not found");
        }

        if (this.elements.equals(elements)) {
            if (hasNext() && size == 1) {
                position++;
            }
        } else {
            if (elements.size() < 1) {
                throw new NoSuchElementException("elements size is 0");

            }
        }
        log4ElementAttributes(elements.get(0));
        return elements.get(0);
    }

    public String findDriverTitle() {
        return driver.getTitle();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getRegex() {
        return regex;
    }

    public static void setRegex(String regex) {
        WebElementsDigging.regex = regex;
    }

    /**
     * Find web element from elements which matches target value using getText()
     * Finally, return position(index) of elements.
     *
     * @return index of elements
     */
    private int findWebElementMatchTarget() {
        WebElement we = null;
        if (elements == null) {
            throw new NoSuchElementException("No element found");
        }

        we = elements.get(position);

        // TODO What if the value does not get from text, like attribute value
        if (we.getText().equals(value)) {
            findTargetElementFlag = true;
            logger.debug("Get the match by text. WEB ELEMENT @EXPRESSION:" + expression);
            log4ElementAttributes(we);
            return position;
        }

        // If did not match current web element, try go to next.
        if (hasNext()) {
            position++;
            findWebElementMatchTarget();
        }

        if (position <= size && findTargetElementFlag) {
            return position;
        }
        return position;
    }

    private void log4ElementAttributes(WebElement we) {
        elementAttributeEnum[] atts = elementAttributeEnum.values();
        logger.debug("Web element @text: " + we.getText());
        logger.debug("Web element @tag: " + we.getTagName());
        for (int i = 0; i < atts.length; i++) {
            logger.debug("Web element @" + atts[i].name() + ": " + we.getAttribute(atts[i].name()));
        }
    }

    private List<WebElement> findElements() {
        return findElements(this.expression);
    }

    private enum elementAttributeEnum {
        id, name, target, href;
    }

    /**
     * Find all elements match expression. If the expression has regex which follow the defination, it will call another method
     * to split expression by pattern.
     *
     * @param expression id, xpath, etc.
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
     *
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

    public enum WebElementType {
        xpath;
    }
}
