package com.example.favoriteteam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoriteteam.service.CloudDBZoneWrapper
import com.example.favoriteteam.service.PlayersCardRepository
import kotlinx.coroutines.launch

class PlayerCardViewModel : ViewModel() {

    var cloudDbRepository: CloudDBZoneWrapper = CloudDBZoneWrapper()

    private var playersCardRepository: PlayersCardRepository = PlayersCardRepository(cloudDbRepository)
    val playerCardData = playersCardRepository.playerCardData

    fun openCloudDBZone(id: Int) {
        cloudDbRepository.createObjectType()

        viewModelScope.launch {
            cloudDbRepository.openCloudDbZone()
            playersCardRepository.getById(id)
        }
    }
}

