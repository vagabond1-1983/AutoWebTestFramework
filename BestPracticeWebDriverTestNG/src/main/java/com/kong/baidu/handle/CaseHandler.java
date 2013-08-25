package com.kong.baidu.handle;

import com.kong.baidu.controller.ContextConstant;
import com.kong.baidu.controller.Helper;
import com.kong.baidu.controller.Suite;
import com.kong.baidu.model.TestCase;
import com.kong.util.XmlRulesDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/23/13
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CaseHandler {
    private static CaseHandler caseHandler = new CaseHandler();
    private Helper helper = Suite.getInstance().getHelper();

    private CaseHandler() {
    }

    public Helper getHelperContext(String paraFile, String RoleFile) {
        if (paraFile != null && RoleFile != null) {
            helper.putContext(ContextConstant.PARAM_MAP_CONTEXT, ((TestCase) new XmlRulesDriver(paraFile, RoleFile).xml2Bean()).getParamMap());
        }
        return helper;
    }

    public Helper getHelperContext() {
        return getHelperContext(null, null);
    }

    public static CaseHandler getInstance() {
        return caseHandler;
    }

}
