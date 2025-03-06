package com.app.cinedimen.domain.repositories.detailscreen

import com.app.cinedimen.data.model.MoviesDetails
import retrofit2.Response

interface RepositoriesMovieDetails {
    suspend fun getMovieDetails(id: Int) : Response<MoviesDetails>
}