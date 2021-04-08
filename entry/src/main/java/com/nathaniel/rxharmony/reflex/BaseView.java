package com.nathaniel.rxharmony.reflex;

/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public interface BaseView {
    /**
     * --------------------------------------------
     * 接口开始情况  这时我们可以显示 菊花圈 或显示下载进度条
     * -------------------------------------------
     *
     * @param displayed show dialog or not
     */
    void showLoading(boolean displayed);

    /**
     * --------------------------------------------
     * 接口请求完毕  这时我们可以  将菊花圈隐藏掉 或下载进度条隐藏掉
     * -------------------------------------------
     */
    void hideLoading();

    /**
     * --------------------------------------------
     * 返回 非定义的code状态码，和msg  mType 区分异常时请求的接口是哪个
     * -------------------------------------------
     *
     * @param message error message
     */
    void onErrorState(String message);
}
