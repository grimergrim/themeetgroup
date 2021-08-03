package com.themeetgroup.iavorskyitestapp.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.themeetgroup.iavorskyitestapp.R
import com.themeetgroup.iavorskyitestapp.utils.ToastsHelper
import com.themeetgroup.iavorskyitestapp.view.games.AllGamesActivity
import com.themeetgroup.iavorskyitestapp.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var playersRatingAdapter: PlayersRatingAdapter
    private lateinit var newPlayerEditText: EditText
    private lateinit var addPLayerButton: Button
    private lateinit var addGameButton: Button
    private lateinit var allGamesButton: Button
    private lateinit var firstPlayerSpinner: Spinner
    private lateinit var secondPlayerSpinner: Spinner
    private lateinit var firstPlayerScore: EditText
    private lateinit var secondPlayerScore: EditText
    private lateinit var playersRatingsList: RecyclerView
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        setObservables()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        getAllData()
    }

    private fun getAllData() {
        playerViewModel.getPlayersNames()
        playerViewModel.getPlayersRatings()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservables() {
        playerViewModel.getPlayersNamesData.observe(this, {
            setFirstPlayerSpinner(it)
            setSecondPlayerSpinner(it)
        })
        playerViewModel.getPlayersRatingsData.observe(this, {
            playersRatingsList.apply {
                playersRatingAdapter.collection.clear()
                playersRatingAdapter.collection = it.toMutableList()
                adapter = playersRatingAdapter
            }
            playersRatingAdapter.notifyDataSetChanged()
        })
    }

    private fun findViews() {
        newPlayerEditText = findViewById(R.id.new_player_edit_text)
        addPLayerButton = findViewById(R.id.add_player_button)
        addGameButton = findViewById(R.id.add_game_button)
        allGamesButton = findViewById(R.id.all_games_button)
        firstPlayerSpinner = findViewById(R.id.first_player_spinner)
        secondPlayerSpinner = findViewById(R.id.second_player_spinner)
        firstPlayerScore = findViewById(R.id.first_player_score)
        secondPlayerScore = findViewById(R.id.second_player_score)
        playersRatingsList = findViewById(R.id.players_list)
    }

    private fun setListeners() {
        addPLayerButton.setOnClickListener {
            playerViewModel.savePlayer(newPlayerEditText.text.toString())
            newPlayerEditText.setText("")
        }
        findViewById<Button>(R.id.add_game_button).setOnClickListener {
            val firstPlayerName = firstPlayerSpinner.selectedItem.toString()
            val firstPlayerScoreNumber = firstPlayerScore.text.toString()
            val secondPlayerName = secondPlayerSpinner.selectedItem.toString()
            val secondPlayerScoreNumber = secondPlayerScore.text.toString()
            if (firstPlayerScoreNumber.isNotEmpty() && secondPlayerScoreNumber.isNotEmpty()
                && firstPlayerName != secondPlayerName
                && firstPlayerScoreNumber != secondPlayerScoreNumber
            ) {
                playerViewModel.saveGame(
                    firstPlayerName,
                    firstPlayerScoreNumber.toInt(),
                    secondPlayerName,
                    secondPlayerScoreNumber.toInt()
                )
                firstPlayerScore.setText("")
                secondPlayerScore.setText("")
            } else if (firstPlayerScoreNumber.isEmpty() || secondPlayerScoreNumber.isEmpty())
                ToastsHelper.showToast(getString(R.string.no_score_warning), applicationContext)
            else if (firstPlayerName == secondPlayerName)
                ToastsHelper.showToast(getString(R.string.same_player_warning), applicationContext)
            else if (firstPlayerScoreNumber == secondPlayerScoreNumber)
                ToastsHelper.showToast(getString(R.string.equal_score_warning), applicationContext)
        }
        findViewById<Button>(R.id.all_games_button).setOnClickListener {
            startActivity(Intent(this, AllGamesActivity::class.java))
        }
    }

    private fun setFirstPlayerSpinner(players: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            players
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        firstPlayerSpinner.adapter = adapter
    }

    private fun setSecondPlayerSpinner(players: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            players
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        secondPlayerSpinner.adapter = adapter
    }

}