package com.app.cinedimen.domain.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response

interface RepositoriesNowPlaying {
    suspend fun getMoviesNowPlaying() : Response<NowPlayingModel>
}