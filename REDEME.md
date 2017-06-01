KUtils v1.0
================
 # 一. 新增KLog 日志打印

    ## 使用方式:![image](https://github.com/devzwy/KUtils/raw/master/Screenshot/KLogImage.png)
    ```Java
    KLog.init(BuildConfig.LOG_DEBUG, "KLog");
    ```
 二. 新增EventBus事件分发

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