package com.themeetgroup.iavorskyitestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themeetgroup.iavorskyitestapp.data.Repository
import com.themeetgroup.iavorskyitestapp.data.entities.Player
import com.themeetgroup.iavorskyitestapp.data.entities.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var getPlayersNamesData = MutableLiveData<List<String>>()
    var getPlayersRatingsData = MutableLiveData<List<Player>>()

    fun getPlayersNames() {
        viewModelScope.launch(Dispatchers.IO) {
            getPlayersNamesData.postValue(repository.getAllPlayersNames())
        }
    }

    @Suppress("SENSELESS_COMPARISON")
    fun savePlayer(playerName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val player = repository.findPlayerByName(playerName)
            if (player == null)
                repository.savePlayer(Player(playerName, 0, 0))
            getPlayersNamesData.postValue(repository.getAllPlayersNames())
            getPlayersRatingsData.postValue(sortPlayers(repository.getAllPlayers()))
        }
    }

    fun saveGame(
        firstPlayerName: String,
        firstPlayerScore: Int,
        secondPlayerName: String,
        secondPlayerScore: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val winner: String
            val loser: String
            val winnerScore: Int
            val loserScore: Int
            val firstPLayer = repository.findPlayerByName(firstPlayerName)
            firstPLayer.numberOfGames += 1
            val secondPLayer = repository.findPlayerByName(secondPlayerName)
            secondPLayer.numberOfGames += 1
            if (firstPlayerScore > secondPlayerScore) {
                firstPLayer.numberOfWins += 1
                winner = firstPlayerName
                loser = secondPlayerName
                winnerScore = firstPlayerScore
                loserScore = secondPlayerScore
            } else {
                secondPLayer.numberOfWins += 1
                winner = secondPlayerName
                loser = firstPlayerName
                winnerScore = secondPlayerScore
                loserScore = firstPlayerScore
            }
            repository.savePlayer(firstPLayer)
            repository.savePlayer(secondPLayer)
            repository.saveGame(Game(winner, loser, winnerScore, loserScore))
            getPlayersRatingsData.postValue(sortPlayers(repository.getAllPlayers()))
        }
    }

    fun getPlayersRatings() {
        viewModelScope.launch(Dispatchers.IO) {
            getPlayersRatingsData.postValue(sortPlayers(repository.getAllPlayers()))
        }
    }

    private fun sortPlayers(players: List<Player>): List<Player> {
        return players.sortedWith(compareBy({ it.numberOfWins }, { it.numberOfGames })).reversed()
    }

}