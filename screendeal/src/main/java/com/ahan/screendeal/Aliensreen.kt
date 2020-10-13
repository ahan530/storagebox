package com.ahan.screendeal

import android.content.res.Resources
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.DisplayCutout
import android.view.View
import android.view.Window
import android.view.WindowInsets

/**
 * CreateTime:2020/10/13
 * Author:admin
 * Description: 判断是否含有异性屏
 **/
object Aliensreen {

    fun haveAlien(
        window: Window
    ): Boolean {
        if (Build.VERSION.SDK_INT >= 28) {
            window.decorView.setOnApplyWindowInsetsListener(object :
                View.OnApplyWindowInsetsListener {
                override fun onApplyWindowInsets(
                    p0: View?,
                    windowInsets: WindowInsets?
                ): WindowInsets {
                    Log.e("notch", "onApplyWindowInsets");
                    if (windowInsets == null) {
                        return windowInsets!!
                    }
                    var cutout = windowInsets.displayCutout
                    if (cutout == null) {
                        //使用googleAPI的手机通过cutout是否为null判断是否刘海屏手机
                    } else {
                        var rects = cutout.boundingRects
                        if (rects.size == 0) {
                            Log.e("notch", "rects==null || rects.size()==0, is not notch screen")
                        } else {
                            Log.e("notch", "rect size:" + rects.size)//注意：刘海的数量可以是多个
                            for (i in rects.indices) {
                                val e = Log.e(
                                    "notch", "cutout.getSafeInsetTop():" + cutout.safeInsetTop
                                            + ", cutout.getSafeInsetBottom():" + cutout.safeInsetBottom
                                            + ", cutout.getSafeInsetLeft():" + cutout.safeInsetLeft
                                            + ", cutout.getSafeInsetRight():" + cutout.safeInsetRight
                                            + ", cutout.rects:" + rects[i]
                                )
                            }

                            var screenTopMargin = cutout.safeInsetTop
                            var screenBottomMargin = cutout.safeInsetBottom;
                        }
                    }
                    return windowInsets;
                }
            })
        } else {
            //API小于28的手机的异形屏需要单独去处理

        }

        return true
    }


    fun getStatusBarHeight(resources: Resources): Int {
        var resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        var height = resources.getDimensionPixelSize(resourceId)
        return height
    }

    fun getScreenTopMargin(): Int {
        return 0
    }

    fun getScreenBottomMargin(): Int {
        return 0
    }

}