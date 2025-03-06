package com.app.cinedimen.data.repositoriesImpl.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.UpComingGET
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesUpComing
import retrofit2.Response

class RepositoriesImplUpComing(private val upComingGET: UpComingGET) : RepositoriesUpComing {
    override suspend fun getUpComingMovies() : Response<NowPlayingModel>{
        return upComingGET.getUpComing()
    }
}