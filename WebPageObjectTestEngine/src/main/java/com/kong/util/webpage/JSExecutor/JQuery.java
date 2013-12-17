package com.kong.util.webpage.JSExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;

/**
 * Load jquery into test target. Execute jquery script first in web object
 */
public class JQuery {

    private static JQuery jquery = null;

    public void setJqueryScript(String jqueryScript) {
        this.jqueryScript = jqueryScript;
    }

    private String jqueryScript = null;
    private static JavascriptExecutor driver;

    private JQuery() {

    }

    public static JQuery getInstance(JavascriptExecutor driver) throws IOException {
        if (jquery == null) {
            jquery = new JQuery();
            JQuery.driver = driver;
        }
        return jquery;
    }

    private void initJQuery() {
        if (!jQueryLoaded()) {
            if(null == jqueryScript) {
                throw new NullPointerException("jquery script not defined. Please define it in setupParam.properties");
            }

            JSExecution.executeJS(driver, jqueryScript);
            JSExecution.executeJS(driver, "window.jQuery=jQuery.noConflict();");
        }
    }

    /**
     * 判断当前页面是否加载了JQuery
     *
     * @return
     */

    public Boolean jQueryLoaded() {
        Boolean loaded;
        String jqueryLoadVerifyScript = "js/JQueryLoadVerify.js";
        try {
            loaded = (Boolean) JSExecution.executeJS(driver, jqueryLoadVerifyScript);
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    /**
     * 执行JS脚本
     *
     * @param script
     * @param args
     * @return
     */

    public Object runJs(String script, Object... args) {
        initJQuery();
        return JSExecution.executeJS(driver, script, args);
    }

    // Devin Dec 16 2013
    // There is only script without parameters following with script to execute
    public Object runJs(String script) {
        initJQuery();
        return JSExecution.executeJS(driver, script);
    }
}