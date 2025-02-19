package com.app.cinedimen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.runtime.LaunchedEffect
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.NowPlayingViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.PopularMovieViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.TopRatedMoviesViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.UpComingViewModel
import com.app.cinedimen.ui.theme.CineDimenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val nowPlayingViewModel : NowPlayingViewModel by viewModels()
    private val topRatedMoviesViewModel : TopRatedMoviesViewModel by viewModels()
    private val upComingViewModel : UpComingViewModel by viewModels()
    private val popularMovieViewModel: PopularMovieViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LaunchedEffect(Unit) {
                movieDetailsViewModel.getMovieDetails(9)
            }
            CineDimenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding), movieDetailsViewModel
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    movieDetailsViewModel: MovieDetailsViewModel
) {
    val movieDetails = movieDetailsViewModel.movieDetails.observeAsState()
    val isLoading = movieDetailsViewModel.isLoading.observeAsState(false)
    val errorMessage = movieDetailsViewModel.errorMessage.observeAsState()

    if (isLoading.value) {
        Text(text = "Carregando...", modifier = modifier)
    } else {
        errorMessage.value?.let {
            Text(text = "Erro: $it", modifier = modifier)
        } ?: run {
            movieDetails.value?.let {
                Text(
                    text = it.toString(),
                    modifier = modifier
                )
            } ?: Text(text = "Nenhum filme encontrado", modifier = modifier)
        }
    }
}
