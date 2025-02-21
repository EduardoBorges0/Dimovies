package com.app.cinedimen.presentation.viewmodel.detailscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repositoriesMovieDetails: RepositoriesMovieDetails) : ViewModel() {

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
                val response = repositoriesMovieDetails.getMovieDetails(id)
                if (response.isSuccessful && response.body() != null) {
                    _movieDetails.value = response.body()
                }else if(response.body() == null){
                    _errorMessage.value = "Filme não encontrado"
                }else {
                    _errorMessage.value = "Erro ${response.code()}: ${response.message()}"
                }
            } catch (e: IOException) {
                _errorMessage.value = "Erro de conexão: verifique sua internet."
            } catch (e: HttpException) {
                _errorMessage.value = "Erro HTTP ${e.code()}: ${e.message()}"
            } catch (e: Exception) {
                _errorMessage.value = "Erro inesperado: ${e.localizedMessage}"
            }
            finally {
                _isLoading.value = false
            }
        }
    }
}
