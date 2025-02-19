package com.app.cinedimen.data.network.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NowPlayingGET {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") authHeader: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OTQ3MDExZjU4MDgxMTU1MTNjZTAwMDU5OTI2NzM2MiIsIm5iZiI6MTczOTg5MDYyNi40MjU5OTk5LCJzdWIiOiI2N2I0OWZjMjYxMTNjNDkzZGM2ZTA3ZjYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.VUg8CZb3C2RwLYU05qfkI_921bx6iVEMHM9NjGOBs8Q",
        @Header("accept") accept: String = "application/json",
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): Response<NowPlayingModel>

}