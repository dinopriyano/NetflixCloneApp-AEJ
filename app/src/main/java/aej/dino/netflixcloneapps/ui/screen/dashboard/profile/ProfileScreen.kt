package aej.dino.netflixcloneapps.ui.screen.dashboard.profile

import aej.dino.netflixcloneapps.core.data.remote.Resource
import aej.dino.netflixcloneapps.core.data.remote.request.UpdateUserRequest
import aej.dino.netflixcloneapps.core.data.remote.response.UpdateResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import aej.dino.netflixcloneapps.ui.MovieViewModel
import aej.dino.netflixcloneapps.ui.component.GhostButton
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.component.OutlineButton
import aej.dino.netflixcloneapps.ui.component.TextFieldEmail
import aej.dino.netflixcloneapps.ui.component.TextFieldUsername
import aej.dino.netflixcloneapps.ui.theme.RedNetflix
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    var profileUsername by rememberSaveable { mutableStateOf("") }
    val currentEmail by viewModel.currentEmail.collectAsState()
    var profileEmail by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val updateUserResponse by viewModel.updateUserResponse.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getCurrentUsername()
        viewModel.getCurrentEmail()
    }

    LaunchedEffect(currentUsername) {
        profileUsername = currentUsername
    }

    LaunchedEffect(currentEmail) {
        profileEmail = currentEmail
    }

    LaunchedEffect(updateUserResponse) {
        when(updateUserResponse) {
            is Resource.Success -> {
                viewModel.storeEmail((updateUserResponse as Resource.Success<WebResponse<UpdateResponse>>).data.data.user.email)
                viewModel.storeUsername((updateUserResponse as Resource.Success<WebResponse<UpdateResponse>>).data.data.user.username)
                Toast.makeText(context, "Success to update profile!", Toast.LENGTH_SHORT).show()
            }
            is Resource.Error -> {
                Toast.makeText(context, "Failed to update profile!", Toast.LENGTH_SHORT).show()
            }
        }
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
            val (profile, username, email, buttonUpdate, buttonLogout, loading) = createRefs()
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

            TextFieldEmail(
                modifier = Modifier.constrainAs(email) {
                    top.linkTo(username.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
                email = profileEmail, onValueChange = { profileEmail = it })

            OutlineButton(
                modifier = Modifier.constrainAs(buttonUpdate){
                    top.linkTo(email.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = "UPDATE PROFILE"
            ) {
                viewModel.updateUser(
                    UpdateUserRequest(
                        email = profileEmail,
                        username = profileUsername
                    )
                )
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

            if(updateUserResponse is Resource.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .constrainAs(loading) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    strokeWidth = 4.dp,
                    color = RedNetflix
                )
            }

        }
    }
}

@Preview
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen(navHostController = rememberNavController())
}