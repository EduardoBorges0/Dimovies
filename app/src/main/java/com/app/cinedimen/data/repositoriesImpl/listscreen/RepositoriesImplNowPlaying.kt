package com.app.cinedimen.data.repositoriesImpl.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.NowPlayingGET
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesNowPlaying
import retrofit2.Response

class RepositoriesImplNowPlaying(private val nowPlayingGET: NowPlayingGET) :
    RepositoriesNowPlaying {
    override suspend fun getMoviesNowPlaying() : Response<NowPlayingModel>{
        return nowPlayingGET.getNowPlayingMovies()
    }
}