package com.themeetgroup.iavorskyitestapp.view.games

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.themeetgroup.iavorskyitestapp.R
import com.themeetgroup.iavorskyitestapp.data.entities.Game
import com.themeetgroup.iavorskyitestapp.utils.ToastsHelper
import com.themeetgroup.iavorskyitestapp.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllGamesActivity : AppCompatActivity() {

    @Inject
    lateinit var allGamesAdapter: AllGamesAdapter
    private val gameViewModel: GameViewModel by viewModels()
    private lateinit var allGamesList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_games)
        findViews()
        setObservables()
        gameViewModel.getAllGames()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservables() {
        gameViewModel.getAllGamesData.observe(this, {
            allGamesList.apply {
                allGamesAdapter.collection.clear()
                allGamesAdapter.collection = it.toMutableList()
                allGamesAdapter.onItemClick = { game, winnerNewScore, loserNewScore ->
                    updatePlayers(game, winnerNewScore, loserNewScore)
                }
                adapter = allGamesAdapter
            }
            allGamesAdapter.notifyDataSetChanged()
        })
    }

    private fun updatePlayers(game: Game, winnerNewScore: String, loserNewScore: String) {
        if (winnerNewScore == loserNewScore)
            ToastsHelper.showToast(getString(R.string.equal_score_warning), applicationContext)
        else
            gameViewModel.updatePlayers(game, winnerNewScore, loserNewScore)
    }

    private fun findViews() {
        allGamesList = findViewById(R.id.games_list)
    }

}