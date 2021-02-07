package com.nathaniel.rxharmony.reflex;

import com.google.gson.JsonParseException;
import com.nathaniel.rxharmony.entity.BaseEntity;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

public abstract class BaseObserver<T> extends DisposableObserver<BaseEntity<T>> {
    public static final int PARSE_ERROR = 10008;
    public static final int BAD_NETWORK = 10007;
    public static final int CONNECT_ERROR = 10006;
    public static final int CONNECT_TIMEOUT = 10005;
    public static final int CONNECT_N = 10004;
    protected BaseView baseView;
    private boolean displayed;

    public BaseObserver(BaseView view) {
        this.baseView = view;
    }

    @Override
    protected void onStart() {
        displayed = true;
        if (baseView != null) {
            baseView.showLoading(displayed);
        }
    }

    @Override
    public void onNext(BaseEntity<T> baseEntity) {
        if (baseView != null) {
            baseView.hideLoading();
        }
        onSuccess(baseEntity);
    }

    @Override
    public void onError(Throwable throwable) {
        if (baseView != null) {
            baseView.hideLoading();
        }
        if (throwable instanceof HttpException) {
            onFailure(BAD_NETWORK, "网络超时");
        } else if (throwable instanceof ConnectException || throwable instanceof UnknownHostException) {
            onFailure(CONNECT_ERROR, "连接错误");
        } else if (throwable instanceof InterruptedIOException) {
            //  连接超时
            onFailure(CONNECT_TIMEOUT, "连接超时");
        } else if (throwable instanceof JsonParseException || throwable instanceof ParseException) {
            onFailure(PARSE_ERROR, "数据解析失败");
        } else if (throwable instanceof ApiException) {
            /*
             **************************************************************
             * 重点说一下此种情况：此类是接口返回内容不规范，开发中肯定会存在这样类似问题，虽不是前端问题，但前端也可以很好处理此类问题
             * 假如正常情况 返回data为集合
             * code:1
             * msg:获取成功
             * data[ 。。。]
             *
             * 当异常情况下，返回data:{}或者data:""
             * code:0
             * msg:获取失败
             * data:{}或者data:""
             *
             * 这样我们定义好的类型Gson解析会失败，由于类型不统一，并报异常，发生此类情况，在不改动后台代码情况下，
             * 一般通常我们会定义成object类型再手动解析，但这样很是麻烦，所以，可参考此种实现方式
             *
             * 实现原理：拦截gson解析，解析前一步，先解析一遍code,如果是定义正常的，继续向下解析，如果非正常情况，抛异常处理，
             * 并且将接口返回的code,msg一并抛出，异常会在这里拦截！！！！
             *************************************************************
             */
            ApiException apiException = (ApiException) throwable;
            onFailure(apiException.getErrorCode(), apiException.getMessage());
        } else {
            if (throwable != null) {
                onFailure(CONNECT_N, throwable.toString());
            } else {
                onFailure(CONNECT_N, "未知错误");
            }
        }
    }

    @Override
    public void onComplete() {
        displayed = false;
        if (baseView != null) {
            baseView.showLoading(displayed);
        }
    }

    /**
     * 请求成功
     *
     * @param baseEntity baseEntity
     */
    protected abstract void onSuccess(BaseEntity<T> baseEntity);

    /**
     * 请求失败
     *
     * @param code    code
     * @param message message
     */
    protected abstract void onFailure(int code, String message);
}
