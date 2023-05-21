package com.nelsonalmendra.movies.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nelsonalmendra.movies.domain.SearchByIdUseCase
import com.nelsonalmendra.movies.ui.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchByIdUseCase: SearchByIdUseCase
): ViewModel() {

    private val imdbID = savedStateHandle.get<String>("imdbID")!!

    val movieDetails: StateFlow<MovieUiState> = searchByIdUseCase(imdbID)
        .map { MovieUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieUiState.Loading)
}
