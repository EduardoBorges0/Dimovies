package com.app.cinedimen.domain.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response

interface RepositoriesTopRated {
    suspend fun getTopRatedMovies() : Response<NowPlayingModel>
}