package aej.dino.netflixcloneapps

import aej.dino.netflixcloneapps.ui.MainViewModel
import aej.dino.netflixcloneapps.ui.Routers
import aej.dino.netflixcloneapps.ui.screen.auth.login.LoginScreen
import aej.dino.netflixcloneapps.ui.screen.auth.register.RegisterScreen
import aej.dino.netflixcloneapps.ui.screen.detail.MovieDetailScreen
import aej.dino.netflixcloneapps.ui.screen.home.HomeScreen
import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    //     private var viewModel: MainViewModel  =
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isSplash.value
            }
        }
        setContent {
            NetflixCloneAppsTheme { // A surface container using the 'background' color from the theme
                NetflixCloneApps()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NetflixCloneApps(
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MainViewModel.Factory)
) {

    val navController = rememberNavController()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getIsLoggedInUser()
    }

    isLoggedIn?.let {
        NavHost(
            navController = navController,
            startDestination = if (it) Routers.HOME else Routers.LOGIN
        ) {

            composable(
                route = Routers.LOGIN
            ) {
                LoginScreen(navController)
            }

            composable(
                route = Routers.REGISTER
            ) {
                RegisterScreen(navController)
            }

            composable(
                route = Routers.HOME
            ) {
                HomeScreen(navController)
            }

            composable(
                route = "${Routers.DETAIL}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.StringType })
            ) { navBackStackEntry ->
                val movieId = navBackStackEntry.arguments?.getString("movieId")
                MovieDetailScreen(movieId.orEmpty(), navController)
            }
        }
    }

}