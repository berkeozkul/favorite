package com.example.favoriteteam.service

import android.content.Context
import android.util.Log
import com.huawei.agconnect.cloud.database.AGConnectCloudDB
import com.huawei.agconnect.cloud.database.CloudDBZone
import com.huawei.agconnect.cloud.database.CloudDBZoneConfig
import com.huawei.agconnect.cloud.database.exceptions.AGConnectCloudDBException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//Bu classta CloudDB Zone açıp kapatma,ObjectType oluşturma gibi işlemleri yapıyrouz.
class CloudDBZoneWrapper {


    private var mCloudDb: AGConnectCloudDB
    var mCloudDbZone: CloudDBZone? = null

    init {
        mCloudDb = AGConnectCloudDB.getInstance()
    }

    //cloud db  bölgesini açmak için kullanıyoruz.
    companion object {
        fun initAGConnectCloudDB(context: Context?) {
            AGConnectCloudDB.initialize(context!!)
        }
    }

    //nesne tipini oluşturcak
    fun createObjectType() {
        try {
            mCloudDb.createObjectType(ObjectTypeInfoHelper.getObjectTypeInfo())
        } catch (exception: AGConnectCloudDBException) {
            Log.w("CloudDbRepository", exception.errMsg)
        }
    }
//cloud dbzone ile bağlanıcak.

    suspend fun openCloudDbZone(): Boolean {

        val mConfig = CloudDBZoneConfig(
            "PlayerDB",
            CloudDBZoneConfig.CloudDBZoneSyncProperty.CLOUDDBZONE_CLOUD_CACHE,
            CloudDBZoneConfig.CloudDBZoneAccessProperty.CLOUDDBZONE_PUBLIC
        )
        mConfig.persistenceEnabled = true

        val task = mCloudDb.openCloudDBZone2(mConfig, true)

        suspendCoroutine<CloudDBZone> { cont ->
            task.addOnSuccessListener {
                //  Log.i(TAG, "openCloudDbZone: success")
                mCloudDbZone = it
                cont.resume(it)
            }.addOnFailureListener {
                // Log.w(TAG, "Open cloudDBZone failed for " + it.message)
                cont.resumeWithException(it)
            }
        }
        return true
    }


}
