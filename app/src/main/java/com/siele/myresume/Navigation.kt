package com.siele.myresume

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavigationResGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route= Screen.MainScreen.route){
            MainContent(navController = navController)
        }
        composable(
            route= Screen.WebViewScreen.route + "/{url}",
            arguments = listOf(
                navArgument("url"){
                    type = NavType.StringType
                }
            )
        ){ entry ->
            WebViewContent(
                url = entry.arguments?.getString("url")!!,
                navController=navController)
        }
        composable(route= Screen.SettingsScreen.route){
            SettingsScreen(navController=navController)
        }
    }
}