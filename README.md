KUtils
========
android快速开发常用第三方库整合,集成了优雅的日志打印(可自动格式化json,xml,日志输出无字符长度4000的限制),两行代码调用EventBus事件分发,okgo网络访问一行代码实现文件上传下载带进度,上送json xml 等参数,可设置缓存模式以及SSL认证等,万能的RecyclerView适配器BaseQuicklyAdapter,实现上啦刷新,下拉加载,item不同布局,一行代码设置头布局和脚布局.

 # 一. 新增KLog 日志打印
 ## 使用方式:
 ```Java
     KLog.init(BuildConfig.LOG_DEBUG, "KLog");
  ```
  ## 效果图
![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/KLogImage.png)




# 二. 新增EventBus事件分发

    使用:Screenshot - MainAty.png   TwoAty.png
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


 三. 新增okgo网络访问

    使用:Word - REDEME_OKGO,README_OKGO_JSONCALLBACK,README_OKGO_RX

 四. 新增BaseQuicklyAdapter

    使用:请查看Sample 详细使用请参照  Word -  README_BaseQuicklyAdapter

    Luban   preferences