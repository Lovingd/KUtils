package cn.kutils.sample;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 创建时间：2017/6/2  下午2:48
 * 创建人：赵文贇
 * 类描述：自定义vp 捕获photoview缩小时的异常
 * 包名：cn.kutils.sample
 * 待我代码编好，娶你为妻可好。
 */
public class ViewPager_ extends ViewPager {
    public ViewPager_(Context context) {
        super(context);
    }

    public ViewPager_(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (Exception ex) {
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
        }
        return false;
    }
}
