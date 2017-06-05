package cn.kutils.view.loadingdialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;

import cn.kutils.R;
import cn.kutils.view.loadingdialog.bean.TieBean;
import cn.kutils.view.loadingdialog.holder.SuperItemHolder;
import cn.kutils.view.loadingdialog.holder.TieItemHolder;

public class TieAdapter extends SuperAdapter<TieBean> {

    public TieAdapter(Context mContext, List<TieBean> list) {
        super(mContext, list);
    }

    @Override
    public SuperItemHolder getItemHolder(ViewGroup parent, int viewType) {
        return new TieItemHolder(mContext, mListener, LayoutInflater.from(mContext).
                inflate(R.layout.dialogui_holder_item_tie, parent, false));
    }
}
