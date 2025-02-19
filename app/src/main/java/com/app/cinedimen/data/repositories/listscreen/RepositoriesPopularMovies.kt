package com.app.cinedimen.data.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.PopularGET
import retrofit2.Response

class RepositoriesPopularMovies(private val popularGET: PopularGET) {
    suspend fun getPopularMovies() : Response<NowPlayingModel>{
        return popularGET.getPopularMovies()
    }
}