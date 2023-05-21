package com.nelsonalmendra.movies.ui.state

import com.nelsonalmendra.movies.model.Movie

sealed interface SearchUiState {
    data class Success(val results: List<Movie>): SearchUiState
    data class Error(val message: String): SearchUiState
    object Loading: SearchUiState
}
