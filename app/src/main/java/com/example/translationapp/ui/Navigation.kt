package com.example.translationapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.translationapp.ui.main.MainScreen
import com.example.translationapp.ui.translate.TranslateScreen
import com.example.translationapp.ui.translate.TranslateViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.TranslateScreen.route + "/{translator}",
            arguments = listOf(
                navArgument("translator") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<TranslateViewModel>()
            entry.arguments?.getString("translator")
                ?.let { TranslateScreen(translator = it, viewModel = viewModel) }
        }
    }
}
