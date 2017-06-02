package cn.kutils.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.kutils.atymanager.AppManager;
import cn.kutils.eventbus.EventBus;
import cn.kutils.eventbus.Subscriber;
import cn.kutils.eventbus.ThreadMode;
import cn.kutils.klog.KLog;
import cn.kutils.sample.adapter.MyAdapter;
import cn.kutils.sample.bean.MainTab;
import cn.kutils.sample.bean.User;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv_main)
    RecyclerView mRvMain;
    private MyAdapter mAdapter = new MyAdapter(R.layout.item_main_tab, null) {
        @Override
        protected void convert(BaseViewHolder helper, MainTab item) {
            helper.setText(R.id.tv_tab, item.getTabName());
            helper.addOnClickListener(R.id.tv_tab);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        KLog.d("EventBus在MainActivity中注册成功");
        initAdaater();
    }

    private void initAdaater() {
        mRvMain.setLayoutManager(new LinearLayoutManager(this));//设置rv布局走向
        mAdapter.isFirstOnly(false);//item的加载动画是否仅在第一次加载时生效
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置加载动画
        mRvMain.setAdapter(mAdapter);

        //添加数据
        List<MainTab> l = new ArrayList<>();
        l.add(new MainTab("测试EventBus事件分发", 0));
        l.add(new MainTab("瀑布流测试", 1));
        l.add(new MainTab("多媒体选择库使用", 2));
        l.add(new MainTab("新功能3", 3));
        l.add(new MainTab("新功能4", 4));

        mAdapter.setNewData(l);

        //处理item点击事件
        mRvMain.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //item中的单控件点击事件
                switch (mAdapter.getData().get(position).getId()) {
                    case 0:
                        //测试EventBus事件分发
                        startActivity(new Intent(MainActivity.this, TwoActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, BeautyPicturesActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, MediaUseAty.class));
                        break;
                    case 3:

                        break;
                }
            }
        });
        mRvMain.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item的点击事件

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);
        KLog.d("EventBus在MainActivity中注销成功");
    }

    @Subscriber(tag = "张三", mode = ThreadMode.MAIN)
    public void OnEventBus_ZhangSan(User user) {
        KLog.d("EventBus使用tag张三接收到User:" + user);
    }

    @Subscriber(tag = "李四", mode = ThreadMode.ASYNC)
    public void OnEventBus_Lisi(User user) {
        KLog.d("EventBus使用tag李四接收到User:" + user);
    }

    @Subscriber()
    public void OnEventBus(User user) {
        KLog.d("EventBus未使用tag收到User:" + user);
        OkGo.post("url").params("key","v").execute(new AbsCallback<User>() {
            @Override
            public void onSuccess(User user, Call call, Response response) {

            }

            @Override
            public User convertSuccess(Response response) throws Exception {
                return null;
            }
        });
    }

}
