package com.themeetgroup.iavorskyitestapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Game(
    @ColumnInfo(name = "winner")
    var winner: String?,
    @ColumnInfo(name = "loser")
    var loser: String?,
    @ColumnInfo(name = "winner_score")
    var winnerScore: Int,
    @ColumnInfo(name = "loser_score")
    var loserScore: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
