package com.nathaniel.rxharmony.slice;

import com.google.gson.Gson;
import com.nathaniel.rxharmony.ResourceTable;
import com.nathaniel.rxharmony.entity.UserEntity;
import com.nathaniel.rxharmony.main.MainPresenter;
import com.nathaniel.rxharmony.main.MainView;
import ohos.agp.window.dialog.ToastDialog;

/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public class MainAbilitySlice extends BaseAbilitySlice<MainPresenter> implements MainView {

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return ResourceTable.Layout_ability_main;
    }

    @Override
    public void onLoginSuccess(UserEntity userEntity) {
        new ToastDialog(getContext())
                .setText(new Gson().toJson(userEntity))
                .show();
    }

    @Override
    public void onRegisterSuccess(UserEntity userEntity) {

    }

    @Override
    public void onResetSuccess(UserEntity userEntity) {

    }

    @Override
    public void onLogoutSuccess(boolean successful) {

    }
}
