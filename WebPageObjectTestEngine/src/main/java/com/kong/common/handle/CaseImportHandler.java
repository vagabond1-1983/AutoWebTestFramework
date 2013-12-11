package com.kong.common.handle;

import com.kong.common.controller.ContextConstant;
import com.kong.common.controller.ContextContainer;
import com.kong.common.controller.Suite;
import com.kong.common.model.TestCase;
import com.kong.util.xmlDataParsing.XmlRulesDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/23/13
 * Time: 9:22 PM
 * Get driver, page elements from Suite into ContextContainer and
 * put cases as sub map into ContextContainer too.
 */
public class CaseImportHandler {
    private static CaseImportHandler caseImportHandler = new CaseImportHandler();
    private ContextContainer contextContainer = Suite.getInstance().getContextContainer();

    private CaseImportHandler() {
    }

    /**
     * Put case xml into context container
     *
     * @param paraFile case xml file
     * @param RoleFile rule file restrict case xml
     * @return ContextContainer
     */
    public ContextContainer getContextContainer(String paraFile, String RoleFile) {
        if (paraFile != null && RoleFile != null) {
            contextContainer.putContext(ContextConstant.PARAM_MAP_CONTEXT, ((TestCase) new XmlRulesDriver(paraFile, RoleFile).xml2Bean()).getParamMap());
        }
        return contextContainer;
    }

    /**
     * Just get ContextContainer
     *
     * @return
     */
    public ContextContainer getContextContainer() {
        return getContextContainer(null, null);
    }

    public static CaseImportHandler getInstance() {
        return caseImportHandler;
    }

}
