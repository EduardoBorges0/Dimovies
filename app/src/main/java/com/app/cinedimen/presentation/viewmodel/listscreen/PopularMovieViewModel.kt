package com.app.cinedimen.presentation.viewmodel.listscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.domain.usecases.listscreen.PopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val popularUseCase: PopularUseCase) : ViewModel(){
    private val _popularMovies = MutableLiveData<NowPlayingModel>()
    val popularMovies: LiveData<NowPlayingModel> = _popularMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    suspend fun getPopularMovies(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = popularUseCase.getPopularMovies()
                if (response.isSuccessful && response.body() != null) {
                    _popularMovies.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Sem filmes"
                }
                _popularMovies.value = popularUseCase.getPopularMovies().body()
            }catch (e: Exception){
                _errorMessage.value = "ERRO: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}