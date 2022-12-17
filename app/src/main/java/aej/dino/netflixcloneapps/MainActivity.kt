package aej.dino.netflixcloneapps

import aej.dino.netflixcloneapps.data.MovieDatasource
import aej.dino.netflixcloneapps.domain.model.Movie
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.screen.MovieGridScreen
import aej.dino.netflixcloneapps.ui.screen.MovieListScreen
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

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
fun NetflixCloneApps() {
    val movies: List<Movie> by rememberSaveable {
        mutableStateOf(MovieDatasource.getNowPlayingMovie())
    }
    var isGrid by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieAppBar(
                onViewChange = { isGrid = it }
            )
        }
    ) { contentPadding ->
        if (isGrid) MovieGridScreen(contentPadding, movies)
        else MovieListScreen(contentPadding, movies)
    }
}