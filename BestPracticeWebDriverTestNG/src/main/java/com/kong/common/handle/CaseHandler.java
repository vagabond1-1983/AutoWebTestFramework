package com.kong.common.handle;

import com.kong.common.controller.ContextConstant;
import com.kong.common.controller.ContextHelper;
import com.kong.common.controller.Suite;
import com.kong.common.model.TestCase;
import com.kong.util.XmlRulesDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/23/13
 * Time: 9:22 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class CaseHandler {
    private static CaseHandler caseHandler = new CaseHandler();
    private ContextHelper contextHelper = Suite.getInstance().getContextHelper();

    private CaseHandler() {
    }

    public ContextHelper getHelperContext(String paraFile, String RoleFile) {
        if (paraFile != null && RoleFile != null) {
            contextHelper.putContext(ContextConstant.PARAM_MAP_CONTEXT, ((TestCase) new XmlRulesDriver(paraFile, RoleFile).xml2Bean()).getParamMap());
        }
        return contextHelper;
    }

    public ContextHelper getHelperContext() {
        return getHelperContext(null, null);
    }

    public static CaseHandler getInstance() {
        return caseHandler;
    }

}
