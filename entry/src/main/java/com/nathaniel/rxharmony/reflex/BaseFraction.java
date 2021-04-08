package com.nathaniel.rxharmony.reflex;

import com.nathaniel.rxharmony.entity.BaseEntity;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.app.Context;

/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public abstract class BaseFraction<P extends BasePresenter> extends Fraction implements BaseView {
    protected P mPresenter;
    protected Component mComponentView;

    public abstract int getUIContent();

    protected abstract P createPresenter();

    public abstract void initComponent();

    public abstract void initData();

    public Context mContext;

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        mComponentView = scatter.parse(getUIContent(), container, false);
        mContext = getFractionAbility();
        mPresenter = createPresenter();
        initComponent();
        initData();
        return mComponentView;
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public String getString(int resId) {
        try {
            return getFractionAbility().getResourceManager().getElement(resId).getString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getColor(int colorId) {
        try {
            return getFractionAbility().getResourceManager().getElement(colorId).getColor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void showLoading(boolean displayed) {

    }

    @Override
    public void hideLoading() {

    }

    public void onErrorState(BaseEntity model, int mType) {
        if (!BaseConstants.getIsTrueCode(model.getCode())) {
//            Toast.show(mContext, model.getMsg());
        }
    }

    public void onProgress(int progress) {

    }
}
