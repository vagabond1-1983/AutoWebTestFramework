package com.kong.util.webpage;

import com.kong.common.model.IBean;
import com.kong.util.LogUtil;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class XmlRulesDriver {
    File input;
    final File rules;
    public static Logger logger = LogUtil.getLogger(XmlRulesDriver.class);

    /*public static void main(String[] args) {
        Suite s = xml2Bean("WebDriverFrame4j/UseCase/BaiduDWTest.xml", "WebDriverFrame4j/UseCase/baidu-rules.xml");
		s.toString();
	}*/

    public XmlRulesDriver(String inputFilePath, String rolesFilePath) {
        if (inputFilePath != null && !inputFilePath.isEmpty() && rolesFilePath != null && !rolesFilePath.isEmpty()) {
            input = new File(getFilePath(inputFilePath));
            rules = new File(getFilePath(rolesFilePath));
        } else {
            throw new NullPointerException();
        }

    }

    private String getFilePath(String fileName) {
        fileName = getClass().getClassLoader().getResource(fileName).getFile();

        if (fileName == null) {
            fileName = getClass().getClassLoader().getResource("/").getFile() + fileName;
            //remove .getParent()
        }
        return fileName;
    }

    public IBean xml2Bean() {
        IBean bean = null;
        try {

            DigesterLoader loader = DigesterLoader.newLoader(new FromXmlRulesModule() {
                @Override
                protected void loadRules() {
                    if (rules.exists()) {
                        loadXMLRules(rules);
                    }
                }
            });
            Digester digester = loader.newDigester();
            if (input.exists()) {
                bean = (IBean) digester.parse(input);
                logger.debug(bean.toString());
            }

        } catch (Exception exc) {

            exc.printStackTrace();
        }
        return bean;
    }
}