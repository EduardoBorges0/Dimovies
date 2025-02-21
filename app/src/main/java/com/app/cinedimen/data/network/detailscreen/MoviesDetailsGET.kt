package com.app.cinedimen.data.network.detailscreen

import com.app.cinedimen.BuildConfig
import com.app.cinedimen.data.model.MoviesDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDetailsGET {
    @GET("{id}")
    suspend fun getMovieDetails(
        @Header("Authorization") authHeader: String = "Bearer ${BuildConfig.API_KEY}",
        @Header("accept") accept: String = "application/json",
        @Path("id") id: Int,
        @Query("language") language: String = "pt-BR",
    ) : Response<MoviesDetails>
}