package com.example.favoriteteam.push
/*
import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PushManager {

    companion object {
        private var accessToken: String? = null
        private var pushToken: String? = null

        fun getDeviceIdToken(context: Context) {

            object : Thread() {
                override fun run() {
                    try {
                        val appId: String = AGConnectServicesConfig.fromContext(context).getString("client/app_id")
                        pushToken = HmsInstanceId.getInstance(context).getToken(appId, "HCM")

                        if (!TextUtils.isEmpty(pushToken)) {
                            Log.i("DeviceIdToken", "get token:$pushToken")
                        }
                    } catch (e: Exception) {
                        Log.i("DeviceIdToken", "getToken failed, $e")
                    }
                }
            }.start()

        }

        fun getAccessToken() {

            AccessTokenClient.getClient().create(AccessTokenInterface::class.java)
                .createAccessToken(
                    "client_credentials",
                    "Your App Secret",
                    "Your App Id"
                )
                .enqueue(object : Callback<AccessToken> {
                    override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                        Log.d("AccessToken", "ERROR : " + t.message)
                    }
                    override fun onResponse(
                        call: Call<AccessToken>,
                        response: Response<AccessToken>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("AccessToken", "AccessToken " + response.body()?.access_token)
                            accessToken = response.body()?.access_token
                            pushToken?.let { sendNotification(it) }
                        }
                    }
                })

        }

        fun sendNotification(pushToken : String) {

            val notificationMessageBody: NotificationMessageBody = NotificationMessageBody.Builder(
                "Hello World ! ", "It's from HMS Push Kit..",
                arrayOf(pushToken))
                .build()

            NotificationClient.getClient().create(NotificationInterface::class.java)
                .createNotification(
                    "Bearer $accessToken",
                    notificationMessageBody
                )
                .enqueue(object : Callback<NotificationMessage> {
                    override fun onFailure(call: Call<NotificationMessage>, t: Throwable) {
                        Log.d("SendPushNotification", "ERROR :  " + t.message)
                    }
                    override fun onResponse(
                        call: Call<NotificationMessage>,
                        response: Response<NotificationMessage>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("SendPushNotification", "Response " + response.body())
                        }
                    }
                })

        }

    }
}

 */