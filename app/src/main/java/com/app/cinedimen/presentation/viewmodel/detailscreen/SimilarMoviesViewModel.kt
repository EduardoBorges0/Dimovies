package com.app.cinedimen.presentation.viewmodel.detailscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesSimilarMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(private val repositoriesSimilarMovies: RepositoriesSimilarMovies) : ViewModel() {
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
                val response = repositoriesSimilarMovies.similarMovies(movieId = movieId)
                if (response.isSuccessful && response.body() != null) {
                    _similarMovie.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Filme não encontrado"
                }else {
                    _errorMessage.value = "Erro ${response.code()}: ${response.message()}"
                }
            } catch (e: IOException) {  // Erros de conexão
                _errorMessage.value = "Erro de conexão: verifique sua internet."
            } catch (e: HttpException) { // Erros HTTP inesperados
                _errorMessage.value = "Erro HTTP ${e.code()}: ${e.message()}"
            } catch (e: Exception) { // Qualquer outro erro inesperado
                _errorMessage.value = "Erro inesperado: ${e.localizedMessage}"
            }
            finally {
                _isLoading.value = false
            }
        }
    }
}