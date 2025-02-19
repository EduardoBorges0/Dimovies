package com.app.cinedimen.domain.usecases.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.listscreen.RepositoriesTopRated
import retrofit2.Response
import javax.inject.Inject

class TopRatedUseCase @Inject constructor(private val repositoriesTopRated: RepositoriesTopRated) {
    suspend fun getTopRatedMovies() : Response<NowPlayingModel>{
        return repositoriesTopRated.getTopRatedMovies()
    }
}