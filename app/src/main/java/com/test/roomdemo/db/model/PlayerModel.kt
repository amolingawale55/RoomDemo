package com.test.roomdemo.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class PlayerModel(
    @ColumnInfo(name = "player_name") val playerName : String,
    @ColumnInfo(name = "player_age") val playerAge : String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}