package com.app.cinedimen.di

import com.app.cinedimen.data.network.detailscreen.MoviesDetailsGET
import com.app.cinedimen.data.network.listscreen.NowPlayingGET
import com.app.cinedimen.data.network.listscreen.PopularGET
import com.app.cinedimen.data.network.listscreen.TopRatedGET
import com.app.cinedimen.data.network.listscreen.UpComingGET
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideNowPlaying(retrofit: Retrofit): NowPlayingGET {
        return retrofit.create(NowPlayingGET::class.java)
    }

    @Provides
    @Singleton
    fun provideTopRated(retrofit: Retrofit): TopRatedGET {
        return retrofit.create(TopRatedGET::class.java)
    }

    @Provides
    @Singleton
    fun provideUpComing(retrofit: Retrofit): UpComingGET {
        return retrofit.create(UpComingGET::class.java)
    }

    @Provides
    @Singleton
    fun providePopularMovies(retrofit: Retrofit): PopularGET {
        return retrofit.create(PopularGET::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetails(retrofit: Retrofit) : MoviesDetailsGET{
        return retrofit.create(MoviesDetailsGET::class.java)
    }
}
