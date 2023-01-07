package aej.dino.netflixcloneapps.ui.screen.home

import aej.dino.netflixcloneapps.ui.MovieViewModel
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.component.MovieSearchField
import aej.dino.netflixcloneapps.ui.screen.list.MovieGridScreen
import aej.dino.netflixcloneapps.ui.screen.list.MovieListScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
  navHostController: NavHostController,
  viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MovieViewModel.Factory)
) {
  val movies by viewModel.movies.collectAsState()

  var isGrid by remember { mutableStateOf(false) }
  var keyword by remember { mutableStateOf("") }

  LaunchedEffect("") { viewModel.getMovies() }
  LaunchedEffect(keyword) { viewModel.searchMovie(keyword) }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      Column(modifier = Modifier.fillMaxWidth()) {
        MovieAppBar(onViewChange = { isGrid = it })
        MovieSearchField(
          keyword,
          onTextChange = {
            keyword = it
          },
          Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(horizontal = 16.dp)
        )
      }
    }
  ) { contentPadding ->
    if (isGrid) MovieGridScreen(contentPadding, movies, navHostController)
    else MovieListScreen(contentPadding, movies, navHostController)
  }
}