package com.example.favoriteteam.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.huawei.agconnect.cloud.database.CloudDBZone
import com.huawei.agconnect.cloud.database.CloudDBZoneObjectList
import com.huawei.agconnect.cloud.database.CloudDBZoneQuery
import com.huawei.agconnect.cloud.database.CloudDBZoneSnapshot
import com.huawei.hmf.tasks.Task

class PlayersRepository(private val cloudDbZoneWrapper: CloudDBZoneWrapper) : IRepository {

    val playersList = MutableLiveData<MutableList<Players>>()

    companion object {
        private val TAG = "PlayersRepository"
    }

    //cloud dbden basketbol oyuncularını çekmeyi tetikler ve viewmodelda görünümde çağırıyoruz
    override fun getBasketball() {
        val query = CloudDBZoneQuery.where(Players::class.java).equalTo("branch", "Basketball")
        val queryTask: Task<CloudDBZoneSnapshot<Players>> = cloudDbZoneWrapper.mCloudDbZone!!.executeQuery(
            query,
            CloudDBZoneQuery.CloudDBZoneQueryPolicy.POLICY_QUERY_FROM_CLOUD_ONLY
        )

        queryTask.addOnSuccessListener { it ->
            Log.i("PlayersRepository", "getAll success")
            getBasketballPlayersResultHandler(it)
        }.addOnFailureListener { it ->
            Log.e("PlayersRepository", "getAll error ${it.localizedMessage}")
        }
    }

    //basketballda olduğu gibi aynısını burdada yapıyoruz.
    override fun getFootball() {
        val query2 = CloudDBZoneQuery.where(Players::class.java).equalTo("branch", "Football")
        val queryTask2: Task<CloudDBZoneSnapshot<Players>> = cloudDbZoneWrapper.mCloudDbZone!!.executeQuery(
            query2,
            CloudDBZoneQuery.CloudDBZoneQueryPolicy.POLICY_QUERY_FROM_CLOUD_ONLY
        )

        queryTask2.addOnSuccessListener { it ->
            Log.i("PlayersRepository", "getAll success")
            getFootballPlayersResultHandler(it)
        }.addOnFailureListener { it ->
            Log.e("PlayersRepository", "getAll error ${it.localizedMessage}")
        }
    }

    //
    private fun getBasketballPlayersResultHandler(snapshot: CloudDBZoneSnapshot<Players>) {
        val playersCursor: CloudDBZoneObjectList<Players> = snapshot.snapshotObjects
        val playersListLocal = mutableListOf<Players>()

        try {
            while (playersCursor.hasNext()) {
                val player = playersCursor.next()
                playersListLocal.add(player)
                Log.d(TAG, "getAllPlayersResultHandler: ${player.name}")
            }
        } catch (exception: Exception) {
            Log.w("PlayersRepository", "getAllPlayers error: ${exception.message}")
        }

        snapshot.release()

        playersList.postValue(playersListLocal)

    }

    private fun getFootballPlayersResultHandler(snapshot: CloudDBZoneSnapshot<Players>) {
        val playersCursor2: CloudDBZoneObjectList<Players> = snapshot.snapshotObjects
        val playersListLocal2 = mutableListOf<Players>()

        try {
            while (playersCursor2.hasNext()) {
                val players = playersCursor2.next()
                playersListLocal2.add(players)
                Log.d(TAG, "getAllPlayersResultHandler: ${players.name}")
            }
        } catch (exception: Exception) {
            Log.w("PlayersRepository", "getAllPlayers error: ${exception.message}")
        }

        snapshot.release()

        playersList.postValue(playersListLocal2)

    }

    override fun getById(id: Int) {
    }

}