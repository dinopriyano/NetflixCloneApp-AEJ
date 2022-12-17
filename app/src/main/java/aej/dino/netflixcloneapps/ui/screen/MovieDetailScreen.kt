package aej.dino.netflixcloneapps.ui.screen

import aej.dino.netflixcloneapps.data.MovieDatasource
import aej.dino.netflixcloneapps.domain.model.Movie
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movie: Movie,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.Black)
        ) {
            val (backdropRef, topBarRef, ratingRef, buttonRef, overviewRef) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(backdropRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.ratio("2:3")
                        height = Dimension.fillToConstraints
                    }
                    .drawWithCache { createVerticalGradient(0, 5f) },
                painter = painterResource(id = movie.backdropResourceId),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp)
                    .drawWithCache { createVerticalGradient(1, 2f) }
            )
            MovieAppBar(
                modifier = Modifier.constrainAs(topBarRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                isTransparent = true, onBack = { onBack() }
            )
            Row(modifier = Modifier.constrainAs(ratingRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }, verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Rounded.Star, contentDescription = "", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${movie.rating}", style = TextStyle(color = Color.White))
            }
            Button(
                modifier = Modifier.constrainAs(buttonRef) {
                    top.linkTo(ratingRef.bottom, 16.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = "",
                    tint = Color.Black
                )
                Text(text = "Play", style = TextStyle(color = Color.Black))
            }
            ContentOverView(
                modifier = Modifier.constrainAs(overviewRef) {
                    top.linkTo(buttonRef.bottom, 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                movie = movie
            )
        }
    }
}

@Composable
private fun ContentOverView(modifier: Modifier = Modifier, movie: Movie) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (overviewRef, imageRef, titleRef, descRef) = createRefs()
        Text(
            modifier = Modifier.constrainAs(overviewRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = "Overview",
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
        )
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(imageRef) {
                    top.linkTo(overviewRef.bottom, 16.dp)
                    start.linkTo(parent.start)
                    width = Dimension.ratio("2:3")
                    height = Dimension.value(150.dp)
                },
            painter = painterResource(id = movie.posterResourceId),
            contentDescription = ""
        )

        Text(
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(imageRef.top)
                start.linkTo(imageRef.end, 16.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            text = movie.title,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )
        Text(
            modifier = Modifier.constrainAs(descRef) {
                top.linkTo(titleRef.bottom, 8.dp)
                start.linkTo(imageRef.end, 16.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            text = movie.description,
            style = TextStyle(color = Color.White)
        )
    }
}

/**
 * @param direction 0 -> down to up , 1 -> up to down
 * @param slicing 1f -> 11, nf = 1:n
 */
private fun CacheDrawScope.createVerticalGradient(
    direction: Int,
    slicing: Float,
): DrawResult {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = size.height / if (direction == 0) slicing else 1f,
        endY = size.height / if (direction == 1) slicing else 1f
    )
    return onDrawWithContent {
        drawContent()
        drawRect(gradientBrush, blendMode = BlendMode.Multiply)
    }
}

@Preview
@Composable
private fun PreviewContentOverView() {
    ContentOverView(movie = MovieDatasource.getNowPlayingMovie()[1])
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieDetailScreen() {
    MovieDetailScreen(MovieDatasource.getNowPlayingMovie()[1]) { }
}