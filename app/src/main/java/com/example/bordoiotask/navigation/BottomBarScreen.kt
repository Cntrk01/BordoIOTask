package com.example.bordoiotask.navigation

import com.example.bordoiotask.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = Screen.HomePage.route,
        title = "Home",
        icon = R.drawable.baseline_home_24
    )

    object Projects : BottomBarScreen(
        route = Screen.Projects.route,
        title = "Projects",
        icon = R.drawable.baseline_dock_24
    )
}
