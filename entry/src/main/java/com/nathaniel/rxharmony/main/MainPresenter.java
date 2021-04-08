package com.nathaniel.rxharmony.main;

import com.nathaniel.rxharmony.entity.BaseEntity;
import com.nathaniel.rxharmony.entity.UserEntity;
import com.nathaniel.rxharmony.reflex.ApiRetrofit;
import com.nathaniel.rxharmony.reflex.ApiService;
import com.nathaniel.rxharmony.reflex.BaseObserver;
import com.nathaniel.rxharmony.reflex.BasePresenter;

import java.util.HashMap;
import java.util.Map;
/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public class MainPresenter extends BasePresenter<MainView> implements UserContract {
    public MainPresenter(MainView baseView) {
        super(baseView);
    }


    private ApiService getApiService() {
        return ApiRetrofit.getInstance().getApiService();
    }

    @Override
    public void requireLogin(String username, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        addDisposable(getApiService().login(params), new BaseObserver<UserEntity>(baseView) {
            @Override
            public void onSuccess(BaseEntity<UserEntity> baseEntity) {
                getBaseView().onLoginSuccess(baseEntity.getData());
            }

            @Override
            protected void onFailure(int code, String message) {
                getBaseView().onErrorState(message);
            }
        });
    }

    @Override
    public void requireRegister(String username, String password, String realname) {

    }

    @Override
    public void resetPassword(String username, String oldPassword, String newPassword) {

    }
}