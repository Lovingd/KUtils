package cn.kutils.view.loadingdialog.holder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.kutils.R;
import cn.kutils.view.loadingdialog.bean.TieBean;
import cn.kutils.view.loadingdialog.listener.OnItemClickListener;


public class TieItemHolder extends SuperItemHolder<TieBean> {


    LinearLayout llBg;
    TextView tvTitle;

    public TieItemHolder(Context mContext, OnItemClickListener listener, View itemView) {
        super(mContext, listener, itemView);
        llBg = (LinearLayout) itemView.findViewById(R.id.ll_bg);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    }

    @Override
    public void refreshView() {
        TieBean data = getData();
        llBg.setSelected(data.isSelect());
        tvTitle.setText("" + data.getTitle());
    }
}
