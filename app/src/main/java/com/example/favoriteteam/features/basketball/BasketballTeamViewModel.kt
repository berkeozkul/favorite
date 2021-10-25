package com.example.favoriteteam.features.basketball

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoriteteam.service.CloudDBZoneWrapper
import com.example.favoriteteam.service.PlayersRepository
import kotlinx.coroutines.launch

class BasketballTeamViewModel : ViewModel() {

    var cloudDbRepository: CloudDBZoneWrapper = CloudDBZoneWrapper()

    private var playersRepository: PlayersRepository = PlayersRepository(cloudDbRepository)
    var playersListLiveData = playersRepository.playersList

    fun openDbZone() {
        cloudDbRepository.createObjectType()
        viewModelScope.launch {
            cloudDbRepository.openCloudDbZone()
            getBasketballPlayers()
        }
    }

    //basketbol oyuncuları için
    fun getBasketballPlayers() {
        playersRepository.getBasketball()
    }
}