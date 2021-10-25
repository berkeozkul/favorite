package com.example.favoriteteam.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.huawei.agconnect.cloud.database.CloudDBZoneQuery
import com.huawei.agconnect.cloud.database.CloudDBZoneSnapshot
import com.huawei.hmf.tasks.Task

class PlayersCardRepository(private val cloudDbZoneWrapper: CloudDBZoneWrapper) : IRepository {

    val playerCardData = MutableLiveData<PlayersCard>()

    //IRepository interfacete bunları girdiğimiz için çağırdık.
    override fun getBasketball() {
    }

    override fun getFootball() {
    }

    //Her oyuncuya tıklanığı zaman cloud dbden açılan bottomsheetin datalarını çekimini tetikliyoruz ve viewmodelda gözükmesi için çağırıyoruz.
    override fun getById(id: Int) {
        val query = CloudDBZoneQuery.where(PlayersCard::class.java).equalTo("playerid", id)
        val queryTask: Task<CloudDBZoneSnapshot<PlayersCard>> =
            cloudDbZoneWrapper.mCloudDbZone!!.executeQuery(
                query,
                CloudDBZoneQuery.CloudDBZoneQueryPolicy.POLICY_QUERY_FROM_CLOUD_ONLY
            )
        queryTask.addOnSuccessListener { it ->
            if (it.snapshotObjects.hasNext()) {
                val player = it.snapshotObjects.next()
                playerCardData.postValue(player)
            }
        }.addOnFailureListener { it ->
            Log.w("PlayersCardRepository", "getById error: ${it.message}")
        }
    }
}