package com.rraya.flopp.network

import com.rraya.flopp.data.models.VideoGameDetails
import com.rraya.flopp.data.models.VideoGameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoGamesAPI {

    @GET("games")
    suspend fun getGames(): Response<VideoGameResponse>

    @GET("games/{id}")
    suspend fun getGameDetails(@Path("id") id: String): Response<VideoGameDetails>
}