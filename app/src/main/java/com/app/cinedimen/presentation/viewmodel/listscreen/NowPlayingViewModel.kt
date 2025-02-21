package com.app.cinedimen.presentation.viewmodel.listscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.listscreen.RepositoriesNowPlaying
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val repositoriesNowPlaying: RepositoriesNowPlaying) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<NowPlayingModel>()
    val nowPlayingMovies: LiveData<NowPlayingModel> = _nowPlayingMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getMoviesNowPlaying(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repositoriesNowPlaying.getMoviesNowPlaying()
                if(response.body()?.results?.isNullOrEmpty() == true){
                    _errorMessage.value = "Sem filmes"
                }
                else if (response.isSuccessful && response.body() != null) {
                    _nowPlayingMovies.value = response.body()
                }else {
                    _errorMessage.value = response.code().toString()
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