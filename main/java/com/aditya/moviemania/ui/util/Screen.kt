package com.aditya.moviemania.ui.util

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object DetailScreen: Screen("detail_screen")
    object SearchScreen: Screen("search_screen")
}
