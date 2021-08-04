package com.themeetgroup.iavorskyitestapp.data.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class PrePopulationData : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        db.execSQL("INSERT INTO player (name, number_of_games, number_of_wins) VALUES ('Amos', 12, 6)")
        db.execSQL("INSERT INTO player (name, number_of_games, number_of_wins) VALUES ('Diego', 8, 5)")
        db.execSQL("INSERT INTO player (name, number_of_games, number_of_wins) VALUES ('Joel', 3, 1)")
        db.execSQL("INSERT INTO player (name, number_of_games, number_of_wins) VALUES ('Tim', 5, 2)")

        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Diego', 'Amos', 5, 4)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Diego', 'Amos', 5, 1)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Diego', 'Amos', 5, 2)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Diego', 'Amos', 5, 0)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Amos', 'Diego', 6, 5)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Amos', 'Diego', 5, 2)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Amos', 'Diego', 4, 0)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Diego', 'Joel', 5, 4)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Amos', 'Tim', 5, 4)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Tim', 'Amos', 5, 2)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Tim', 'Amos', 5, 3)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Amos', 'Tim', 5, 3)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Amos', 'Joel', 5, 4)")
        db.execSQL("INSERT INTO game (winner, loser, winner_score, loser_score) VALUES ('Joel', 'Tim', 5, 2)")
    }

}