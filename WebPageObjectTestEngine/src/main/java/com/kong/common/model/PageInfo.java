package com.kong.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/9/13
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageInfo {
    private String name;
    private String classpath;
    //Inside property for page info. Reference for page object
    private Class<?> pageObjectClass;

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;

        try {
            this.pageObjectClass = Class.forName(classpath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (null == pageObjectClass) {
                this.pageObjectClass = null;
            }
        }
    }

    public Class<?> getPageObjectClass() {
        return pageObjectClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (null != this.pageObjectClass) {
            return "PageInfo{" +
                    "class name: " + this.pageObjectClass.getClass() +
                    '}';
        } else {
            return "Name: " + name + " but not class found.";
        }
    }
}
