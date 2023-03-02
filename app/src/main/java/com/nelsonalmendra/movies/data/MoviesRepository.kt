package com.nelsonalmendra.movies.data

import com.nelsonalmendra.movies.api.MoviesService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val service: MoviesService) {

    fun search(text: String) = flow {
        emit(service.getMovies(i = text))
    }
}
