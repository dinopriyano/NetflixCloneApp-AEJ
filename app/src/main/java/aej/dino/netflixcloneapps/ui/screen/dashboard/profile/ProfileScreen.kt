package aej.dino.netflixcloneapps.ui.screen.dashboard.profile

import aej.dino.netflixcloneapps.ui.MovieViewModel
import aej.dino.netflixcloneapps.ui.component.GhostButton
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.component.OutlineButton
import aej.dino.netflixcloneapps.ui.component.TextFieldUsername
import aej.dino.netflixcloneapps.ui.theme.RedNetflix
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MovieViewModel.Factory)
) {
    val currentUsername by viewModel.currentUsername.collectAsState()
    var profileUsername by remember { mutableStateOf(currentUsername) }

    LaunchedEffect(Unit){
        viewModel.getCurrentUsername()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        topBar = {
            MovieAppBar(onViewChange = { }, modifier = Modifier.fillMaxWidth())
        },
    ) { contentPadding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            val (profile, username, buttonUpdate, buttonLogout) = createRefs()
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(RedNetflix)
                    .constrainAs(profile) {
                        top.linkTo(parent.top, 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "S",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                    fontSize = 52.sp
                )
            }

            TextFieldUsername(
                modifier = Modifier.constrainAs(username) {
                    top.linkTo(profile.bottom, 32.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
                username = profileUsername, onValueChange = { profileUsername = it }
            )

            OutlineButton(
                modifier = Modifier.constrainAs(buttonUpdate){
                    top.linkTo(username.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = "UPDATE PROFILE"
            ) {
                /* onClick  */
            }

            GhostButton(
                modifier = Modifier.constrainAs(buttonLogout){
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = "LOGOUT",
                style = MaterialTheme.typography.titleMedium.copy(color = RedNetflix)
            ) {
                viewModel.logout()
            }

        }
    }
}

@Preview
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen(navHostController = rememberNavController())
}