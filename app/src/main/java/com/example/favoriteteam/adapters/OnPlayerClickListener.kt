package com.example.favoriteteam.adapters

import com.example.favoriteteam.service.Players


//oyuncuya tıklandığı zaman ne yapması gerektiğini tanımlıyoruz.
interface OnPlayersClickListener {
    fun onItemClick(item: Players)
}