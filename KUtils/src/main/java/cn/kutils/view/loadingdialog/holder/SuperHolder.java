package cn.kutils.view.loadingdialog.holder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import cn.kutils.view.loadingdialog.bean.BuildBean;


public abstract class SuperHolder {
    public View rootView;

    public SuperHolder(Context context) {
        rootView = View.inflate(context, setLayoutRes(), null);
        findViews();
    }

    protected abstract void findViews();

    protected abstract
    @LayoutRes
    int setLayoutRes();

    /**
     * 一般情况下，实现这个方法就足够了
     *
     * @param context
     * @param bean
     */
    public abstract void assingDatasAndEvents(Context context, BuildBean bean);


}
