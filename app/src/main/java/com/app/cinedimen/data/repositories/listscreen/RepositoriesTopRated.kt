package com.app.cinedimen.data.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.TopRatedGET
import retrofit2.Response

class RepositoriesTopRated(private val topRatedGET: TopRatedGET) {
  suspend fun getTopRatedMovies() : Response<NowPlayingModel>{
      return topRatedGET.getTopRatedMovies()
  }
}