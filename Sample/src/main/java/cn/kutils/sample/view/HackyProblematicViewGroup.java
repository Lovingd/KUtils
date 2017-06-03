package cn.kutils.sample.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class HackyProblematicViewGroup extends ViewGroup {

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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}