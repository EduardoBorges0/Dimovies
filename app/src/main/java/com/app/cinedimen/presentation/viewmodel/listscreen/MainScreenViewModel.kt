package com.app.cinedimen.presentation.viewmodel.listscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesNowPlaying
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesPopularMovies
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesTopRated
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesUpComing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repositoriesNowPlaying: RepositoriesNowPlaying,
    private val repositoriesPopularMovies: RepositoriesPopularMovies,
    private val repositoriesTopRated: RepositoriesTopRated,
    private val repositoriesUpComing: RepositoriesUpComing,
) :
    ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<NowPlayingModel>()
    val nowPlayingMovies: LiveData<NowPlayingModel> = _nowPlayingMovies

    private val _popularMovies = MutableLiveData<NowPlayingModel>()
    val popularMovies: LiveData<NowPlayingModel> = _popularMovies

    private val _topRated = MutableLiveData<NowPlayingModel>()
    val topRated: LiveData<NowPlayingModel> = _topRated

    private val _upComingMovies = MutableLiveData<NowPlayingModel>()
    val upComingMovies: LiveData<NowPlayingModel> = _upComingMovies


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getMoviesNowPlaying() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repositoriesNowPlaying.getMoviesNowPlaying()
                if (response.body()?.results?.isNullOrEmpty() == true) {
                    _errorMessage.value = "Sem filmes"
                } else if (response.isSuccessful && response.body() != null) {
                    _nowPlayingMovies.value = response.body()
                } else {
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

    fun getPopularMovies() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repositoriesPopularMovies.getPopularMovies()
                if (response.isSuccessful && response.body() != null) {
                    _popularMovies.value = response.body()
                } else if (response.body() == null) {
                    _errorMessage.value = "Sem filmes"
                } else {
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

    fun getTopRated() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repositoriesTopRated.getTopRatedMovies()
                if (response.isSuccessful && response.body() != null) {
                    _topRated.value = response.body()
                } else if (response.body() == null) {
                    _errorMessage.value = "Sem filmes"
                } else {
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

    fun getUpComingMovies() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repositoriesUpComing.getUpComingMovies()
                if (response.isSuccessful && response.body() != null) {
                    _upComingMovies.value = response.body()
                } else if (response.body() == null) {
                    _errorMessage.value = "Sem filmes"
                } else {
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