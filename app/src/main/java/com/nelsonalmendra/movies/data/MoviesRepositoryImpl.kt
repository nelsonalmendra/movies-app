package com.nelsonalmendra.movies.data

import com.nelsonalmendra.movies.api.MoviesService
import com.nelsonalmendra.movies.model.Movie
import com.nelsonalmendra.movies.model.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val service: MoviesService
): MoviesRepository {

    override fun searchByText(text: String): Flow<Search> = flow {
        emit(service.searchMovies(s = text))
    }

    override fun searchById(id: String): Flow<Movie> = flow {
        emit(service.getMovie(i = id))
    }
}
