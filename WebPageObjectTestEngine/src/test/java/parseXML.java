import com.kong.common.model.CaseScenario;
import com.kong.common.model.StepInfo;
import com.kong.common.model.TestCase;
import com.kong.util.xmlDataParsing.XmlRulesDriver;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 8/12/13
 * Time: 10:07 PM
 * To change this template use File | BrowserSettings | File Templates.
 */
public class parseXML {
    @Test
    public void parse() {
        String inputFile = "baidu_se_para_login.xml";
        String roleFile = "baidu-rules.xml";
        XmlRulesDriver dataDriver = new XmlRulesDriver(inputFile, roleFile);
        TestCase testCase = (TestCase)dataDriver.xml2Bean();
        testCase.getParamMap().size();
    }

    @Test
    public void parseSample() {
        String inputFile = "baidu_login_case.xml";
        String roleFile = "baidu_rules_cps.xml";
        XmlRulesDriver dataDriver = new XmlRulesDriver(inputFile, roleFile);
        CaseScenario onecase = (CaseScenario)dataDriver.xml2Bean();
        for(StepInfo si : onecase.getStepInfos()) {
            si.stepExecute(null, null);
        }
    }
}
