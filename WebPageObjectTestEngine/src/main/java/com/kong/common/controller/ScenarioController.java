package com.kong.common.controller;

import com.kong.common.handle.CaseImportHandler;
import com.kong.common.model.CaseScenario;
import com.kong.common.model.StepInfo;
import com.kong.util.log.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: Dec 17 2013
 * Time: 10:22 PM
 */
public class ScenarioController {
    public static Logger logger = LogUtil.getLogger(ScenarioController.class);
    private WebDriver driver;
    private ContextContainer context;

    // Devin Dec 11 2013
    // As there is common way to load scenarios and steps and execute methods also, we should consider common handler to build scenario and stepExecute
    @Parameters({"scenarioFileName", "roleFileName"})
    @Test
    public void scenarioTransaction(String paraFile, String paraRoles) {
        context = CaseImportHandler.getInstance().getContextContainer(paraFile, paraRoles);
        driver = context.getCurrentDriver();

        CaseScenario scenario = (CaseScenario)context.getScenarioBean();

        if(null != scenario) {
            logger.debug("Start scenario: " + scenario.getName());
            for(StepInfo si : scenario.getStepInfos()) {
                si.stepExecute(driver, context);
            }
        }
    }
}
