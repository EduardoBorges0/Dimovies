package com.app.cinedimen.data.repositoriesImpl.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.PopularGET
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesPopularMovies
import retrofit2.Response

class RepositoriesImplPopularMovies(private val popularGET: PopularGET) :
    RepositoriesPopularMovies {
    override suspend fun getPopularMovies() : Response<NowPlayingModel>{
        return popularGET.getPopularMovies()
    }
}