package com.kong.util.xmlDataParsing;

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
    public static Object methodExec(Class<?> targetClass, String methodName, Object... params) {
            Object result = null;
            if(null != targetClass) {
                Method[] methods = targetClass.getMethods();
                for(Method m : methods) {
                    if(m.getName().contains(methodName)) {
                        try {
                            result = m.invoke(targetClass.newInstance(), params);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InstantiationException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                }
            }
        return result;
    }
}
