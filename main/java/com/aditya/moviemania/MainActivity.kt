package com.aditya.moviemania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.aditya.moviemania.ui.theme.MovieManiaTheme
import com.aditya.moviemania.ui.util.Navigation
import com.aditya.moviemania.ui.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieManiaTheme {
                Surface {
                    val navController = rememberNavController()
                    Navigation(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    )
                }
            }
        }
    }
}
