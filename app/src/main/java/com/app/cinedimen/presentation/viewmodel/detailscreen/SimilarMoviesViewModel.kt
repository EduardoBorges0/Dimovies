package com.app.cinedimen.presentation.viewmodel.detailscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.domain.usecases.detailscreen.SimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(private val similarMoviesUseCase: SimilarMoviesUseCase) : ViewModel() {
    private val _similarMovie = MutableLiveData<NowPlayingModel>()
    val similarMovie: LiveData<NowPlayingModel> = _similarMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    suspend fun getSimilarMovies(movieId: Int){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = similarMoviesUseCase.getSimilarMovies(movieId = movieId)
                if (response.isSuccessful && response.body() != null) {
                    _similarMovie.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Filme não encontrado"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro ${e.message}"
            }
            finally {
                _isLoading.value = false
            }
        }
    }
}