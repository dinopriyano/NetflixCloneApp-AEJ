package aej.dino.netflixcloneapps

import aej.dino.netflixcloneapps.domain.model.Movie
import aej.dino.netflixcloneapps.ui.screen.MovieDetailScreen
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api

@ExperimentalMaterial3Api
class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getParcelable<Movie>(EXTRA_MOVIE)?.let { movie ->
            setContent {
                NetflixCloneAppsTheme {
                    MovieDetailScreen(movie = movie) { finish() }
                }
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}