package com.example.favoriteteam

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.favoriteteam.R
import com.example.favoriteteam.push.PushService
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsInstance
import com.huawei.hms.analytics.HiAnalyticsTools
import com.huawei.hms.push.RemoteMessage

var pushToken: String? = null
var accessToken: String? = null
var context : Context? = null
lateinit  var instance : HiAnalyticsInstance

class MainActivity : AppCompatActivity() {


    private lateinit var tvToken: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        HiAnalyticsTools.enableLog()
        instance = HiAnalytics.getInstance(applicationContext)
        instance.setAnalyticsEnabled(true)
        instance.regHmsSvcEvent()

        tvToken = findViewById(R.id.tv_log)
        val receiver = MyReceiver()
        val filter = IntentFilter()
        filter.addAction("com.huawei.codelabpush.ON_NEW_TOKEN")
        registerReceiver(receiver, filter)
    }

    inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if ("com.huawei.codelabpush.ON_NEW_TOKEN" == intent?.action) {
                val token = intent.getStringExtra("token")
                tvToken?.text = token
            }
        }
    }
    }







