package com.nathaniel.rxharmony.main;

import com.nathaniel.rxharmony.entity.UserEntity;
import com.nathaniel.rxharmony.reflex.BaseView;

public interface MainView extends BaseView {
    void onLoginSuccess(UserEntity userEntity);

    void onRegisterSuccess(UserEntity userEntity);

    void onResetSuccess(UserEntity userEntity);

    void onLogoutSuccess(boolean successful);
}
