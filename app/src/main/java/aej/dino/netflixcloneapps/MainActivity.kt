package aej.dino.netflixcloneapps

import aej.dino.netflixcloneapps.ui.MovieViewModel
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.component.MovieSearchField
import aej.dino.netflixcloneapps.ui.screen.MovieGridScreen
import aej.dino.netflixcloneapps.ui.screen.MovieListScreen
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflixCloneAppsTheme { // A surface container using the 'background' color from the theme
                NetflixCloneApps()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NetflixCloneApps(
  viewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)
) {
    val movies by viewModel.movies.observeAsState(arrayListOf())

    var isGrid by remember { mutableStateOf(false) }
    var keyword by remember { mutableStateOf("") }

    LaunchedEffect(keyword) {
      viewModel.getMovies(keyword)
    }

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
        if (isGrid) MovieGridScreen(contentPadding, movies)
        else MovieListScreen(contentPadding, movies)
    }
}