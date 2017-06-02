/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package cn.kutils.boxing.impl.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * https://github.com/chrisbanes/PhotoView/issues/35
 */
public class HackyViewPager extends ViewPager {
    private boolean mIsLocked;

    public HackyViewPager(Context context) {
        super(context);
        mIsLocked = false;
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mIsLocked = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mIsLocked) {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !mIsLocked && super.onTouchEvent(event);
    }

    public boolean isLocked() {
        return mIsLocked;
    }

    public void setLocked(boolean isLocked) {
        this.mIsLocked = isLocked;
    }

}
