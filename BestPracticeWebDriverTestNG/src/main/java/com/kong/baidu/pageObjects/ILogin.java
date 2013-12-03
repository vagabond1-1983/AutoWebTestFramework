package com.kong.baidu.pageObjects;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/3/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ILogin {
    void loginForm(String username, String password);

    boolean isLogin();
}
