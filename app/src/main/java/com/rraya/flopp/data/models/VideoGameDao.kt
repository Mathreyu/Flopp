package com.rraya.flopp.data.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoGameDao {
    @Query("SELECT * FROM video_game_light_object ORDER BY name")
    fun getVideoGames(): List<VideoGameLight>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videoGameList: List<VideoGameLight>)
}