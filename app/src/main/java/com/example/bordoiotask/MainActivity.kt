package com.example.bordoiotask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bordoiotask.navigation.Screen
import com.example.bordoiotask.navigation.SetupNavGraph
import com.example.bordoiotask.ui.theme.BordoIoTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BordoIoTaskTheme {
                BottomBarAnimationApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarAnimationApp() {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Screen.HomePage.route -> {
            bottomBarState.value = true
        }

        Screen.Projects.route -> {
            bottomBarState.value = true
        }

        else -> {
            bottomBarState.value = false
        }
    }
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, bottomBarState = bottomBarState)
        },
        content = {
            //burada padding verince bottombarda köşelikler ortaya çıkıyor !
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                SetupNavGraph(navHostController = navController)
            }
        })
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val items = listOf(BottomBarScreen.Home, BottomBarScreen.Projects)

        AnimatedVisibility(
            visible = bottomBarState.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            content = {
                BottomAppBar (
                    contentColor=Color.Transparent,
                    //modifier = Modifier.clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                    contentPadding = PaddingValues(horizontal = 10.dp),
                ){
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    items.forEach {
                        NavigationBarItem(
                            modifier = Modifier.background(Color.Transparent),
                            selected = currentRoute == it.route,
                            onClick = { navController.navigate(it.route) },
                            icon = {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = it.title
                                )
                            },
                            label = { Text(text = it.title) },
                        )
                    }
                }
            })
}
