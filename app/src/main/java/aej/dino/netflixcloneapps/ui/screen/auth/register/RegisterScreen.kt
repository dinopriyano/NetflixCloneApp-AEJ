package aej.dino.netflixcloneapps.ui.screen.auth.register

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.core.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.ui.component.GhostButton
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.component.OutlineButton
import aej.dino.netflixcloneapps.ui.component.TextFieldBirthDate
import aej.dino.netflixcloneapps.ui.component.TextFieldDropdown
import aej.dino.netflixcloneapps.ui.component.TextFieldEmail
import aej.dino.netflixcloneapps.ui.component.TextFieldPassword
import aej.dino.netflixcloneapps.ui.component.TextFieldUsername
import aej.dino.netflixcloneapps.ui.screen.auth.AuthViewModel
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
) {

    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    val listOfGender = listOf("Male", "Female")

    val registerRequest = RegisterRequest(
        password, date, gender.toIntOrNull() ?: 1, email, username
    )

    val userRegisterResponse by viewModel.registerScreenState.collectAsState()

    LaunchedEffect(userRegisterResponse) {
        when(userRegisterResponse) {
            is RegisterScreenState.Success -> {
                navHostController.popBackStack()
            }
            is RegisterScreenState.Error -> {
                Toast.makeText(context, (userRegisterResponse as RegisterScreenState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier
            .background(Color.Black)
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        topBar = {
            MovieAppBar()
        },
    ) { contentPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                TextFieldEmail(email = email, onValueChange = { email = it })
                TextFieldPassword(password = password, onValueChange = { password = it })
                TextFieldUsername(username = username, onValueChange = { username = it })
                TextFieldBirthDate(date = date, onValueChange = { date = it })
                TextFieldDropdown(
                    text = gender,
                    label = "Gender",
                    itemsDropdown = listOfGender,
                    onValueChange = { gender = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlineButton(text = stringResource(R.string.register).uppercase()) {
                    viewModel.register(registerRequest)
                }

                GhostButton(text = stringResource(R.string.have_account).uppercase()) {
                    navHostController.popBackStack()
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            if(userRegisterResponse is RegisterScreenState.Loading) {
                CircularProgressIndicator(
                    color = Color.White
                )
            }
        }
    }

}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    NetflixCloneAppsTheme {
        RegisterScreen(navHostController = rememberNavController())
    }
}