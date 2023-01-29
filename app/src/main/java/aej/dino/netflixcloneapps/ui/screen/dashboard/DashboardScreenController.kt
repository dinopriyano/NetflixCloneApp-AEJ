package aej.dino.netflixcloneapps.ui.screen.dashboard

import aej.dino.netflixcloneapps.ui.Routers
import aej.dino.netflixcloneapps.ui.screen.auth.register.RegisterScreen
import aej.dino.netflixcloneapps.ui.screen.dashboard.favourite.FavouriteScreen
import aej.dino.netflixcloneapps.ui.screen.dashboard.home.HomeScreen
import aej.dino.netflixcloneapps.ui.screen.dashboard.profile.ProfileScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api @Composable
fun DashboardScreenController(
  parentNavHostController: NavHostController,
  dashboardNavHostController: NavHostController
) {
  NavHost(navController = dashboardNavHostController, startDestination = Routers.HOME) {
    composable(
      route = Routers.HOME
    ) {
      HomeScreen(parentNavHostController)
    }

    composable(
      route = Routers.FAVOURITE
    ) {
      FavouriteScreen(parentNavHostController)
    }

    composable(
      route = Routers.PROFILE
    ) {
      ProfileScreen(parentNavHostController)
    }
  }
}