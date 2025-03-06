package com.app.cinedimen.presentation.view.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.cinedimen.R
import com.app.cinedimen.presentation.view.components.AlertDialogError
import com.app.cinedimen.presentation.view.components.ListScreen
import com.app.cinedimen.presentation.view.ui.theme.BackgroundColor
import com.app.cinedimen.presentation.viewmodel.listscreen.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel
) {
    var showDialog by remember { mutableStateOf(true) }
    val nowPlayingMovies = mainScreenViewModel.nowPlayingMovies.observeAsState().value?.results ?: emptyList()
    val upComingMovies = mainScreenViewModel.upComingMovies.observeAsState().value?.results ?: emptyList()
    val popularMovies = mainScreenViewModel.popularMovies.observeAsState().value?.results ?: emptyList()
    val topRatedMovies = mainScreenViewModel.topRated.observeAsState().value?.results ?: emptyList()
    val isLoading = mainScreenViewModel.isLoading.observeAsState().value ?: false

    val errorMessageNowPlaying = mainScreenViewModel.errorMessage.observeAsState().value
    val errorMessageUpComing = mainScreenViewModel.errorMessage.observeAsState().value
    val errorMessagePopular = mainScreenViewModel.errorMessage.observeAsState().value
    val errorMessageTopRated = mainScreenViewModel.errorMessage.observeAsState().value


    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        AsyncImage(
            model = R.drawable.movieyou,
            contentDescription = "logo",
            modifier = Modifier
                .padding(top = screenHeight / 15)
                .size(70.dp)
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            ListScreen(navController = navController, movieSituation = "Em Exibição", movieList = nowPlayingMovies, modifier = Modifier.padding(bottom = 10.dp).padding(horizontal = 10.dp))
            ListScreen(navController = navController, movieSituation = "Em Breve", movieList = upComingMovies, modifier = Modifier.padding(bottom = 10.dp).padding(horizontal = 10.dp))
            ListScreen(navController = navController, movieSituation = "Mais Populares", movieList = popularMovies, modifier = Modifier.padding(bottom = 10.dp).padding(horizontal = 10.dp))
            ListScreen(navController = navController, movieSituation = "Mais Bem Avaliados", movieList = topRatedMovies, modifier = Modifier.padding(bottom = 10.dp).padding(horizontal = 10.dp))
        }

        if (!errorMessageNowPlaying.isNullOrEmpty() ||
            !errorMessagePopular.isNullOrEmpty() ||
            !errorMessageUpComing.isNullOrEmpty() ||
            !errorMessageTopRated.isNullOrEmpty()) {

            val errorMessage = listOfNotNull(
                errorMessageNowPlaying,
                errorMessagePopular,
                errorMessageUpComing,
                errorMessageTopRated
            ).joinToString("\n")
            if(showDialog){
                AlertDialogError(
                    confirmAction = {
                        mainScreenViewModel.getMoviesNowPlaying()
                        mainScreenViewModel.getTopRated()
                        mainScreenViewModel.getUpComingMovies()
                        mainScreenViewModel.getPopularMovies()
                        showDialog = false
                    },
                    mainMessage = errorMessage,
                )
            }
            showDialog = true

        }

    }
}
