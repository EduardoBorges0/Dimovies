package com.app.cinedimen.presentation.viewmodel.listscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.listscreen.RepositoriesUpComing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(private val repositoriesUpComing: RepositoriesUpComing) : ViewModel() {
    private val _upComingMovies = MutableLiveData<NowPlayingModel>()
    val upComingMovies: LiveData<NowPlayingModel> = _upComingMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getUpComingMovies(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repositoriesUpComing.getUpComingMovies()
                if (response.isSuccessful && response.body() != null) {
                    _upComingMovies.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Sem filmes"
                }else {
                    _errorMessage.value = "Erro ${response.code()}: ${response.message()}"
                }
            } catch (e: IOException) {
                _errorMessage.value = "Erro de conexão: verifique sua internet."
            } catch (e: HttpException) {
                _errorMessage.value = "Erro HTTP ${e.code()}: ${e.message()}"
            } catch (e: Exception) {
                _errorMessage.value = "Erro inesperado: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}