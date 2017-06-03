package cn.kutils.sample;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
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
import cn.kutils.permissionchecker.PermissionListener;
import cn.kutils.permissionchecker.TedPermission;
import cn.kutils.sample.adapter.MyAdapter;
import cn.kutils.sample.aty.BeautyPicturesActivity;
import cn.kutils.sample.aty.MediaUseAty;
import cn.kutils.sample.aty.ProgressButtonAty;
import cn.kutils.sample.aty.TwoActivity;
import cn.kutils.sample.bean.MainTab;
import cn.kutils.sample.bean.User;
import cn.kutils.view.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv_main)
    RecyclerView mRvMain;

    @Bind(R.id.civ)
    CircleImageView mCiv;


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
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496372638951&di=be636f1362f06d68b902b8115cda13dc&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fxgs_150428%2Fdesk_005.jpg")
                .into(mCiv);
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
        l.add(new MainTab("android6.0权限检测", 3));
        l.add(new MainTab("带下载进度的button", 4));

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
                        checkPermission();
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ProgressButtonAty.class));
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

    private void checkPermission() {
        new TedPermission(getApplicationContext())
                .setPermissionListener(mPermissionListener)
                .setDeniedMessage("您有未授予的权限，可能导致部分功能闪退，请点击\"设置\"授权相关权限")
                .setPermissions(
//                        Manifest.permission.VIBRATE,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_WIFI_STATE,
//                        Manifest.permission.ACCESS_NETWORK_STATE,
//                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_LOGS,
//                        Manifest.permission.GET_TASKS,
//                        Manifest.permission.SET_DEBUG_APP,
//                        Manifest.permission.SYSTEM_ALERT_WINDOW,
//                        Manifest.permission.GET_ACCOUNTS,
//                        Manifest.permission.WRITE_SETTINGS,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.WAKE_LOCK,
//                        Manifest.permission.MODIFY_AUDIO_SETTINGS,
//                        Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                        Manifest.permission.CALL_PHONE)
                .check();
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
        OkGo.post("url").params("key", "v").execute(new AbsCallback<User>() {
            @Override
            public void onSuccess(User user, Call call, Response response) {

            }

            @Override
            public User convertSuccess(Response response) throws Exception {
                return null;
            }
        });
    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            KLog.d("全部权限已获取成功");
            //业务逻辑代码
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            //deniedPermissions 为用户未授予的权限
            KLog.d("未授予的权限:" + deniedPermissions);
            //业务逻辑
        }
    };

}
