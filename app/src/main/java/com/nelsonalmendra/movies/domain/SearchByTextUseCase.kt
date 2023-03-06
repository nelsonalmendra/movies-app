package com.nelsonalmendra.movies.domain

import com.nelsonalmendra.movies.data.MoviesRepository
import com.nelsonalmendra.movies.model.Movie
import com.nelsonalmendra.movies.model.Search
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class SearchByTextUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(text: String): Flow<List<Movie>> {
        Timber.d("Search by text: $text")
        return repository.searchByText(text)
    }
}
