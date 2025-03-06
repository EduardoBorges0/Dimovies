package com.app.cinedimen.data.repositoriesImpl.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.TopRatedGET
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesTopRated
import retrofit2.Response

class RepositoriesImplTopRated(private val topRatedGET: TopRatedGET) : RepositoriesTopRated {
  override suspend fun getTopRatedMovies() : Response<NowPlayingModel>{
      return topRatedGET.getTopRatedMovies()
  }
}