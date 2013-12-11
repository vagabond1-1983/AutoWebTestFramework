package com.kong.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/14/13
 * Time: 10:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class JQueryEntity implements IBean{
    public JQueryEntity(String jqueryScriptURI) {
        this.jqueryScriptURI = jqueryScriptURI;
    }

    public String getJqueryScriptURI() {
        return jqueryScriptURI;
    }

    private String jqueryScriptURI = null;

}
