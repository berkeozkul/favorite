package com.example.favoriteteam.features.football

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoriteteam.service.PlayersRepository
import com.example.favoriteteam.service.CloudDBZoneWrapper
import kotlinx.coroutines.launch

class FootballTeamViewModel : ViewModel() {

    var cloudDbRepository: CloudDBZoneWrapper = CloudDBZoneWrapper()

    private var playersRepository: PlayersRepository = PlayersRepository(cloudDbRepository)
    var playersListLiveData = playersRepository.playersList

    fun openDbZone() {
        cloudDbRepository.createObjectType()
        viewModelScope.launch {
            cloudDbRepository.openCloudDbZone()
            getFootballPlayers()
        }
    }

    // footballplayerları çağırmak için kullanıyoruz main activiyde
    fun getFootballPlayers() {
        playersRepository.getFootball()
    }

}