package com.app.cinedimen.domain.repositories.detailscreen

import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response

interface RepositoriesSimilarMovies {
    suspend fun similarMovies(movieId: Int) : Response<NowPlayingModel>
}