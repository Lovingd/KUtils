KUtils(代码来源第三方,只做代码搬运工) 即将更新:图片,视频选择库,鲁班图片压缩.图片九宫格展示以及常用view和视频处理库.
========
# 坐稳了,我要开车了

- 好吃不过饺子，好玩不过嫂子，吃嫂子包的饺子，玩包饺子的嫂子，吃最烫的饺子，操最浪的嫂子!
- 好喝不过拿铁， 好玩不过表姐，喝表姐买的拿铁，玩买拿铁的表姐，喝最香的拿铁，操最骚的表姐!
- 好吃不过海味，好玩不过表妹， 吃表妹做的海味，玩做海味的表妹，吃最美的海味，操最美的表妹!
- 好躺不过草席，好睡不过小姨，带着小姨买草席，买完草席睡小姨，躺着最凉的草席，睡着最骚的小姨，躺着小姨买的草席，睡着买草席的小姨，小姨买的草席最凉，草席上的小姨最强

![image](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496376464576&di=9a90e5111b3c5b3638073b6cc379e4da&imgtype=0&src=http%3A%2F%2Faliimg.changba.com%2Fcache%2Fphoto%2F128116118_640_640.jpg)

进入重点..android快速开发常用第三方库整合,集成了优雅的日志打印(可自动格式化json,xml,日志输出无字符长度4000的限制),两行代码调用EventBus事件分发,okgo网络访问一行代码实现文件上传下载带进度,上送json xml 等参数,可设置缓存模式以及SSL认证等,万能的RecyclerView适配器BaseQuicklyAdapter,实现上啦刷新,下拉加载,item不同布局,一行代码设置头布局和脚布局.


 # 一. 新增KLog 日志打印
 ## 使用方式:
 ```Java
     //全局只需初始化一次
     KLog.init(BuildConfig.LOG_DEBUG, "KLog");

     KLog.d("");
     KLog.xml("");//打印xml数据 自动格式化   看图
     KLog.json("");//打印json数据 自动格式化 看图
  ```
  ## 效果图
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/KLogImage.png)

# 二. 新增EventBus事件分发

## 使用方式:
```Java
        //注册监听
         EventBus.getDefault().register(this);
         //发出事件
         EventBus.getDefault().post(new User("张三",26),"张三");
          //接收事件
         @Subscriber(tag = "张三", mode = ThreadMode.MAIN)
             public void OnEventBus_ZhangSan(User user) {
                 KLog.d("EventBus使用tag张三接收到User:" + user);
             }
          //注销事件
         EventBus.getDefault().unregister(this);
```
## 效果图
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/MainAty.png)
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/TwoAty.png)


# 三. 新增okgo网络访问

