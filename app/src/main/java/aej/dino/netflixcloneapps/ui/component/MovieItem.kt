package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.core.data.MovieDatasource
import aej.dino.netflixcloneapps.core.domain.model.Movie
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    isGrid: Boolean,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            onItemClick.invoke(movie)
        }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(if (isGrid) movie.posterResourceId else movie.backdropResourceId)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.movie_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(220.dp)
            )
            Text(
                text = movie.rating.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier
                    .padding(12.dp)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .background(Color.Black)
                    .padding(9.dp)
                    .align(Alignment.TopEnd)
            )
        }
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    NetflixCloneAppsTheme {
        MovieItem(isGrid = false, movie = MovieDatasource.getNowPlayingMovie()[0], onItemClick = {})
    }
}