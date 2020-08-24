package com.test.roomdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.roomdemo.db.model.PlayerModel

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playerModel : PlayerModel)

    @Query("SELECT * FROM player_table")
     fun getPlayers() : LiveData<List<PlayerModel>>

    @Query("DELETE FROM player_table")
     fun deleteAll()
}