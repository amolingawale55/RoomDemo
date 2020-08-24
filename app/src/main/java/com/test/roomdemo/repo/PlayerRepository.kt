package com.test.roomdemo.repo

import androidx.lifecycle.LiveData
import com.test.roomdemo.db.dao.PlayerDao
import com.test.roomdemo.db.model.PlayerModel

class PlayerRepository(private val playerDao : PlayerDao) {

     fun insertPlayer(playerModel: PlayerModel){
        playerDao.insert(playerModel)
    }

     fun getPlayers() : LiveData<List<PlayerModel>>{
        return playerDao.getPlayers()
    }

     fun deleteAll(){
        playerDao.deleteAll()
    }
}