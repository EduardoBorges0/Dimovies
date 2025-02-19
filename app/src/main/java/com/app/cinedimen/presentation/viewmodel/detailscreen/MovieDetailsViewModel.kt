package com.app.cinedimen.presentation.viewmodel.detailscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.domain.usecases.detailscreen.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsUseCase: MovieDetailsUseCase) : ViewModel() {

    private val _movieDetails = MutableLiveData<MoviesDetails>()
    val movieDetails: LiveData<MoviesDetails> = _movieDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getMovieDetails(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = movieDetailsUseCase.getMovieDetails(id)
                if (response.isSuccessful && response.body() != null) {
                    _movieDetails.value = response.body()
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
