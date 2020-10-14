package com.ahan.storagebox

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ahan.screendeal.BaseSreenUtils
import com.ahan.screendeal.BaseSreenUtils.getScreenRealHeight
import com.ahan.storagebox.ui.ScreenDealActivity
import com.ahan.storagebox.ui.StrorageActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.switchButton
import kotlinx.android.synthetic.main.activity_screen_deal.*

class MainActivity : AppCompatActivity() {

    private var isOpenAlienScreen = 0 //1：打开
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("info", "onCreate:${  getScreenRealHeight(this)} ")

        //开启和关闭异形屏使用
        switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                isOpenAlienScreen = 1
            }else{
                isOpenAlienScreen = 0
            }
        }

        //屏幕处理工具类
        screendeal.setOnClickListener {
            val intent = Intent(this@MainActivity, ScreenDealActivity::class.java)
            intent.putExtra("key",isOpenAlienScreen)
            startActivity(intent)
        }

        //存储
        button.setOnClickListener {
            startActivity(Intent(this@MainActivity, StrorageActivity::class.java))
        }
    }
}