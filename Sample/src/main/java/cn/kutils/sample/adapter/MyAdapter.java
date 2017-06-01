package cn.kutils.sample.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.kutils.sample.bean.MainTab;

/**
 * 创建时间：2017/6/1  下午4:57
 * 创建人：赵文贇
 * 类描述：
 * 包名：cn.kutils.sample.adapter
 * 待我代码编好，娶你为妻可好。
 */
public abstract class MyAdapter extends BaseQuickAdapter<MainTab,BaseViewHolder> {


    public MyAdapter(@LayoutRes int layoutResId, @Nullable List<MainTab> data) {
        super(layoutResId, data);
    }
}
