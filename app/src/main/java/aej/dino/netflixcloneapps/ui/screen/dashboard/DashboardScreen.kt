package aej.dino.netflixcloneapps.ui.screen.dashboard

import aej.dino.netflixcloneapps.ui.screen.dashboard.DashboardMenuItem.Menus.menus
import aej.dino.netflixcloneapps.ui.theme.RedNetflix
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api
@Composable
fun DashboardScreen(
  navHostController: NavHostController
) {
  val dashboardNavHostController = rememberNavController()

  Scaffold(
    bottomBar = {
      DashboardBottomNav(dashboardNavHostController)
    }
  ) { contentPadding ->
    Box(modifier = Modifier.padding(contentPadding)) {
      DashboardScreenController(parentNavHostController = navHostController, dashboardNavHostController = dashboardNavHostController)
    }
  }
}

@Composable fun DashboardBottomNav(
  navController: NavHostController
) {
  BottomNavigation(
    backgroundColor = Color.Black,
    elevation = 16.dp
  ) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route

    menus.forEach { menu ->
      BottomNavigationItem(
        icon = {
          Icon(
            painter = painterResource(id = menu.icon),
            contentDescription = menu.label,
            tint = if(currentScreen == menu.route) RedNetflix else Color.White
          )
        },
        selected = currentScreen == menu.route,
        onClick = {
          if( currentScreen != menu.route ) {
            navController.graph.startDestinationRoute?.let { item ->
              navController.popBackStack(
                item, false
              )
            }
            navController.navigate(menu.route) {
              launchSingleTop = true
            }
          }
        },
        alwaysShowLabel = false,
        selectedContentColor = RedNetflix
      )
    }
  }
}