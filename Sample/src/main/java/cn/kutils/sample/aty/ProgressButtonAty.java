package cn.kutils.sample.aty;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.sample.R;
import cn.kutils.view.progressbutton.AnimDownloadProgressButton;

/**
 * 创建时间：2017/6/3  下午11:19
 * 创建人：赵文贇
 * 类描述：
 * 包名：cn.kutils.sample.aty
 * 待我代码编好，娶你为妻可好。
 */
public class ProgressButtonAty extends AppCompatActivity {
    @Bind(R.id.anim_btn)
    AnimDownloadProgressButton mAnimBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbuttonaty);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mAnimBtn.setCurrentText("安装");
        mAnimBtn.setTextSize(25f);
    }

    @OnClick({R.id.anim_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anim_btn:
                showTheButton();
                break;
        }
    }


    private void showTheButton() {
        mAnimBtn.setState(AnimDownloadProgressButton.DOWNLOADING);
        mAnimBtn.setProgressText("下载中", mAnimBtn.getProgress() + 8);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    final int finalI = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAnimBtn.setProgressText("下载中", finalI);
                            if (mAnimBtn.getProgress() == 100) {
                                mAnimBtn.setState(AnimDownloadProgressButton.INSTALLING);
                                mAnimBtn.setCurrentText("安装中");
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        mAnimBtn.setState(AnimDownloadProgressButton.NORMAL);
                                        mAnimBtn.setCurrentText("打开");
                                    }
                                }, 2000);   //2秒
                                return;
                            }
                        }
                    });
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
