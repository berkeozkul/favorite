package com.example.favoriteteam.service
//Burda oyuncuları çağıdığımız interface.
interface IRepository {

    fun getBasketball()
    fun getFootball()
    fun getById(id: Int)
}