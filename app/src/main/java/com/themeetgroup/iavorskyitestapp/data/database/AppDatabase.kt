package com.themeetgroup.iavorskyitestapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.themeetgroup.iavorskyitestapp.data.dao.PlayerDao
import com.themeetgroup.iavorskyitestapp.data.dao.GameDao
import com.themeetgroup.iavorskyitestapp.data.entities.Game
import com.themeetgroup.iavorskyitestapp.data.entities.Player

@Database(entities = [Player::class, Game::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    abstract fun gameDao(): GameDao

}