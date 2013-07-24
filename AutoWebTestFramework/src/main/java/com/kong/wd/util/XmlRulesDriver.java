package com.kong.wd.util;

import java.io.File;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;

import com.kong.wd.model.Suite;
import org.apache.logging.log4j.Logger;


public class XmlRulesDriver {
    File input          ;
    final File rules;
    public static Logger logger = LogUtil.getLogger(XmlRulesDriver.class);

    /*public static void main(String[] args) {
		Suite s = xml2Bean("WebDriverFrame4j/UseCase/BaiduDWTest.xml", "WebDriverFrame4j/UseCase/baidu-rules.xml");
		s.toString();
	}*/

    public XmlRulesDriver(String inputFilePath, String rolesFilePath) {
        if(inputFilePath != null && !inputFilePath.isEmpty() && rolesFilePath != null && !rolesFilePath.isEmpty()) {
            input= new File(inputFilePath);
            rules = new File(rolesFilePath);
        }                   else {
            throw new NullPointerException();
        }

    }
	
	public Suite xml2Bean() {
		Suite suite = null;
		try {

			DigesterLoader loader = DigesterLoader.newLoader(new FromXmlRulesModule() {
				@Override
				protected void loadRules() {
                    if(rules.exists()) {
					    loadXMLRules(rules);
                    }
				}
			});
			Digester digester = loader.newDigester();
            if(input.exists()) {
			    suite = (Suite) digester.parse(input);
            }
			logger.debug(suite.toString());
			
		} catch (Exception exc) {
			
			exc.printStackTrace();
		}
		return suite;
	}
}