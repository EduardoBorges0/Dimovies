package com.app.cinedimen.domain.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response

interface RepositoriesPopularMovies {
    suspend fun getPopularMovies() : Response<NowPlayingModel>
}