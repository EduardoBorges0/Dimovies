package com.app.cinedimen.presentation.viewmodel.listscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.domain.usecases.listscreen.UpComingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(private val upComingUseCase: UpComingUseCase) : ViewModel() {
    private val _upComingMovies = MutableLiveData<NowPlayingModel>()
    val upComingMovies: LiveData<NowPlayingModel> = _upComingMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    suspend fun getUpComingMovies(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = upComingUseCase.getUpComing()
                if (response.isSuccessful && response.body() != null) {
                    _upComingMovies.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Sem filmes"
                }
                _upComingMovies.value = upComingUseCase.getUpComing().body()
            }catch (e: Exception){
                _errorMessage.value = "ERRO: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}