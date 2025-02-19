package com.app.cinedimen.data.repositories.listscreen

import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.listscreen.UpComingGET
import retrofit2.Response

class RepositoriesUpComing(private val upComingGET: UpComingGET) {
    suspend fun getUpComingMovies() : Response<NowPlayingModel>{
        return upComingGET.getUpComing()
    }
}