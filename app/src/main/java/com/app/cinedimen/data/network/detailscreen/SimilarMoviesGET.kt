package com.app.cinedimen.data.network.detailscreen

import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarMoviesGET {
    @GET("{movieId}/similar")
    suspend fun getSimilarMovies(
        @Header("Authorization") authHeader: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OTQ3MDExZjU4MDgxMTU1MTNjZTAwMDU5OTI2NzM2MiIsIm5iZiI6MTczOTg5MDYyNi40MjU5OTk5LCJzdWIiOiI2N2I0OWZjMjYxMTNjNDkzZGM2ZTA3ZjYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.VUg8CZb3C2RwLYU05qfkI_921bx6iVEMHM9NjGOBs8Q",
        @Header("accept") accept: String = "application/json",
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "pt-BR",
    ) : Response<NowPlayingModel>
}