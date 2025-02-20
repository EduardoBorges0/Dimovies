package com.app.cinedimen.presentation.navigations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.activity.viewModels

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.cinedimen.presentation.view.detailscreen.MovieDetailsContent
import com.app.cinedimen.presentation.view.mainScreen.MainScreen
import com.app.cinedimen.presentation.view.splashScreen.SplashScreen
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieReviewViewModel
import com.app.cinedimen.presentation.viewmodel.detailscreen.SimilarMoviesViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.NowPlayingViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.PopularMovieViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.TopRatedMoviesViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.UpComingViewModel
import com.app.cinedimen.presentation.view.ui.theme.CineDimenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationsActivity : ComponentActivity() {
    private val nowPlayingViewModel : NowPlayingViewModel by viewModels()
    private val topRatedMoviesViewModel : TopRatedMoviesViewModel by viewModels()
    private val upComingViewModel : UpComingViewModel by viewModels()
    private val popularMovieViewModel: PopularMovieViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val movieReviewViewModel : MovieReviewViewModel by viewModels()
    private val similarMoviesViewModel: SimilarMoviesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                nowPlayingViewModel.getMoviesNowPlaying()
                upComingViewModel.getUpComingMovies()
                popularMovieViewModel.getPopularMovies()
                topRatedMoviesViewModel.getTopRated()
            }
            CineDimenTheme {
                    NavigationsRoutes(
                        nowPlayingViewModel = nowPlayingViewModel,
                        popularMovieViewModel = popularMovieViewModel,
                        topRatedMoviesViewModel = topRatedMoviesViewModel,
                        upComingViewModel = upComingViewModel,
                        movieDetailsViewModel = movieDetailsViewModel,
                        movieReviewViewModel = movieReviewViewModel,
                        similarMoviesViewModel = similarMoviesViewModel
                    )
            }
        }
    }
}
@Composable
fun NavigationsRoutes(
    nowPlayingViewModel: NowPlayingViewModel,
    popularMovieViewModel: PopularMovieViewModel,
    topRatedMoviesViewModel: TopRatedMoviesViewModel,
    upComingViewModel: UpComingViewModel,
    movieDetailsViewModel: MovieDetailsViewModel,
    movieReviewViewModel: MovieReviewViewModel,
    similarMoviesViewModel: SimilarMoviesViewModel
) {
   val navController = rememberNavController()
   NavHost(navController = navController, startDestination = "splash"){
       composable("splash"){
           SplashScreen(navController)
       }
       composable("movieList") {
           MainScreen(
               navController = navController,
               nowPlayingViewModel = nowPlayingViewModel,
               popularMovieViewModel = popularMovieViewModel,
               topRatedMoviesViewModel = topRatedMoviesViewModel,
               upComingViewModel = upComingViewModel
           )
       }
       composable("movieDetails/{movieId}") { backStackEntry ->
           val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
           movieDetailsViewModel.getMovieDetails(movieId.toInt())
           movieReviewViewModel.getMovieReview(movieId = movieId.toInt())
           similarMoviesViewModel.getSimilarMovies(movieId = movieId.toInt())

           MovieDetailsContent(movieDetailsViewModel = movieDetailsViewModel,
               movieReviewViewModel = movieReviewViewModel,
               movieSimilarMoviesViewModel = similarMoviesViewModel,
               navController = navController,
               movieId = movieId.toInt()
           )
       }
   }
}
