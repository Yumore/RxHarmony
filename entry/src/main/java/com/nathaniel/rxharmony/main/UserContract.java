package com.nathaniel.rxharmony.main;
/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public interface UserContract {
    void requireLogin(String username, String password);

    void requireRegister(String username, String password, String realname);

    void resetPassword(String username, String oldPassword, String newPassword);
}
