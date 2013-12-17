package com.kong.baidu.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/2/13
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPage implements ILogin {
    @FindBy(how = How.ID, using = "lb")
    private WebElement loginPopLink;

    @FindBy(how = How.ID, using = "TANGRAM__PSP_8__userName")
    private WebElement username;

    @FindBy(how = How.ID, using = "TANGRAM__PSP_8__password")
    private WebElement password;

    @FindBy(how = How.ID, using = "TANGRAM__PSP_8__submit")
    private WebElement submitButton;

    @FindBy(how = How.ID, using = "s_username_top")
    private WebElement userNameSuccessShow;

    @FindBy(how = How.ID, using = "passport-login-pop")
    private WebElement passwordLoginPopup;

    private String usernameFieldValue = null;

    @Override
    public void loginForm(String username, String password) {
            try {
                loginPopLink.click();
                //TODO Devin
                // Sometimes login pop not start even if refresh page....
                // Try to find way to invoke this like run js
                while(!passwordLoginPopup.isDisplayed()) {
                    Thread.sleep(5000);
                    loginPopLink.click();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch(NoSuchElementException e) {
                loginForm(username, password);
            }


        if (null != username) {
            usernameFieldValue = username;
        }

        this.username.sendKeys(username);
        this.password.sendKeys(password);

        submitButton.submit();
    }

    @Override
    public boolean isLogin(String expectValue) {
        //Debug
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return userNameSuccessShow.getText().contains(expectValue);
    }
}
