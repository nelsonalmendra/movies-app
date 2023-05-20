package com.nelsonalmendra.movies.data

import com.nelsonalmendra.movies.api.MoviesService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val service: MoviesService
): MoviesRepository {

    override fun searchByText(text: String) = flow {
        emit(service.searchMovies(s = text).movies)
    }

    override fun searchById(id: String) = flow {
        emit(service.getMovie(i = id))
    }
}
