package com.themeetgroup.iavorskyitestapp.data.dao

import androidx.room.*
import com.themeetgroup.iavorskyitestapp.data.entities.Player

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player")
    fun getAllPlayers(): List<Player>

    @Query("SELECT name FROM player")
    fun getAllPlayersNames(): List<String>

    @Query("SELECT * FROM player WHERE name LIKE :first LIMIT 1")
    fun findPlayerByName(first: String): Player

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlayer(vararg player: Player)

}