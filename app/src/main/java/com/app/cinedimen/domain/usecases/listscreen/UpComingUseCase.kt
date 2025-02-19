package com.app.cinedimen.domain.usecases.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.listscreen.RepositoriesUpComing
import retrofit2.Response
import javax.inject.Inject

class UpComingUseCase @Inject constructor(private val repositoriesUpComing: RepositoriesUpComing) {
  suspend fun getUpComing() : Response<NowPlayingModel>{
      return repositoriesUpComing.getUpComingMovies()
  }
}