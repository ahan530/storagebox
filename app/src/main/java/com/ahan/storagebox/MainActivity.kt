package com.ahan.storagebox

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import com.ahan.screendeal.BaseSreenUtils
import com.ahan.screendeal.BaseSreenUtils.getScreenRealHeight

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //使内容出现在status bar后边，如果要使用全屏的话再加上View.SYSTEM_UI_FLAG_FULLSCREEN
   //     window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
           //设置页面全屏显示
        val lp: WindowManager.LayoutParams = window.attributes

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
           // lp.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            lp.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
        }
        //设置页面延伸到刘海区显示
        window.attributes = lp

        setContentView(R.layout.activity_main)

        BaseSreenUtils.getSreenAllHeight(window)


        Log.i("info", "onCreate:${  getScreenRealHeight(this)} ")
    }
}