package com.example.bordoiotask.navigation

sealed class Screen(val route:String){
    object HomePage : Screen(route = "home_page")
    object Projects : Screen(route = "projects_page")
}
