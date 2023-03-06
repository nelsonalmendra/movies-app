package com.nelsonalmendra.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nelsonalmendra.movies.domain.SearchByIdUseCase
import com.nelsonalmendra.movies.domain.SearchByTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val searchByTextUseCase: SearchByTextUseCase,
    private val searchByIdUseCase: SearchByIdUseCase
): ViewModel() {

}
