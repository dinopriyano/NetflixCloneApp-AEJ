package aej.dino.netflixcloneapps.ui.screen.auth.register

import aej.dino.netflixcloneapps.R
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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    val listOfGender = listOf("Male", "Female")

    val userRegisterResponse by viewModel.userRegister.collectAsState()

    LaunchedEffect(userRegisterResponse) {
        userRegisterResponse?.let {
            navHostController.popBackStack()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(contentPadding),
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
                viewModel.register(email, password)
            }

            GhostButton(text = stringResource(R.string.have_account).uppercase()) {
                navHostController.popBackStack()
            }
            Spacer(modifier = Modifier.weight(1f))
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