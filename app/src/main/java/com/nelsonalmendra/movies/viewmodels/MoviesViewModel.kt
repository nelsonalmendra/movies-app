package com.nelsonalmendra.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nelsonalmendra.movies.domain.SearchByIdUseCase
import com.nelsonalmendra.movies.domain.SearchByTextUseCase
import com.nelsonalmendra.movies.ui.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val searchByTextUseCase: SearchByTextUseCase,
    private val searchByIdUseCase: SearchByIdUseCase
): ViewModel() {

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Success(emptyList()))
    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    fun search(text: String) {
        if (text.length < 3) {
            _searchUiState.value = SearchUiState.Error("No results (min 3 characters)")
            return
        }

        _searchUiState.value = SearchUiState.Loading
        viewModelScope.launch {
            searchByTextUseCase(text).collect { movies ->
                if (movies.isEmpty()) {
                    _searchUiState.value = SearchUiState.Error("No results")
                } else {
                    _searchUiState.value = SearchUiState.Success(movies)
                }
            }
        }
    }
}
