package cn.kutils.sample.aty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.sample.R;
import cn.kutils.view.loadingdialog.DialogUIUtils;
import cn.kutils.view.loadingdialog.bean.TieBean;
import cn.kutils.view.loadingdialog.listener.DialogUIItemListener;
import cn.kutils.view.loadingdialog.listener.DialogUIListener;


/**
 * 创建时间：2017/6/5  下午7:13
 * 创建人：赵文贇
 * 类描述：自定义对话框
 * 包名：cn.kutils.sample.aty
 * 待我代码编好，娶你为妻可好。
 */
public class DialogSample extends AppCompatActivity {
    @Bind(R.id.bt1)
    Button mBt1;
    @Bind(R.id.bt2)
    Button mBt2;
    @Bind(R.id.bt3)
    Button mBt3;
    @Bind(R.id.bt4)
    Button mBt4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogsample);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
//                DialogUIUtils.showToast("这是一个Toast");
                DialogUIUtils.showLoadingHorizontal(this, "loading...").show();
                break;
            case R.id.bt2:
                DialogUIUtils.showAlert(this, "提示", "请输入用户名密码", "用户名", "密码", "确定", "取消", false, false, false, new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }

                    @Override
                    public void onGetInput(CharSequence input1, CharSequence input2) {

                    }
                }).show();

                break;
            case R.id.bt3:
                DialogUIUtils.showMdAlert(this, "提示", "账号已被锁定", new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }
                }).show();
                break;
            case R.id.bt4:
//                DialogUIUtils.showToastCenter("你好");
                ArrayList<TieBean> tieBeen =new ArrayList<>();
                tieBeen.add(new TieBean("0",0));
                tieBeen.add(new TieBean("1",1));
                tieBeen.add(new TieBean("2",2));
                tieBeen.add(new TieBean("3",3));
                tieBeen.add(new TieBean("4",4));
                DialogUIUtils.showMdBottomSheet(this, false, "提示", tieBeen, "确定", 1, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {

                    }
                }).show();
                break;
        }
    }

}
