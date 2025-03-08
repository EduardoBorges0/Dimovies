package com.app.cinedimen.domain.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response

interface RepositoriesMainScreen {
    suspend fun getMoviesNowPlaying() : Response<NowPlayingModel>

    suspend fun getPopularMovies() : Response<NowPlayingModel>

    suspend fun getTopRatedMovies() : Response<NowPlayingModel>

    suspend fun getUpComingMovies() : Response<NowPlayingModel>
}