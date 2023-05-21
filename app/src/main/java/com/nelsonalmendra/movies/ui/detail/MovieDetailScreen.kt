package com.nelsonalmendra.movies.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = movie.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier
                    .widthIn(min = 200.dp)
                    .padding(end = 8.dp),
                model = movie.poster,
                contentDescription = "poster",
                contentScale = FillWidth
            )
            Text(
                text = movie.plot,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        DisplayYearAndGenre(movie)
        DisplayCast(movie)
        DisplayAwards(movie)
        DisplayImdb(movie)
    }
}

@Composable
private fun DisplayYearAndGenre(movie: Movie) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = movie.year,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.padding(end = 8.dp))
        Text(
            text = movie.genre,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun DisplayCast(movie: Movie) {
    Card(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Cast",
            fontSize = 20.sp
        )
        Text(
            text = movie.actors,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp
        )
        Text(
            text = movie.director,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp
        )
    }
}

@Composable
private fun DisplayAwards(movie: Movie) {
    Card(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = "Awards",
            fontSize = 20.sp
        )
        Text(
            text = movie.awards,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayImdb(movie: Movie) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/${movie.imdbID}")) }

    Card(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        onClick = { context.startActivity(intent) }
    ) {
        Text(
            text = "IMDB: ${movie.imdbRating}",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp
        )
    }
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
