package com.efecanbayat.movieapplication.ui.feature.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efecanbayat.movieapplication.data.model.response.moviedetail.Cast
import com.efecanbayat.movieapplication.data.model.response.moviedetail.MovieDetailResponse
import com.efecanbayat.movieapplication.data.repository.MovieRepository
import com.efecanbayat.movieapplication.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailViewState())
    val uiState: StateFlow<MovieDetailViewState> = _uiState.asStateFlow()

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            repository.getMovie(movieId).collectLatest { response ->
                when (response) {
                    is DataState.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = response.apiError?.message,
                                isLoading = false
                            )
                        }
                    }
                    is DataState.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is DataState.Success -> {
                        getMovieCredits(movieId)
                        getMovieVideos(movieId)
                        _uiState.update {
                            it.copy(
                                movie = response.data,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getMovieCredits(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieCredits(movieId).collectLatest { response ->
                when (response) {
                    is DataState.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = response.apiError?.message,
                                isLoading = false
                            )
                        }
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.Success -> {
                        _uiState.update {
                            it.copy(
                                movieCredits = CastData(response.data.cast),
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getMovieVideos(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieVideos(movieId).collectLatest { response ->
                when (response) {
                    is DataState.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = response.apiError?.message,
                                isLoading = false
                            )
                        }
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.Success -> {
                        _uiState.update {
                            it.copy(
                                movieVideo = response.data.results?.firstOrNull()?.key,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }
}

data class MovieDetailViewState(
    val movie: MovieDetailResponse? = null,
    val movieCredits: CastData? = null,
    val movieVideo: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)

data class CastData(val castItem: ArrayList<Cast?>?)
