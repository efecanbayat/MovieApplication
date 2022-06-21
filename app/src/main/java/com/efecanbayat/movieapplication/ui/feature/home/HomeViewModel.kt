package com.efecanbayat.movieapplication.ui.feature.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efecanbayat.movieapplication.data.model.HomeDataHolder
import com.efecanbayat.movieapplication.data.model.PopularMovieData
import com.efecanbayat.movieapplication.data.model.SearchedMovieData
import com.efecanbayat.movieapplication.data.model.SearchedPersonData
import com.efecanbayat.movieapplication.data.model.response.home.Result
import com.efecanbayat.movieapplication.data.model.response.search.SearchResult
import com.efecanbayat.movieapplication.data.repository.MovieRepository
import com.efecanbayat.movieapplication.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesViewState())
    val uiState: StateFlow<MoviesViewState> = _uiState.asStateFlow()

    val homeLiveData = MediatorLiveData<HomeDataHolder>().also {
        it.value = HomeDataHolder()
    }

    private val movieLiveData = MutableLiveData<PopularMovieData?>()
    private val searchMovieLiveData = MutableLiveData<SearchedMovieData?>()
    private val searchPersonLiveData = MutableLiveData<SearchedPersonData?>()

    init {
        getMovies()

        homeLiveData.addSource(movieLiveData) {
            homeLiveData.value = homeLiveData.value?.apply {
                popularMovieData = it
            }
        }

        homeLiveData.addSource(searchMovieLiveData) {
            homeLiveData.value = homeLiveData.value?.apply {
                searchedMovieData = it
            }
        }

        homeLiveData.addSource(searchPersonLiveData) {
            homeLiveData.value = homeLiveData.value?.apply {
                searchedPersonData = it
            }
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            repository.getMovies().collect { response ->
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
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is DataState.Success -> {
                        _uiState.update {
                            val prepareList = ArrayList<MovieItemViewType>().apply {
                                response.data.results?.map { result ->
                                    MovieItemViewType.MovieItem(
                                        result
                                    )
                                }?.let { a -> addAll(a) }
                            }
                            movieLiveData.value = PopularMovieData(prepareList)
                            it.copy(
                                movies = prepareList,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun searchMovieAndPerson(query: String?) {
        viewModelScope.launch {
            if (query.isNullOrEmpty()) {
                _uiState.let {
                    movieLiveData.value = PopularMovieData(it.value.movies)
                }
                searchMovieLiveData.value = null
                searchPersonLiveData.value = null
            } else {
                repository.searchMovieAndPerson(query).collect { response ->
                    when (response) {
                        is DataState.Error -> {
                            _uiState.update {
                                it.copy(
                                    errorMessage = response.apiError?.message
                                )
                            }
                            movieLiveData.value = null
                            searchMovieLiveData.value = SearchedMovieData(arrayListOf())
                            searchPersonLiveData.value = SearchedPersonData(arrayListOf())
                        }
                        is DataState.Loading -> {
                        }
                        is DataState.Success -> {
                            val searchedMovieList: ArrayList<MovieItemViewType> = arrayListOf()
                            val searchedPersonList: ArrayList<MovieItemViewType> = arrayListOf()

                            response.data.searchResults?.forEach { result ->
                                when (result?.mediaType) {
                                    MediaType.MOVIE -> searchedMovieList.add(
                                        MovieItemViewType.SearchMovieItem(
                                            result
                                        )
                                    )
                                    MediaType.PERSON -> searchedPersonList.add(
                                        MovieItemViewType.SearchPersonItem(
                                            result
                                        )
                                    )
                                }
                            }

                            if (searchedMovieList.isNotEmpty()) {
                                searchMovieLiveData.value = SearchedMovieData(searchedMovieList)
                                movieLiveData.value = null
                            } else {
                                searchMovieLiveData.value = SearchedMovieData(arrayListOf())
                            }

                            if (searchedPersonList.isNotEmpty()) {
                                searchPersonLiveData.value = SearchedPersonData(searchedPersonList)
                                movieLiveData.value = null
                            } else {
                                searchPersonLiveData.value = SearchedPersonData(arrayListOf())
                            }
                        }
                    }
                }
            }
        }
    }
}

data class MoviesViewState(
    val movies: ArrayList<MovieItemViewType>? = arrayListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)

sealed class MovieItemViewType {
    data class MovieItem(val movie: Result) : MovieItemViewType()
    data class SearchPersonItem(val person: SearchResult) : MovieItemViewType()
    data class SearchMovieItem(val movie: SearchResult) : MovieItemViewType()
}

object MediaType {
    const val MOVIE = "movie"
    const val PERSON = "person"
}