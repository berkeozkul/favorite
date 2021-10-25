package com.example.favoriteteam.util

import android.app.Application
import com.example.favoriteteam.service.CloudDBZoneWrapper

class FavoriteTeamApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CloudDBZoneWrapper.initAGConnectCloudDB(applicationContext)
    }

    }
