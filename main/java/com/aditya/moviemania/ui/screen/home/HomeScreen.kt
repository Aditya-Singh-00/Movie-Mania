package com.aditya.moviemania.ui.screen.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aditya.moviemania.R
import com.aditya.moviemania.ui.screen.common.ChangingPreviewCard
import com.aditya.moviemania.ui.screen.common.MovieHorizontalRow
import com.aditya.moviemania.ui.screen.common.SearchBar
import com.aditya.moviemania.ui.theme.cardWidth
import com.aditya.moviemania.ui.theme.spaceMedium
import com.aditya.moviemania.ui.theme.spaceSmall
import com.aditya.moviemania.ui.util.Screen

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val homeScreenState = viewModel.homeScreenState.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(spaceMedium)
            )
        }
    ) {
        when (homeScreenState) {
            is HomeScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            is HomeScreenState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.MiscellaneousServices,
                        contentDescription = null,
                        modifier = Modifier.size(cardWidth),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface)
                    )
                    Spacer(modifier = Modifier.height(spaceMedium))
                    Text(
                        text = stringResource(id = R.string.error_message)
                    )
                }
            }
            is HomeScreenState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spaceSmall),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchBar(
                        value = "",
                        onValueChange = { },
                        onClick = {
                            navController.navigate(Screen.SearchScreen.route)
                        },
                        hint = stringResource(id = R.string.search),
                        enabled = false,
                        leadingIcon = Icons.Outlined.Search,
                        keyboardActions = KeyboardActions.Default
                    )

                    LazyColumn {
                        item {
                            homeScreenState.latestMovies?.let {
                                ChangingPreviewCard(
                                    movies = it,
                                    onClick = { id ->
                                        navController.navigate(
                                            Screen.DetailScreen.route.plus("/$id")
                                        )
                                    }
                                )
                            }
                        }
                        items(items = homeScreenState.moviesWithType) { moviesWithType ->
                            MovieHorizontalRow(
                                movies = moviesWithType.movies,
                                category = moviesWithType.genre,
                                onClick = { id ->
                                    navController.navigate(
                                        Screen.DetailScreen.route.plus("/$id")
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}