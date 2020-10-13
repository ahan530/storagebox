package com.ahan.screendeal

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.*
import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 * CreateTime:2020/10/13
 * Author:admin
 * Description:
 **/
object BaseSreenUtils {

    const val NoAlien = 0  //无异形屏
    const val YesAlien = 1 //有异形屏

    /**
     * 获取屏幕的高度
     * @param window
     * @return 屏幕总高度
     */
    fun getSreenAllHeight(window: Window): Int {

        //1:判断系统API版本>=28 基本都是使用google的异性屏幕API(如果有特殊的手机也需要判断)
        if (Build.VERSION.SDK_INT >= 28) {
            Log.e("TAG", "getSreenAllHeight: ")
            getIsAlienBygoogle(window)

        } else {
            //2:小于28 个别手机厂商还是有异形屏（判断是不是那几个特定的异形屏幕品牌）
        }

        return 0
    }

    /**
     * 获取状态栏高度
     * @param window
     * @return 状态栏高度
     */
    fun getStatusBarHeight(window: Window): Int {
        return 0
    }

    /**-------------------------------私有方法-----------------------------**/

    /**
     * 通过google方法判断是不是异形屏,同时返回异形状态栏高度
     */
    private fun getIsAlienBygoogle(window: Window): Int {
        var LogTag = "info"
        var mHeight = NoAlien
        if (Build.VERSION.SDK_INT >= 28) {

//            val windowInsets: WindowInsets = window.decorView.rootWindowInsets
//
//            if (windowInsets != null){
//                Log.d(LogTag, "windowInsets空的！")
//
//            // 当全屏顶部显示黑边时，getDisplayCutout()返回为null
//            val displayCutout: DisplayCutout? = windowInsets.displayCutout
//            if (displayCutout != null) {
//                //通过判断是否存在rects来确定是否刘海屏手机
//                val rects: List<Rect> = displayCutout.boundingRects
//                if (rects != null && rects.isNotEmpty()) Log.d(LogTag, "异形屏手机！")
//                Log.d(
//                    LogTag,
//                    "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft()
//                )
//                Log.d(
//                    LogTag,
//                    "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight()
//                )
//                Log.d(
//                    LogTag,
//                    "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop()
//                )
//                Log.d(
//                    LogTag,
//                    "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom()
//                )
//            }else{
//                Log.d(LogTag, "异形屏手机！")
//            }


            window.decorView.setOnApplyWindowInsetsListener(object :
                View.OnApplyWindowInsetsListener {
                override fun onApplyWindowInsets(
                    p0: View?,
                    windowInsets: WindowInsets?
                ): WindowInsets? {
                    Log.e("notch", "形111屏aaaa数据")
                    if (windowInsets == null) {
                        Log.e("notch", "形111屏数据")
                        return windowInsets
                    }
                    Log.e("notch", "形111屏aaaaqqqqq数据")
//                    windowInsets?.let {
//                        val displayCutout1 = it.displayCutout
//                        displayCutout1?.let {
//                            Log.e("notch", "形屏数据")
//                        }
//                        if (displayCutout1 == null) {
//                            Log.e("notch", "2121212形屏数据")
//                        }
//                    }

                    var cutout = windowInsets.displayCutout

                    if (cutout == null) {
                        Log.e("notch", "无异形屏数据")
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

                            Log.e("notch", "异形屏数据：$screenTopMargin $screenBottomMargin")
                        }
                    }
                    return windowInsets
                }
            })
        }
//        }

        return mHeight  //返回状态栏高度
    }

    private const val PORTRAIT = 0
    private const val LANDSCAPE = 1

    @NonNull
    private val mRealSizes: Array<Point?> = arrayOfNulls<Point>(2)


    fun getScreenRealHeight(@Nullable context: Context?): Int {

        //API小于17的
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return getScreenHeight(context)
        }
        var orientation: Int = context?.resources?.configuration?.orientation
            ?: context!!.resources.configuration.orientation
        orientation = if (orientation == Configuration.ORIENTATION_PORTRAIT) PORTRAIT else LANDSCAPE

        if (mRealSizes[orientation] == null) {
            val windowManager: WindowManager =
                (if (context != null) context.getSystemService(Context.WINDOW_SERVICE) as WindowManager else context
                    !!.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                    ?: return getScreenHeight(context)
            val display: Display = windowManager.defaultDisplay
            val point = Point()
            display.getRealSize(point)
            mRealSizes[orientation] = point
        }
        return mRealSizes[orientation]!!.y
    }

    fun getScreenHeight(@Nullable context: Context?): Int {
        return if (context != null) {
            context.getResources().getDisplayMetrics().heightPixels
        } else 0
    }

    @Volatile
    private var mHasCheckAllScreen = false

    @Volatile
    private var mIsAllScreenDevice = false

    fun isAllScreenDevice(context: Context): Boolean {
        if (mHasCheckAllScreen) {
            return mIsAllScreenDevice
        }
        mHasCheckAllScreen = true
        mIsAllScreenDevice = false
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false
        }
        val windowManager: WindowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {
            val display: Display = windowManager.getDefaultDisplay()
            val point = Point()
            display.getRealSize(point)
            val width: Float
            val height: Float
            if (point.x < point.y) {
                width = point.x.toFloat()
                height = point.y.toFloat()
            } else {
                width = point.y.toFloat()
                height = point.x.toFloat()
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true
            }
        }
        return mIsAllScreenDevice
    }

}