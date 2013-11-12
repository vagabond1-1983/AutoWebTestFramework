package com.kong.util.webpage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JSExecution {
    public static Object executeJS(JavascriptExecutor driver, String javascript) {
        javascript = loadScript(javascript);
        return driver.executeScript(javascript);
    }

    private static String loadScript(String javascript) {
        String strScript = null;
        if (!javascript.endsWith(".js")) {
            return javascript;
        }

        InputStream inStream = JSExecution.class.getClassLoader().getResourceAsStream(javascript);
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();

        byte[] buff = new byte[100];
        int rc = 0;

        if (null == inStream) {
            throw new RuntimeException("Cannot find the " + javascript + " in classpath");
        }

        try {
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }

            strScript = new String(swapStream.toByteArray(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inStream.close();
                return strScript;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return null;
    }

    public static Object executeJS(JavascriptExecutor js, String script, Object[] args) {
        script = loadScript(script);
        return js.executeScript(script, args);
    }
}