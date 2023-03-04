package com.nelsonalmendra.movies.domain

import com.nelsonalmendra.movies.data.MoviesRepository
import com.nelsonalmendra.movies.model.Movie
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class SearchByIdUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(id: String): Flow<Movie> {
        Timber.d("Search by id: $id")
        return repository.searchById(id)
    }
}
