package com.efecanbayat.movieapplication.ui.feature.persondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efecanbayat.movieapplication.data.model.response.persondetail.Cast
import com.efecanbayat.movieapplication.data.model.response.persondetail.PersonDetailResponse
import com.efecanbayat.movieapplication.data.repository.MovieRepository
import com.efecanbayat.movieapplication.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PersonDetailViewState())
    val uiState: StateFlow<PersonDetailViewState> = _uiState.asStateFlow()

    fun getPerson(personId: Int) {
        viewModelScope.launch {
            repository.getPerson(personId).collectLatest { response ->
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
                        getPersonCredits(personId)
                        _uiState.update {
                            it.copy(
                                person = response.data,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getPersonCredits(personId: Int) {
        viewModelScope.launch {
            repository.getPersonCredits(personId).collectLatest { response ->
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
                                personCredits = CreditsData(response.data.cast),
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

data class PersonDetailViewState(
    val person: PersonDetailResponse? = null,
    val personCredits: CreditsData? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)

data class CreditsData(val creditsItem: ArrayList<Cast?>?)