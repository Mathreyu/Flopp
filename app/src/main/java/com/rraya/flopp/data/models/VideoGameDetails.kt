package com.rraya.flopp.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "video_game_detail_object")
data class VideoGameDetails(
    @NonNull @PrimaryKey val id: String,
    val name: String? = "",
    val description: String? = "",
    @SerializedName("background_image") val backgroundImage: String? = "",
    val metacritic: String? = ""
)