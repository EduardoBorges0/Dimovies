package com.app.cinedimen.data.repositoriesImpl.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.MainScreenNetwork
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesMainScreen
import retrofit2.Response

class RepositoriesImplMainScreen(private val mainScreenNetwork: MainScreenNetwork) :
    RepositoriesMainScreen {
    override suspend fun getMoviesNowPlaying() : Response<NowPlayingModel>{
        return mainScreenNetwork.getMovies("now_playing")
    }
    override suspend fun getPopularMovies() : Response<NowPlayingModel>{
        return mainScreenNetwork.getMovies("popular")
    }
    override suspend fun getTopRatedMovies() : Response<NowPlayingModel>{
        return mainScreenNetwork.getMovies("top_rated")
    }
    override suspend fun getUpComingMovies() : Response<NowPlayingModel>{
        return mainScreenNetwork.getMovies("upcoming")
    }
}