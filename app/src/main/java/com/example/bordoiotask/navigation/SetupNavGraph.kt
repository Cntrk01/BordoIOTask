package com.example.bordoiotask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bordoiotask.presentation.home.HomePage

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomePage.route
    ) {
        composable(route = Screen.HomePage.route){
            HomePage()
        }

    }
}