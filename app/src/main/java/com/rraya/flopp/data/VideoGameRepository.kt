package com.rraya.flopp.data

import android.content.Context
import android.util.Log
import com.rraya.flopp.data.models.*
import com.rraya.flopp.network.NetworkUtils
import com.rraya.flopp.network.VideoGamesAPI
import retrofit2.Response

class VideoGameRepository constructor(
    private val context: Context
) {

    private val videoGameDao: VideoGameDao = FloppDB.getInstance(context).videoGameLightDao()
    private val videoGameDetailDao: VideoGameDetailDao = FloppDB.getInstance(context).detailsDao()
    private val client = NetworkUtils().createVideoGamesApi().create(VideoGamesAPI::class.java)

    suspend fun getVideoGames(): List<VideoGameLight> {
        val gamesResponse: Response<VideoGameResponse>
        try {
            gamesResponse = client.getGames()
            if (gamesResponse.isSuccessful) {
                val games = gamesResponse.body()?.results ?: emptyList()
                videoGameDao.insertAll(games)
            }
        } catch (throwable: Throwable) {
            Log.d(this.javaClass.name, throwable.message!!)
            //Error handling should be implemented here. Enum with status could be a good option
        }
        return videoGameDao.getVideoGames() ?: emptyList()
    }

    suspend fun getVideoGameDetails(id: String): VideoGameDetails {
        val details: Response<VideoGameDetails>
        try {
            details = client.getGameDetails(id)
            if (details.isSuccessful) {
                videoGameDetailDao.insert(details.body()!!)
            }
        } catch (throwable: Throwable) {
            Log.d(this.javaClass.name, throwable.message!!)
            //Error handling should be implemented here. Enum with status could be a good option
        }
        return videoGameDetailDao.getVideoGameDetails(id)
    }
}