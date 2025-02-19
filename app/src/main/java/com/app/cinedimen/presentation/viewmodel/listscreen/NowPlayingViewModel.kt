package com.app.cinedimen.presentation.viewmodel.listscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.domain.usecases.listscreen.NowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val nowPlayingUseCase: NowPlayingUseCase) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<NowPlayingModel>()
    val nowPlayingMovies: LiveData<NowPlayingModel> = _nowPlayingMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    suspend fun getMoviesNowPlaying(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = nowPlayingUseCase.getMoviesNowPlaying()
                if (response.isSuccessful && response.body() != null) {
                    _nowPlayingMovies.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Sem filmes"
                }
                _nowPlayingMovies.value = nowPlayingUseCase.getMoviesNowPlaying().body()
            }catch (e: Exception){
                _errorMessage.value = "ERRO: ${e.message}"

            } finally {
                _isLoading.value = false
            }
        }

   }
}