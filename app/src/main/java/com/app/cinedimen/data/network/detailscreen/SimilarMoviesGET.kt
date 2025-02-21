package com.app.cinedimen.data.network.detailscreen

import com.app.cinedimen.BuildConfig
import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarMoviesGET {
    @GET("{movieId}/similar")
    suspend fun getSimilarMovies(
        @Header("Authorization") authHeader: String = "Bearer ${BuildConfig.API_KEY}",
        @Header("accept") accept: String = "application/json",
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "pt-BR",
    ) : Response<NowPlayingModel>
}