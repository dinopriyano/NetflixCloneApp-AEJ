package aej.dino.netflixcloneapps

import aej.dino.netflixcloneapps.ui.screen.MovieListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import androidx.compose.material3.ExperimentalMaterial3Api

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

@ExperimentalMaterial3Api @Composable
fun NetflixCloneApps() {
  MovieListScreen()
}