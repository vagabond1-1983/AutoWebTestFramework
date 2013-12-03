package com.kong.baidu.pageObjects;

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
    private WebElement submit;

    @FindBy(how = How.ID, using = "s_username_top")
    private WebElement userNameSuccessShow;

    private String usernameFieldValue = null;

    @Override
    public void loginForm(String username, String password) {
        loginPopLink.click();

        if (null != username) {
            usernameFieldValue = username;
        }

        this.username.sendKeys(username);
        this.password.sendKeys(password);

        submit.submit();
    }

    @Override
    public boolean isLogin() {
        return userNameSuccessShow.getText().contains(revMail(usernameFieldValue));
    }

    private String revMail(String usernameFieldValue) {
        if (null != usernameFieldValue) {
            return usernameFieldValue.substring(0, usernameFieldValue.indexOf("@"));
        }
        return null;
    }

}
