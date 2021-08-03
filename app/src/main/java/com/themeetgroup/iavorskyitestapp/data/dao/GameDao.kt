package com.themeetgroup.iavorskyitestapp.data.dao

import androidx.room.*
import com.themeetgroup.iavorskyitestapp.data.entities.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllGames(): List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGame(game: Game)

    @Update
    fun updateGame(game: Game)

}