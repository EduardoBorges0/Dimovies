package com.app.cinedimen.data.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.NowPlayingGET
import retrofit2.Response

class RepositoriesNowPlaying(private val nowPlayingGET: NowPlayingGET) {
    suspend fun getMoviesNowPlaying() : Response<NowPlayingModel>{
        return nowPlayingGET.getNowPlayingMovies()
    }
}