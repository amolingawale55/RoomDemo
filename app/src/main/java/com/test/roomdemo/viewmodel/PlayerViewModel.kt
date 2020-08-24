package com.test.roomdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.roomdemo.db.WordRoomDatabase
import com.test.roomdemo.db.model.PlayerModel
import com.test.roomdemo.repo.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    val playerDao = WordRoomDatabase.getDatabase(application,viewModelScope).PlayerDao()
    private val playerRepository : PlayerRepository = PlayerRepository(playerDao)

    var playerList = playerDao.getPlayers()

     fun insert(playerModel: PlayerModel) = viewModelScope.launch(Dispatchers.IO){
        playerRepository.insertPlayer(playerModel)
    }

    fun deleteAll()  = viewModelScope.launch{
        playerRepository.deleteAll()
    }
}