package aej.dino.netflixcloneapps.ui.screen.dashboard.favourite

import aej.dino.netflixcloneapps.core.data.MovieDatasource
import aej.dino.netflixcloneapps.ui.MovieViewModel
import aej.dino.netflixcloneapps.ui.Routers
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.component.MovieItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    navController: NavHostController,
    viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MovieViewModel.Factory)
) {
    val favoriteMovies by viewModel.favoriteMovie.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getFavoriteMovies()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        topBar = {
            MovieAppBar(onViewChange = { }, modifier = Modifier.fillMaxWidth())
        },
    ) { contentPadding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(contentPadding),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    text = "Favorites",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                )
            }
            item { }
            items(favoriteMovies) { movie ->
                MovieItem(
                    isGrid = true,
                    movie = movie,
                    onItemClick = {
                        navController.navigate("${Routers.DETAIL}/${movie.id}")
                    })
            }
        }
    }

}


@Preview
@Composable
private fun PreviewFavoriteScreen() {
    val navController = rememberNavController()
    FavouriteScreen(navController = navController)
}