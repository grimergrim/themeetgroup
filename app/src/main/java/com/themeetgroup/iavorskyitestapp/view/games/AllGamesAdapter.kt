package com.themeetgroup.iavorskyitestapp.view.games

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.themeetgroup.iavorskyitestapp.R
import com.themeetgroup.iavorskyitestapp.data.entities.Game
import javax.inject.Inject

class AllGamesAdapter @Inject constructor(private val context: Context) : //TODO how to get context from Hilt?
    RecyclerView.Adapter<AllGamesAdapter.ViewHolder>() {

    var collection: MutableList<Game> = mutableListOf()
    var onItemClick: ((Game, String, String) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val winnerNameTextView: TextView = view.findViewById(R.id.winner_name)
        val winnerScoreEditText: EditText = view.findViewById(R.id.winner_score)
        val loserNameTextView: TextView = view.findViewById(R.id.loser_name)
        val loserScoreEditText: EditText = view.findViewById(R.id.loser_score)
        val editGameButton: Button = view.findViewById(R.id.edit_game_button)
        var isEditing = false
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.all_games_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.winnerNameTextView.text = collection[position].winner
        viewHolder.winnerScoreEditText.setText(collection[position].winnerScore.toString())
        viewHolder.loserNameTextView.text = collection[position].loser
        viewHolder.loserScoreEditText.setText(collection[position].loserScore.toString())
        viewHolder.winnerScoreEditText.isEnabled = false
        viewHolder.loserScoreEditText.isEnabled = false
        viewHolder.editGameButton.setOnClickListener {
            if (viewHolder.isEditing) {
                viewHolder.isEditing = false
                viewHolder.editGameButton.text = context.getString(R.string.edit_button_title)
                viewHolder.winnerScoreEditText.isEnabled = false
                viewHolder.loserScoreEditText.isEnabled = false
                onItemClick?.invoke(
                    collection[position],
                    viewHolder.winnerScoreEditText.text.toString(),
                    viewHolder.loserScoreEditText.text.toString()
                )
            } else {
                viewHolder.isEditing = true
                viewHolder.editGameButton.text = context.getString(R.string.save_button_title)
                viewHolder.winnerScoreEditText.isEnabled = true
                viewHolder.loserScoreEditText.isEnabled = true
            }
        }
    }

    override fun getItemCount() = collection.size

}