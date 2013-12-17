package com.kong.util.webpage.JSExecutor;

import com.kong.util.log.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JSExecution {
    public static Logger logger = LogUtil.getLogger(JSExecution.class);

    public static Object executeJS(JavascriptExecutor driver, String javascript) {
        javascript = loadScript(javascript);
        return driver.executeScript(javascript);
    }

    private static String loadScript(String javascript) {
        // Devin Dec 16 2013
        // parameter is null
        if(null == javascript) {
            return null;
        }

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
            logger.debug("javascript like: " + strScript + "\nto be executed");
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
        if(null == args) {
            throw new RuntimeException("when call executescript for JavascriptExecutor, parameter should not be null.");
        }
        return js.executeScript(script, args);
    }
}