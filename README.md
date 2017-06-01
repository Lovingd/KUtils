KUtils
========
android快速开发常用第三方库整合,集成了优雅的日志打印(可自动格式化json,xml,日志输出无字符长度4000的限制),两行代码调用EventBus事件分发,okgo网络访问一行代码实现文件上传下载带进度,上送json xml 等参数,可设置缓存模式以及SSL认证等,万能的RecyclerView适配器BaseQuicklyAdapter,实现上啦刷新,下拉加载,item不同布局,一行代码设置头布局和脚布局.

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
- [OkGo详细使用文档](https://github.com/devzwy/KUtils/blob/master/Word/REDEME.md)
- [OkRx使用文档](https://github.com/jeasonlzy/OkGO/blob/master/README_RX.md)https://github.com/jeasonlzy/OkGO/blob/master/README_RX.md
# 四. 新增BaseQuicklyAdapter

## 使用方式:
- [BaseQuicklyAdapter详细使用文档](https://github.com/devzwy/KUtils/raw/master/Word/README_BaseQuicklyAdapter)

   #### Luban   preferences