package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.ViewList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    imageResourceId: Int = R.drawable.ic_netflix,
    isTransparent: Boolean? = false,
    onBack: (() -> Unit)? = null,
    onViewChange: ((isGrid: Boolean) -> Unit)? = null
) {
    var isGrid by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = if (isTransparent == true) Color.Transparent else Color.Black
        ),
        title = {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = stringResource(R.string.app_bar_image),
                modifier = Modifier.height(35.dp),
                contentScale = ContentScale.FillHeight
            )
        },
        navigationIcon = {
            if (onBack != null) IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "",
                    tint = if (isTransparent == true) Color.White else Color.Black
                )
            }
        },
        actions = {
            if (onViewChange != null) IconButton(onClick = {
                isGrid = !isGrid
                onViewChange(isGrid)
            }) {
                Icon(
                    imageVector = if (isGrid) Icons.Rounded.GridView else Icons.Rounded.ViewList,
                    contentDescription = "",
                    tint = if (isTransparent == true) Color.White else Color.Black
                )
            }
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun MovieAppBarPreview() {
    NetflixCloneAppsTheme {
        MovieAppBar()
    }
}