## 使用方式:
```Java
    //全局只需初始化一次
    OkGo.init(this);
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true);
            //如果使用默认的 60秒,以下三行也不需要传
    //                .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
    //                .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
    //                .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
    
            //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
    //                .setCacheMode(CacheMode.NO_CACHE)
    
            //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
    //                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
    
            //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
    //                .setRetryCount(3)
    
            //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
    //              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
    //                .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效
    
            //可以设置https的证书,以下几种方案根据需要自己设置
    //                .setCertificates()                                  //方法一：信任所有证书,不安全有风险
    //              .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
    //              .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
    //              //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
    //               .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//
    
            //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
    //               .setHostnameVerifier(new SafeHostnameVerifier())
    
            //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
    //                .addInterceptor(new Interceptor() {
    //                    @Override
    //                    public Response intercept(Chain chain) throws IOException {
    //                        return chain.proceed(chain.request());
    //                    }
    //                })
    
            //这两行同上，不需要就不要加入
    //                .addCommonHeaders(headers)  //设置全局公共头
    //                .addCommonParams(params);   //设置全局公共参数
    
    
    //简单请求
     OkGo.post("url").params("key","v").execute(new AbsCallback<User>() {
                @Override
                public void onSuccess(User user, Call call, Response response) {
    
                }
    
                @Override
                public User convertSuccess(Response response) throws Exception {
                    return null;
                }
            });
```
- [OkGo详细使用文档](https://github.com/devzwy/KUtils/blob/master/Word/README_OKGO.md)

# 四. 新增BaseQuicklyAdapter

## 使用方式:
```Java
    //定义适配器
    public abstract class MyAdapter extends BaseQuickAdapter<MainTab,BaseViewHolder> {
    
    
        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<MainTab> data) {
            super(layoutResId, data);
        }
    }
    
    //初始化
     private MyAdapter mAdapter = new MyAdapter(R.layout.item_main_tab, null) {
            @Override
            protected void convert(BaseViewHolder helper, MainTab item) {
                helper.setText(R.id.tv_tab, item.getTabName());
                helper.addOnClickListener(R.id.tv_tab);
            }
        };
    
    //设置适配器及填充数据
      mRvMain.setLayoutManager(new LinearLayoutManager(this));//设置rv布局走向
            mAdapter.isFirstOnly(false);//item的加载动画是否仅在第一次加载时生效
            mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置加载动画
            mRvMain.setAdapter(mAdapter);
    
            //添加数据
            List<MainTab> l = new ArrayList<>();
            l.add(new MainTab("测试EventBus事件分发", 0));
            l.add(new MainTab("新功能1", 1));
            l.add(new MainTab("新功能2", 2));
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
    
                            break;
                        case 2:
    
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
```

- [BaseQuicklyAdapter详细使用文档](https://github.com/devzwy/KUtils/raw/master/Word/README_BaseQuicklyAdapter)


# 五. 新增Glide加载图片 一行代码

## 实现代码
```Java
     private void initAdapter() {
            mAdapter = new MyAdapter(null);
            mAdapter.isFirstOnly(false);
            mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRvMain.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
            mRvMain.setAdapter(mAdapter);
    
            List<String> l = new ArrayList<>();
            for (int i = 0; i < 80; i++) {
                if (i % 2 == 0) {
                    l.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496372638951&di=be636f1362f06d68b902b8115cda13dc&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fxgs_150428%2Fdesk_005.jpg");
                } else if (i % 3 == 0) {
                    l.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496373786557&di=bc8e94b8e82224a71ea7ea518d06e9ec&imgtype=0&src=http%3A%2F%2Fh7.86.cc%2Fwalls%2F20151020%2F1024x768_3fe2e5a70003597.jpg");
                } else if (i % 4 == 0) {
                    l.add("http://pic.qiantucdn.com/58pic/15/36/00/73b58PICgvY_1024.jpg!/fw/780/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center");
                } else {
                    l.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496373818671&di=d2fc21e4fbeec9792dfb53ff31c03093&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201701%2F23%2F10783ece0ff1eb32bf137ff9b7ba329e.jpg");
                }
                heightList.add(new Random().nextInt(300) + 200);
            }
            mAdapter.setNewData(l);
        }



        private class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    
            public MyAdapter(@Nullable List<String> data) {
                super(R.layout.item_girls, data);
            }
    
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                ImageView imageView = (ImageView) helper.getView(R.id.iv);
                //设置高度
                int height = heightList.get(helper.getPosition());
                //得到控件的高度
                ViewGroup.LayoutParams layoutParams = (imageView).getLayoutParams();
                //设置高度
                layoutParams.height = height;
                //使用Glide加载图片 placeholder参数为加载失败时显示的默认图片
                Glide.with(BeautyPicturesActivity.this).load(item).placeholder(R.mipmap.ic_launcher).into(imageView);
            }
        }
```
## 使用RecyclerView和BaseQuickAdapter实现的瀑布流效果图 调用请查看sample 几行代码调用(Glide加载图片只需一行代码即可)
- 瀑布流效果图<br />
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/cccc.png)

# 六.新增多媒体选择库
## 使用方式:
#### 1 在项目根目录下的build.gradle文件中添加一行代码
```Java
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }//添加这行代码
    }
}
```
#### 2 将sample中photoinfos包下的activity copy到项目中
#### 3 在AndroidManifest文件添加如下activity
```Java
    <!--图片选择库相关aty-->
            <activity
                android:name="cn.kutils.boxing.impl.ui.BoxingActivity"
                android:launchMode="singleTop"
                android:screenOrientation="portrait"
                android:theme="@style/Boxing.AppTheme.NoActionBar">
            </activity>
    
            <activity
                android:name="cn.kutils.boxing.impl.ui.BoxingViewActivity"
                android:launchMode="singleTop"
                android:screenOrientation="portrait"
                android:theme="@style/Boxing.AppTheme.NoActionBar">
            </activity>
    
            <activity
                android:name="cn.kutils.boxing.impl.ui.BoxingBottomSheetActivity"
                android:screenOrientation="portrait"
                android:theme="@style/Boxing.AppTheme.NoActionBar"/>
    
            <activity
                android:name="com.yalantis.ucrop.UCropActivity"
                android:screenOrientation="portrait"
                android:theme="@style/Theme.AppCompat.Light.NoActionBar"/>
    
            <provider
                android:name="android.support.v4.content.FileProvider"
                android:authorities="${applicationId}.file.provider"
                android:exported="false"
                android:grantUriPermissions="true">
    
                <meta-data
                    android:name="android.support.FILE_PROVIDER_PATHS"
                    android:resource="@xml/boxing_file_provider"/>
    
            </provider>

```
#### 4 在Application中进行相关初始化(全局只需初始化一次即可)
```Java
   @Override
    public void onCreate() {
        super.onCreate();
        IBoxingMediaLoader loader = new BoxingFrescoLoader(this);
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());
    }

```
#### 5 打开多媒体选择器
- 打开图片选择器(多选不带裁剪功能)
```Java
                //构造配置参数
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
                config.needCamera(R.mipmap.ic_launcher_round).needGif().withMaxCount(9); // 支持gif，相机，设置最大选图数
//            .withMediaPlaceHolderRes(resInt) // 设置默认图片占位图，默认无
//            .withAlbumPlaceHolderRes(resInt) // 设置默认相册占位图，默认无
//             .withVideoDurationRes(resInt) // 视频模式下，时长的图标，默认无
//                初始化Boxing，构造Intent并启动
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUESTCODE_1);
```
- 效果图<br />

![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/i1.png)
<br />
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/i2.png)

