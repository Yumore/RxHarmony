package com.nathaniel.rxharmony.slice;

import com.nathaniel.rxharmony.reflex.BasePresenter;
import com.nathaniel.rxharmony.reflex.BaseView;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.ability.fraction.FractionManager;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ProgressBar;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public abstract class BaseAbilitySlice<P extends BasePresenter> extends AbilitySlice implements BaseView {
    private Context context;
    private P presenter;
    private ProgressBar progressBar;

    @Override
    public void onStart(Intent intent) {
        context = this;
        presenter = createPresenter();
        beforeUIContent();
        super.onStart(intent);
        super.setUIContent(getLayoutRes());
    }

    /**
     * create presenter
     *
     * @return P
     */
    protected abstract P createPresenter();

    protected abstract int getLayoutRes();

    /**
     * initialize something before ui
     */
    protected void beforeUIContent() {

    }

    public FractionManager getFractionManager() {
        Ability ability = getAbility();
        if (ability instanceof FractionAbility) {
            FractionAbility fractionAbility = (FractionAbility) ability;
            return fractionAbility.getFractionManager();
        }
        return null;
    }

    @Override
    public void showLoading(boolean displayed) {
        progressBar = new ProgressBar(getContext());
        progressBar.setProgressHintText("正在加载");
        progressBar.setProgressColor(Color.BLUE);
    }

    @Override
    public void hideLoading() {
        if (null != progressBar && progressBar.isComponentDisplayed()) {
            progressBar.setContentEnable(false);
            progressBar = null;
        }
    }

    @Override
    public void onErrorState(String message) {
        new ToastDialog(getContext())
                .setText(message)
                .setAlignment(LayoutAlignment.CENTER)
                .show();
    }
}
