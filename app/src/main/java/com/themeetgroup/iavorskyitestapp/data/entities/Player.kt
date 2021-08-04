package com.themeetgroup.iavorskyitestapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Player(
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "number_of_games")
    var numberOfGames: Int,
    @ColumnInfo(name = "number_of_wins")
    var numberOfWins: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
