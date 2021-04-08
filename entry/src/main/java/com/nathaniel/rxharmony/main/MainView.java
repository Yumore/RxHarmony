package com.nathaniel.rxharmony.main;

import com.nathaniel.rxharmony.entity.UserEntity;
import com.nathaniel.rxharmony.reflex.BaseView;
/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public interface MainView extends BaseView {
    void onLoginSuccess(UserEntity userEntity);

    void onRegisterSuccess(UserEntity userEntity);

    void onResetSuccess(UserEntity userEntity);

    void onLogoutSuccess(boolean successful);
}
