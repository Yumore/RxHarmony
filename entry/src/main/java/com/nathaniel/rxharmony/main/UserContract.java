package com.nathaniel.rxharmony.main;

public interface UserContract {
    void requireLogin(String username, String password);

    void requireRegister(String username, String password, String realname);

    void resetPassword(String username, String oldPassword, String newPassword);
}
