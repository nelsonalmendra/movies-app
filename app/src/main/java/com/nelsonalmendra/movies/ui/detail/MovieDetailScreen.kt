package com.nelsonalmendra.movies.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nelsonalmendra.movies.model.Movie
import com.nelsonalmendra.movies.ui.state.MovieUiState
import com.nelsonalmendra.movies.viewmodels.MoviesDetailViewModel

@Composable
fun MovieDetailScreen(
    moviesDetailViewModel: MoviesDetailViewModel = hiltViewModel(),
) {
    val movieDetailsUiState by moviesDetailViewModel.movieDetails.collectAsState()
    when (val state = movieDetailsUiState) {
        is MovieUiState.Success -> DisplayMovieDetails(state.movie)
        is MovieUiState.Error -> DisplayMessage(state.message)
        else -> DisplayLoading()
    }
}

@Composable
fun DisplayMovieDetails(movie: Movie) {
    Text(text = movie.title)
}

@Composable
fun DisplayLoading() {
    CircularProgressIndicator(
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun DisplayMessage(message: String = "No Results") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message
        )
    }
}
