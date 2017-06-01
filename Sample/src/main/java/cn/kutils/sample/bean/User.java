package cn.kutils.sample.bean;

import java.io.Serializable;

/**
 * 创建时间：2017/6/1  下午4:01
 * 创建人：赵文贇
 * 类描述：
 * 包名：cn.kutils.sample.bean
 * 待我代码编好，娶你为妻可好。
 */
public class User implements Serializable {
    private String name ;
    private int age ;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
