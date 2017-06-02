package cn.kutils.boxing.impl;

import android.support.annotation.DrawableRes;

import cn.kutils.R;
import cn.kutils.boxing.model.BoxingManager;


/**
 * Help getting the resource in config.
 *
 * @author ChenSL
 */

public class BoxingResHelper {

    @DrawableRes
    public static int getMediaCheckedRes() {
        int result = BoxingManager.getInstance().getBoxingConfig().getMediaCheckedRes();
        return result > 0 ? result : R.drawable.ic_boxing_checked;
    }

    @DrawableRes
    public static int getMediaUncheckedRes() {
        int result = BoxingManager.getInstance().getBoxingConfig().getMediaUnCheckedRes();
        return result > 0 ? result : R.drawable.shape_boxing_unchecked;
    }

    @DrawableRes
    public static int getCameraRes() {
        return BoxingManager.getInstance().getBoxingConfig().getCameraRes();
    }
}
