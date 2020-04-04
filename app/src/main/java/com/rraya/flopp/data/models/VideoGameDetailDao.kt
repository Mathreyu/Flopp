package com.rraya.flopp.data.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoGameDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videoGameDetails: VideoGameDetails)

    @Query("SELECT * FROM video_game_detail_object WHERE id = :id")
    fun getVideoGameDetails(id: String): VideoGameDetails
}