- 打开图片选择器(单选带裁剪功能)
```Java

        String cachePath = BoxingFileHelper.getCacheDir(this);
                        if (TextUtils.isEmpty(cachePath)) {
                            Toast.makeText(getApplicationContext(), R.string.boxing_storage_deny, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Uri destUri = new Uri.Builder()
                                .scheme("file")
                                .appendPath(cachePath)
                                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                                .build();
                        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).withCropOption(new BoxingCropOption(destUri))
                                .withMediaPlaceHolderRes(R.mipmap.ic_launcher_round);
                        Boxing.of(singleCropImgConfig).withIntent(this, BoxingActivity.class).start(this, REQUESTCODE_3);
```
- 效果图<br />
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/ii1.png)<br />

![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/ii2.png)

- 打开视频选择器
```Java
      //启动视频选择
                    BoxingConfig videoConfig = new BoxingConfig(BoxingConfig.Mode.VIDEO).withVideoDurationRes(R.mipmap.ic_launcher_round);
                    Boxing.of(videoConfig).withIntent(this, BoxingActivity.class).start(this, REQUESTCODE_2);
```
- 效果图<br />
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/iii1.png)

#### 6 取结果
```Java

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //
            List<BaseMedia> medias = Boxing.getResult(data);
            //注意判断null
        }
```
# 七.新增photoview图片预览
## 使用方式同ImageView:
- 定义方式1
```Java
    <com.github.chrisbanes.photoview.PhotoView
        android:id="@+id/pv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scaleType="fitXY"/>
```
- 定义方式2
```Java
        PhotoView photoView = new PhotoView(context);
        photoView.setScaleType(ImageView.ScaleType.FIT_XY);
```
#### 关于 PhotoView 异常捕获
```Java

    public class HackyProblematicViewGroup extends ProblematicViewGroup {
    
        public HackyProblematicViewGroup(Context context) {
            super(context);
        }
    
        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                            //uncomment if you really want to see these errors
                //e.printStackTrace();
                return false;
            }
        }
}
```



# 八.新增activity管理类
## 使用方式:
##### 在BaseActivty的onCreate()和onDestroy()中添加如下代码:
```Java
    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mediaaty);
            AppManager.getAppManager().addActivity(this);//将activity压入栈
     @Override
        protected void onDestroy() {
            super.onDestroy();
            AppManager.getAppManager().finishActivity(this);//将activity从栈取出
        }
```
##### 在退出应用时只需调用如下代码即可关闭所有页面
```Java
    AppManager.getAppManager().AppExit(this);
```

# 八.新增android端加解密工具类
## 使用方式(待编码):


# 九.新增CircleImageView圆角用户头像
## 使用方式同ImageView:
```Java
       <cn.kutils.view.CircleImageView
                   android:id="@+id/civ"
                   android:layout_width="60dp"
                   android:layout_height="60dp"
                   android:layout_centerInParent="true"
                   app:border_color="@color/boxing_black1"
                   app:border_width="2dp"/>
```
+ 效果图 <br/>
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/ci.png)
### 同行共同探讨技术可加我 QQ3648415
   ##### 缺少: 对话框 进度条 时间日期选择控件 九图预览 鲁班压缩 android6.0动态权限检测 共享参数 沉浸式状态栏 自定义toast 侧滑关闭页面  