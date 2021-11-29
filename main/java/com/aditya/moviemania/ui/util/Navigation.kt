package com.aditya.moviemania.ui.util

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aditya.moviemania.ui.screen.home.HomeScreen
import com.aditya.moviemania.ui.screen.movie_detail.MovieDetailScreen
import com.aditya.moviemania.ui.screen.search.SearchScreen

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(
                route = Screen.HomeScreen.route
            ) {
                HomeScreen(navController)
            }
            composable(
                route = Screen.DetailScreen.route + "/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("noteColor")
                id?.let {
                    MovieDetailScreen(navController)
                }
            }
            composable(
                route = Screen.SearchScreen.route
            ) {
                SearchScreen(navController = navController)
            }
        }
    }
}