package com.nelsonalmendra.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nelsonalmendra.movies.ui.theme.MoviesappTheme
import com.nelsonalmendra.movies.viewmodels.MoviesViewModel
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
                    val navController = rememberNavController()
                    MoviesNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun MoviesNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen {
                navController.navigate("search")
            }
        }
        composable("search") { SearchScreen() }
    }
}

@Composable
fun HomeScreen(onButtonClick: () -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        OutlinedButton (
            onClick = onButtonClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                modifier = Modifier.defaultMinSize(),
                contentDescription = "search")

            Text(
                text = "Search for a movie..."
            )
        }
    }
}

@Composable
fun SearchScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        val searchText = remember { mutableStateOf(TextFieldValue()) }
        TextField(value = searchText.value, onValueChange = {
            searchText.value = it
        })
    }
}

@Composable
fun Greeting(
    name: String,
    viewModel: MoviesViewModel = hiltViewModel()) {
//    viewModel.test()
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesappTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            HomeScreen()
            SearchScreen()
        }
    }
}
