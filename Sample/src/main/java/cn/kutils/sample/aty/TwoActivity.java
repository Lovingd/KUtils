package cn.kutils.sample.aty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.atymanager.AppManager;
import cn.kutils.eventbus.EventBus;
import cn.kutils.klog.KLog;
import cn.kutils.sample.R;
import cn.kutils.sample.bean.User;

/**
 * 创建时间：2017/6/1  下午3:57
 * 创建人：赵文贇
 * 类描述：
 * 包名：cn.kutils.sample
 * 待我代码编好，娶你为妻可好。
 */
public class TwoActivity extends AppCompatActivity {
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
        setContentView(R.layout.twoactivity);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        KLog.d("EventBus在TwoActivity中注册成功");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);
        KLog.d("EventBus在TwoActivity中注销成功");
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                EventBus.getDefault().post(new User("张三",26),"张三");
                KLog.d("EventBus发出tag=张三的事件");
                break;
            case R.id.bt2:
                EventBus.getDefault().post(new User("李四",28),"李四");
                KLog.d("EventBus发出tag=李四的事件");
                break;
            case R.id.bt3:
                EventBus.getDefault().post(new User("王五",38));
                KLog.d("EventBus未使用tag发出王五的事件");
                break;
            case R.id.bt4:
                break;
        }
    }
}
