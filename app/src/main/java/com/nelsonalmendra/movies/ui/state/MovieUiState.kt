package com.nelsonalmendra.movies.ui.state

import com.nelsonalmendra.movies.model.Movie

sealed interface MovieUiState {
    data class Success(val movie: Movie): MovieUiState
    data class Error(val message: String): MovieUiState
    object Loading: MovieUiState
}
