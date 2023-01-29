package aej.dino.netflixcloneapps.ui.screen.dashboard.home

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.ui.MovieViewModel
import aej.dino.netflixcloneapps.ui.Routers
import aej.dino.netflixcloneapps.ui.component.ErrorUi
import aej.dino.netflixcloneapps.ui.component.LoadingUi
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.component.MovieItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@ExperimentalMaterial3Api @Composable fun HomeScreen(
  navHostController: NavHostController,
  viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MovieViewModel.Factory)
) {
  val homeState by viewModel.homeScreenState.collectAsState()

  var isGrid by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    viewModel.combineData()
    viewModel.getNowPlayingMovies()
    viewModel.getPopularMovies()
    viewModel.getUpcomingMovies()
  }

  Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Black, topBar = {
    MovieAppBar(onViewChange = { isGrid = it }, modifier = Modifier.fillMaxWidth())
  }) { contentPadding ->
    when (homeState) {
      is HomeScreenState.Success -> {
        val nowPlaying = (homeState as HomeScreenState.Success).nowPlayingMovies
        val popular = (homeState as HomeScreenState.Success).popularMovies
        val upcoming = (homeState as HomeScreenState.Success).upcomingMovies

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
        ) {
          MovieSection(
            title = stringResource(R.string.now_playing),
            movies = nowPlaying,
            navController = navHostController,
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp)
          )
          MovieSection(
            title = stringResource(R.string.popular),
            movies = popular,
            navController = navHostController,
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp)
          )
          MovieSection(
            title = stringResource(R.string.upcoming),
            movies = upcoming,
            navController = navHostController,
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp)
          )
          Spacer(modifier = Modifier.height(16.dp))
        }
      }

      is HomeScreenState.Loading -> {
        LoadingUi(
          modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
        )
      }

      is HomeScreenState.Error -> {
        val error = (homeState as HomeScreenState.Error)
        ErrorUi(message = error.message, onButtonClick = {
          when (error.whatError) {
            HomeScreenRequestEnum.NOW_PLAYING -> {
              viewModel.getNowPlayingMovies()
            }

            HomeScreenRequestEnum.POPULAR -> {
              viewModel.getPopularMovies()
            }

            HomeScreenRequestEnum.UP_COMING -> {
              viewModel.getUpcomingMovies()
            }
          }
        })
      }

      else -> Unit
    }
  }
}

@Composable fun MovieSection(
  title: String, movies: List<Movie>, navController: NavHostController, modifier: Modifier
) {
  Column(
    modifier = modifier
  ) {
    Text(
      text = title, style = MaterialTheme.typography.titleSmall.copy(
        color = Color.White
      ), modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    )
    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
      items(movies) { movie ->
        MovieItem(modifier = Modifier.width(150.dp),
          isGrid = true,
          movie = movie,
          onItemClick = { movie ->
            navController.navigate("${Routers.DETAIL}/${movie.id}")
          })
      }
    }
  }
}