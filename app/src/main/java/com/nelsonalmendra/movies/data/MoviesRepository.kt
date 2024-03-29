package com.nelsonalmendra.movies.data

import com.nelsonalmendra.movies.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun searchByText(text: String): Flow<List<Movie>>
    fun searchById(id: String): Flow<Movie>
}
