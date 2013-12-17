package com.kong.util.xmlDataParsing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/10/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class MethodAutoMatches {
    public static Object methodExec(WebDriver driver, Class<?> targetClass, String methodName, Object... params) {
            Object result = null;
            if(null != targetClass) {
                Method[] methods = targetClass.getMethods();
                for(Method m : methods) {
                    if(m.getName().contains(methodName)) {
                        try {
                            return m.invoke(PageFactory.initElements(driver, targetClass), params);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                }
            }
        return result;
    }

    public static Object methodExec(WebDriver driver, Object targetInstance, String methodName, Object... params) {
        Object result = null;
        if(null != targetInstance) {
            Method[] methods = targetInstance.getClass().getMethods();
            for(Method m : methods) {
                if(isRightMethod(m, methodName, params)) {
                    try {
                        return m.invoke(targetInstance, params);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }
        return result;
    }

    private static boolean isRightMethod(Method m, String methodName, Object[] params) {
        if(null == m || !m.getName().contains(methodName)) {
            return false;
        }

        if(m.getParameterTypes().length != params.length) {
            return false;
        }

        return true;
    }


}
