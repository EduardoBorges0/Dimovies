package com.app.cinedimen.data.network.detailscreen

import com.app.cinedimen.BuildConfig
import com.app.cinedimen.data.model.MovieReviewsResponse
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDetailsNetwork {
    @GET("{id}")
    suspend fun getMovieDetails(
        @Header("Authorization") authHeader: String = "Bearer ${BuildConfig.API_KEY}",
        @Header("accept") accept: String = "application/json",
        @Path("id") id: Int,
        @Query("language") language: String = "pt-BR",
    ) : Response<MoviesDetails>

    @GET("{movieId}/reviews")
    suspend fun getReviews(
        @Header("Authorization") authHeader: String = "Bearer ${BuildConfig.API_KEY}",
        @Header("accept") accept: String = "application/json",
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "pt-BR",
    ) : Response<MovieReviewsResponse>

    @GET("{movieId}/similar")
    suspend fun getSimilarMovies(
        @Header("Authorization") authHeader: String = "Bearer ${BuildConfig.API_KEY}",
        @Header("accept") accept: String = "application/json",
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "pt-BR",
    ) : Response<NowPlayingModel>
}