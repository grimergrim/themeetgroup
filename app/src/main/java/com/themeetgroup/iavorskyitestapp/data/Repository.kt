package com.themeetgroup.iavorskyitestapp.data

import com.themeetgroup.iavorskyitestapp.data.dao.PlayerDao
import com.themeetgroup.iavorskyitestapp.data.entities.Player
import com.themeetgroup.iavorskyitestapp.data.dao.GameDao
import com.themeetgroup.iavorskyitestapp.data.entities.Game
import javax.inject.Inject

open class Repository @Inject constructor(
    private val playerDao: PlayerDao,
    private val gameDao: GameDao
) {

    open fun savePlayer(player: Player) = playerDao.savePlayer(player)

    open fun getAllPlayersNames() = playerDao.getAllPlayersNames()

    open fun findPlayerByName(name: String) = playerDao.findPlayerByName(name)

    open fun getAllPlayers() = playerDao.getAllPlayers()

    open fun saveGame(game: Game) = gameDao.saveGame(game)

    open fun getAllGames() = gameDao.getAllGames()

    open fun updateGame(game: Game) = gameDao.updateGame(game)

}