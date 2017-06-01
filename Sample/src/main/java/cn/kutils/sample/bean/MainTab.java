package cn.kutils.sample.bean;

import java.io.Serializable;

/**
 * 创建时间：2017/6/1  下午4:59
 * 创建人：赵文贇
 * 类描述：
 * 包名：cn.kutils.sample.bean
 * 待我代码编好，娶你为妻可好。
 */
public class MainTab implements Serializable {
    private String tabName;
    private int id;

    public MainTab(String tabName, int id) {
        this.tabName = tabName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
}
