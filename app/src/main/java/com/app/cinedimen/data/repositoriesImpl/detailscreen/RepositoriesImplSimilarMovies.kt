package com.app.cinedimen.data.repositoriesImpl.detailscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.detailscreen.SimilarMoviesGET
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesSimilarMovies
import retrofit2.Response

class RepositoriesImplSimilarMovies (private val similarMoviesGET: SimilarMoviesGET) : RepositoriesSimilarMovies {
    override suspend fun similarMovies(movieId: Int) : Response<NowPlayingModel>{
        return similarMoviesGET.getSimilarMovies(movieId = movieId)
    }
}