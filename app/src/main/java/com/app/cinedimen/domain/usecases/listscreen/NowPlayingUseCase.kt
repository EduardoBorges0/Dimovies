package com.app.cinedimen.domain.usecases.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.listscreen.RepositoriesNowPlaying
import retrofit2.Response
import javax.inject.Inject

class NowPlayingUseCase @Inject constructor(private val repositoriesNowPlaying: RepositoriesNowPlaying) {

    suspend fun getMoviesNowPlaying() : Response<NowPlayingModel>{
        return repositoriesNowPlaying.getMoviesNowPlaying()
    }
}