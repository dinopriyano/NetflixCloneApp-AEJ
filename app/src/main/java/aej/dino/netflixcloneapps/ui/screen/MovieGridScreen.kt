package aej.dino.netflixcloneapps.ui.screen

import aej.dino.netflixcloneapps.data.MovieDatasource
import aej.dino.netflixcloneapps.domain.model.Movie
import aej.dino.netflixcloneapps.ui.component.MovieItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieGridScreen(
    paddingValues: PaddingValues, movies: List<Movie>
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(paddingValues),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                modifier = Modifier.padding(horizontal = 16.dp),
                isGrid = true,
                movie = movie
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieGridScreen() {
    MovieGridScreen(paddingValues = PaddingValues(), MovieDatasource.getNowPlayingMovie())
}