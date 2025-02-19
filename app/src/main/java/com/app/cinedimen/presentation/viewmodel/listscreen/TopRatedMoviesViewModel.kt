package com.app.cinedimen.presentation.viewmodel.listscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.domain.usecases.listscreen.TopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(private val topRatedUseCase: TopRatedUseCase) : ViewModel() {
    private val _topRated = MutableLiveData<NowPlayingModel>()
    val topRated: LiveData<NowPlayingModel> = _topRated

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    suspend fun getTopRated() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = topRatedUseCase.getTopRatedMovies()
                if (response.isSuccessful && response.body() != null) {
                    _topRated.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Sem filmes"
                }
                _topRated.value = topRatedUseCase.getTopRatedMovies().body()
            }catch (e: Exception){
                _errorMessage.value = "ERRO: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}