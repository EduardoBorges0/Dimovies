package com.app.cinedimen.data.repositoriesImpl.detailscreen

import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.network.detailscreen.MoviesDetailsGET
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesMovieDetails
import retrofit2.Response

class RepositoriesImplMovieDetails(private val moviesDetails: MoviesDetailsGET) : RepositoriesMovieDetails {
    override suspend fun getMovieDetails(id: Int) : Response<MoviesDetails>{
        return moviesDetails.getMovieDetails(id = id)
    }
}