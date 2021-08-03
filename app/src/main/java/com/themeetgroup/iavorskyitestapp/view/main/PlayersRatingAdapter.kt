package com.themeetgroup.iavorskyitestapp.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.themeetgroup.iavorskyitestapp.data.entities.Player
import com.themeetgroup.iavorskyitestapp.R
import javax.inject.Inject

class PlayersRatingAdapter @Inject constructor() :
    RecyclerView.Adapter<PlayersRatingAdapter.ViewHolder>() {

    var collection: MutableList<Player> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerNameTextView: TextView = view.findViewById(R.id.player_name)
        val numberOfGamesTextView: TextView = view.findViewById(R.id.number_of_games)
        val numberOfWinsTextView: TextView = view.findViewById(R.id.number_of_wins)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.player_rating_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.playerNameTextView.text = collection[position].name
        viewHolder.numberOfGamesTextView.text = collection[position].numberOfGames.toString()
        viewHolder.numberOfWinsTextView.text = collection[position].numberOfWins.toString()
    }

    override fun getItemCount() = collection.size

}