package com.app.cinedimen.data.repositories.detailscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.detailscreen.SimilarMoviesGET
import retrofit2.Response
import javax.inject.Inject

class RepositoriesSimilarMovies (private val similarMoviesGET: SimilarMoviesGET) {
    suspend fun similarMovies(movieId: Int) : Response<NowPlayingModel>{
        return similarMoviesGET.getSimilarMovies(movieId = movieId)
    }
}