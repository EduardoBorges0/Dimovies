package com.app.cinedimen.data.network.listscreen

import com.app.cinedimen.BuildConfig
import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface MainScreenNetwork {
    @GET
    suspend fun getMovies(
        @Url endpoint: String,
        @Header("Authorization") authHeader: String = "Bearer ${BuildConfig.API_KEY}",
        @Header("accept") accept: String = "application/json",
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): Response<NowPlayingModel>
}
