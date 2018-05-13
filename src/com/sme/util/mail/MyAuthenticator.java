package com.sme.util.mail;

/**
 * Created by Administrator on 2016/6/3.
 * User : yaoping
 * Date:2016/6/3
 * Time:11:14
 * Version : 1.0
 */

        import javax.mail.*;

public class MyAuthenticator extends Authenticator {
    String userName=null;
    String password=null;

    public MyAuthenticator() {

    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
