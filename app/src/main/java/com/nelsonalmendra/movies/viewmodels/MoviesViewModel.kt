package com.nelsonalmendra.movies.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nelsonalmendra.movies.domain.SearchByIdUseCase
import com.nelsonalmendra.movies.domain.SearchByTextUseCase
import com.nelsonalmendra.movies.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchByTextUseCase: SearchByTextUseCase,
    private val searchByIdUseCase: SearchByIdUseCase
): ViewModel() {

    val movies: Flow<List<Movie>> =
        searchByTextUseCase(savedStateHandle["query"] ?: "")

}
