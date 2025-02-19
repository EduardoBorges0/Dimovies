package com.app.cinedimen.domain.usecases.detailscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesSimilarMovies
import retrofit2.Response
import javax.inject.Inject

class SimilarMoviesUseCase @Inject constructor(private val repositoriesSimilarMovies: RepositoriesSimilarMovies) {
    suspend fun getSimilarMovies(movieId: Int) : Response<NowPlayingModel> {
        return repositoriesSimilarMovies.similarMovies(movieId)
    }
}