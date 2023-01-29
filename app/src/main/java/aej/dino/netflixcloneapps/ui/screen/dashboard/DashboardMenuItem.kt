package aej.dino.netflixcloneapps.ui.screen.dashboard

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.ui.Routers
import androidx.annotation.DrawableRes

sealed class DashboardMenuItem(
  val route: String,
  val label: String,
  @DrawableRes val icon: Int
) {
  object Home: DashboardMenuItem(Routers.HOME, "Home", R.drawable.ic_home)
  object Favourite: DashboardMenuItem(Routers.FAVOURITE, "Favourite", R.drawable.ic_favorite)
  object Profile: DashboardMenuItem(Routers.PROFILE, "Profile", R.drawable.ic_profile)

  object Menus {
    val menus = listOf(
      Home, Favourite, Profile
    )
  }

}
