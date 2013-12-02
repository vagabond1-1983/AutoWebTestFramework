AutoWebTestFramework
====================
Description:

Based on cases description in xml, we pick up them and take action in corresponding web application. Put web locators into one properties, let repeatable tasks to act following cases descripted by user.
User should define web locator, cases xml
Web locator and case id used the same key defined in project.

Core Objects：
Controller -- setup container like driver, page elements, parameter elements
Handler -- initial handler like driver, url, etc.. Case handler like get case details 
TestCase -- establish tasks in this case
TestTask -- Tasks build through page level, one task means user action

Container Structor
Container：webpage (Map)，parameter from user(Map)，driver

Tech：
webdriver，testng，log4j，apache digester
