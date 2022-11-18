package com.example.translationapp.ui

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object TranslateScreen : Screen("translate_screen")

    fun withArgs(vararg args: String): String {

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
