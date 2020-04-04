package com.rraya.flopp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "video_game_light_object")
data class VideoGameLight(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val name: String? = "",
    @SerializedName("metacritic") val metacriticScore: String? = "",
    @SerializedName("background_image") val backgroundImage: String
)