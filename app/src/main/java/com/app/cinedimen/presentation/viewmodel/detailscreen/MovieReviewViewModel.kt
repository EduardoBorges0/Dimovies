package com.app.cinedimen.presentation.viewmodel.detailscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cinedimen.data.model.MovieReviewModel
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieReviewViewModel @Inject constructor(private val repositoriesMovieReview: RepositoriesMovieReview) : ViewModel() {
    private val _movieReview = MutableLiveData<MovieReviewModel>()
    val movieReview: LiveData<MovieReviewModel> = _movieReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    suspend fun getMovieReview(movieId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repositoriesMovieReview.getMovieReviews(movieId)
                if (response.isSuccessful && response.body() != null) {
                    _movieReview.value = response.body()
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