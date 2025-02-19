package com.app.cinedimen.domain.usecases.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.listscreen.RepositoriesPopularMovies
import retrofit2.Response
import javax.inject.Inject

class PopularUseCase @Inject constructor(private val repositoriesPopularMovies: RepositoriesPopularMovies){
    suspend fun getPopularMovies() : Response<NowPlayingModel> {
        return repositoriesPopularMovies.getPopularMovies()
    }
}