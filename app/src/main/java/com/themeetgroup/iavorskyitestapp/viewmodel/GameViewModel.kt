package com.themeetgroup.iavorskyitestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themeetgroup.iavorskyitestapp.data.Repository
import com.themeetgroup.iavorskyitestapp.data.entities.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var getAllGamesData = MutableLiveData<List<Game>>()

    fun getAllGames() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllGamesData.postValue(repository.getAllGames())
        }
    }

    fun updatePlayers(game: Game, oldWinnerNewScore: String, oldLoserNewScore: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val oldWinner = game.winner
            val newWinner = if (oldWinnerNewScore > oldLoserNewScore) game.loser else game.winner
            if (oldWinner != newWinner) {
                game.winner = newWinner
                game.loser = oldWinner
                val winner = repository.findPlayerByName(game.winner ?: "")
                winner.numberOfWins += 1
                repository.savePlayer(winner)
                val loser = repository.findPlayerByName(game.loser ?: "")
                loser.numberOfWins -= 1
                repository.savePlayer(loser)
                game.winnerScore = oldLoserNewScore.toInt()
                game.loserScore = oldWinnerNewScore.toInt()
            } else {
                if (game.winnerScore != oldWinnerNewScore.toInt()) {
                    game.winnerScore = oldWinnerNewScore.toInt()
                }
                if (game.loserScore != oldLoserNewScore.toInt()) {
                    game.loserScore = oldLoserNewScore.toInt()
                }
            }
            repository.updateGame(game)
        }
    }

}