package com.ahan.storagebox.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahan.screendeal.BaseSreenUtils
import com.ahan.storagebox.R
import com.ahan.storagebox.adapter.ScreenAdapter.ScreenAdapter
import com.ahan.storagebox.model.ScreenBean
import kotlinx.android.synthetic.main.activity_screen_deal.*

/**
 * CreateTime:2020/10/14
 * @Author:admin
 * Description: 屏幕处理示例
 **/
class ScreenDealActivity : AppCompatActivity() {

    private var listData = ArrayList<ScreenBean>()
    private val myAdapter by lazy { ScreenAdapter(listData) }

    private var menu1=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getIntExtra("key", 0).let {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            //设置页面全屏显示
            val lp: WindowManager.LayoutParams = window.attributes
            //大于28 设置异形屏使用
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                if (it == 0) {
                    lp.layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
                    menu1="未设置使用异形屏"
                } else {
                    lp.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                    menu1="已设置设置使用异形屏，模式为：LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES"
                }
                window.attributes = lp
            }
        }
        setContentView(R.layout.activity_screen_deal)

        initSetting()

        initView()
    }

    private fun initSetting() {
        tv1.text=menu1
    }

    private fun initView() {
        listData.add(ScreenBean("1：获取状态栏高度", ""))
        listData.add(ScreenBean("2：获取异形屏高度", ""))

        rv.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@ScreenDealActivity)
        }
        myAdapter.setOnItemClickListener { _, _, position ->

            when (position) {
                0 -> {
                    //获取状态栏高度
                    val statusBarHeight = BaseSreenUtils.getStatusBarHeight(this)
                    listData[0].content = "$statusBarHeight"
                }
                1 -> {
                    val sreenAllHeight = BaseSreenUtils.getSreenAllHeight(window)
                    if (sreenAllHeight == 0) {
                        listData[1].content = "该手机无异性屏，或未开启异形屏使用"
                    } else {
                        listData[1].content = "异形屏高度为$sreenAllHeight"
                    }
                }
                2 -> {

                }
                else -> {

                }
            }
            myAdapter.notifyDataSetChanged();
        }
    }
}