package com.nelsonalmendra.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nelsonalmendra.movies.model.Movie
import com.nelsonalmendra.movies.ui.state.SearchUiState
import com.nelsonalmendra.movies.ui.theme.MoviesappTheme
import com.nelsonalmendra.movies.viewmodels.MoviesViewModel
import com.nelsonalmendra.movies_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val navController = rememberNavController()
//                    MoviesNavHost(navController)
                    SearchScreen()
                }
            }
        }
    }
}

@Composable
fun MoviesNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") { SearchScreen() }
    }
}

@Composable
fun SearchScreen(moviesViewModel: MoviesViewModel = hiltViewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        SearchBar()

        val searchUiState by moviesViewModel.searchUiState.collectAsState()
        when (searchUiState) {
            is SearchUiState.Success -> DisplayList(movies = (searchUiState as SearchUiState.Success).results)
            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel = hiltViewModel()
) {
    var value by rememberSaveable { mutableStateOf("") }
    TextField(
        value = value,
        onValueChange = {
            value = it
            moviesViewModel.search(it)
        },
        leadingIcon = {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = null
          )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

//@Preview(showBackground = true)
@Composable
fun DisplayList(
    modifier: Modifier = Modifier,
    movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = modifier.height(120.dp)
    ) {
        items(movies) {
            DisplayItem(it)
        }
    }
}

@Composable
fun DisplayItem(movie: Movie) {
    Text(
        text = movie.title,
        modifier = Modifier.height(56.dp)
    )
}
