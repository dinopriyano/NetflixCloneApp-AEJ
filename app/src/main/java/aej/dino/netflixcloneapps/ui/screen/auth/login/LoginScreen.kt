package aej.dino.netflixcloneapps.ui.screen.auth.login

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.ui.Routers
import aej.dino.netflixcloneapps.ui.component.MovieAppBar
import aej.dino.netflixcloneapps.ui.screen.auth.AuthViewModel
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import aej.dino.netflixcloneapps.ui.theme.RedNetflix
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api @Composable fun LoginScreen(
  navHostController: NavHostController,
  viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
) {

  var email by rememberSaveable { mutableStateOf("") }
  var password by rememberSaveable { mutableStateOf("") }
  var passwordVisible by rememberSaveable { mutableStateOf(false) }

  val userLoginResponse by viewModel.userLoin.collectAsState()

  LaunchedEffect(userLoginResponse) {
    userLoginResponse?.let { user ->
      viewModel.storeEmail(user.email)
      navHostController.navigate(Routers.HOME)
    }
  }

  Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
    MovieAppBar()
  }) { contentPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding),
    ) {
      Spacer(modifier = Modifier.weight(1f))
      OutlinedTextField(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        value = email,
        onValueChange = { email = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
          containerColor = MaterialTheme.colorScheme.surface,
          unfocusedBorderColor = Color.LightGray,
          focusedBorderColor = MaterialTheme.colorScheme.onSurface
        ),
        label = {
          Text(text = stringResource(R.string.email))
        },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Email
        ),
        shape = RoundedCornerShape(16.dp)
      )

      OutlinedTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        value = password,
        onValueChange = { password = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
          containerColor = MaterialTheme.colorScheme.surface,
          unfocusedBorderColor = Color.LightGray,
          focusedBorderColor = MaterialTheme.colorScheme.onSurface
        ),
        label = {
          Text(text = stringResource(R.string.password))
        },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Password
        ),
        trailingIcon = {
          val image = if (passwordVisible) Icons.Filled.Visibility
          else Icons.Filled.VisibilityOff

          IconButton(onClick = {
            passwordVisible = !passwordVisible
          }) {
            Icon(imageVector = image, contentDescription = null)
          }
        },
        shape = RoundedCornerShape(16.dp)
      )

      Button(
        onClick = {
                  viewModel.login(email, password)
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(top = 32.dp)
      ) {
        Text(text = stringResource(R.string.login))
      }

      Spacer(modifier = Modifier.weight(1f))

      ClickableText(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(bottom = 32.dp),
        text = buildAnnotatedString {
          withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
            append(stringResource(R.string.not_have_account))
          }
          withStyle(style = SpanStyle(color = RedNetflix, fontWeight = FontWeight.SemiBold)) {
            append(stringResource(R.string.register))
          }
        },
        style = TextStyle.Default.copy(
          textAlign = TextAlign.Center
        ),
        onClick = {
          navHostController.navigate(Routers.REGISTER)
        }
      )

    }
  }

}

@ExperimentalMaterial3Api @Preview(showBackground = true) @Composable fun LoginScreenPreview() {
  NetflixCloneAppsTheme {
    LoginScreen(navHostController = rememberNavController())
  }
}